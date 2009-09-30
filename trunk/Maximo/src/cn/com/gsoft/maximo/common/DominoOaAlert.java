/* ==================================================================   
 * Created [2009-9-30 上午10:43:59] by Wan Fei
 * ==================================================================  
 * DominoOaAlert.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * 杭州中科天翔科技有限公司拥有该文件的使用、复制、修改和分发的许可权
 * 如果你想得到更多信息，请访问 <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on DominoOaAlert.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.DocumentCollection;
import lotus.domino.NotesException;
import lotus.domino.Session;

/**
 * DominoOaAlert.java
 * @author Wan Fei
 *
 */
public class DominoOaAlert {
	
	private Session session;	//访问地市Domino会话，可由MaximoThread获取传入
	private TargetServer ts;	//目标服务器配置信息文档
	private Document doc;		//Domino服务器上新建的文档
	
	public DominoOaAlert(Session session,TargetServer ts,Document doc){
		this.session = session;
		this.ts = ts;
		this.doc = doc;
	}
	
	public void sendAlert(){
		try{
			Database db = session.getDatabase(ts.getServername(), "app/index.nsf");
			DocumentCollection stcol = db.search("Form = \"Msnconfig\" ");			
			int lcount =stcol.getCount();
			if(lcount>0){
				Document stDoc=stcol.getFirstDocument();
				String stm_domHost = stDoc.getItemValueString("DominoHostIp");
				String stm_domURL = stDoc.getItemValueString("dominoHttpURL");
				String stm_msgservice = stDoc.getItemValueString("msgservice");	//取消息服务地址
				String isdommailserver = stDoc.getItemValueString("isdommailserver");
				String isdomstserver = stDoc.getItemValueString("isdomstserver");
				String ismobileway = stDoc.getItemValueString("ismobileway");
				
				if(isdommailserver.equals("是")){
					
				}
				if(ismobileway.equals("是")){
					//sendsms(); //发送手机短信
				}
				if(isdomstserver.equals("是")){
					sendStmsg(stm_domHost,stm_domURL,stm_msgservice); //发送Sametime即时消息
				}
			}
			db.recycle();
			
		}catch(NotesException e) {
			System.out.println("消息发送异常。");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendStmsg(String stm_domHost,String stm_domURL,String stm_msgservice){
		try{
			if(stm_msgservice.equals(""))return;
			String filepath = ts.getDbname().replaceAll("\\\\", "/");
			String stm_user = "";
			Vector stm_users = doc.getItemValue("Author");
			//获取WebService
			String endpoint = stm_msgservice;
			Service service = new Service();
			String appDocUnid = doc.getUniversalID();
			String ticketid = doc.getItemValueString("ticketid");
			String msg = "<a herf="+stm_domURL+"/"+filepath+"/0/"+appDocUnid+"><font color=red>"+getTime()
						+"</font> 有一件工单号为 <font color=red>"+ticketid
						+"</font> 的运维工单需要您办理</a>";

			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("sendMessage");

			for (int j=0; j<stm_users.size(); j++) {
				stm_user = (String)stm_users.get(j);
				call.invoke(new Object[] {"", stm_user,msg,"1"});
			}
			
		}
		catch (NotesException e) {
			Logger.log("即时消息发送异常。");
		}
		catch(Exception e){
			Logger.log(e.toString());
		}
	}
	
	public String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strTime = formatter.format(now);
        return strTime;
    }
}
