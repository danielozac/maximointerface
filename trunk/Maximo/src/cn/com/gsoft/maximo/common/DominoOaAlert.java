/* ==================================================================   
 * Created [2009-9-30 ����10:43:59] by Wan Fei
 * ==================================================================  
 * DominoOaAlert.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
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
	
	private Session session;	//���ʵ���Domino�Ự������MaximoThread��ȡ����
	private TargetServer ts;	//Ŀ�������������Ϣ�ĵ�
	private Document doc;		//Domino���������½����ĵ�
	
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
				String stm_msgservice = stDoc.getItemValueString("msgservice");	//ȡ��Ϣ�����ַ
				String isdommailserver = stDoc.getItemValueString("isdommailserver");
				String isdomstserver = stDoc.getItemValueString("isdomstserver");
				String ismobileway = stDoc.getItemValueString("ismobileway");
				
				if(isdommailserver.equals("��")){
					
				}
				if(ismobileway.equals("��")){
					//sendsms(); //�����ֻ�����
				}
				if(isdomstserver.equals("��")){
					sendStmsg(stm_domHost,stm_domURL,stm_msgservice); //����Sametime��ʱ��Ϣ
				}
			}
			db.recycle();
			
		}catch(NotesException e) {
			System.out.println("��Ϣ�����쳣��");
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
			//��ȡWebService
			String endpoint = stm_msgservice;
			Service service = new Service();
			String appDocUnid = doc.getUniversalID();
			String ticketid = doc.getItemValueString("ticketid");
			String msg = "<a herf="+stm_domURL+"/"+filepath+"/0/"+appDocUnid+"><font color=red>"+getTime()
						+"</font> ��һ��������Ϊ <font color=red>"+ticketid
						+"</font> ����ά������Ҫ������</a>";

			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("sendMessage");

			for (int j=0; j<stm_users.size(); j++) {
				stm_user = (String)stm_users.get(j);
				call.invoke(new Object[] {"", stm_user,msg,"1"});
			}
			
		}
		catch (NotesException e) {
			Logger.log("��ʱ��Ϣ�����쳣��");
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
