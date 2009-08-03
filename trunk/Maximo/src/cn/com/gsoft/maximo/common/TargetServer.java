/* ==================================================================   
 * Created [2009-7-23 下午03:27:30] by Wan Fei
 * ==================================================================  
 * TargetServer.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * 杭州中科天翔科技有限公司拥有该文件的使用、复制、修改和分发的许可权
 * 如果你想得到更多信息，请访问 <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on TargetServer.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

import java.util.Vector;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.Session;
import lotus.domino.View;

/**
 * TargetServer.java
 * @author Wan Fei
 *
 */
public class TargetServer {

	private String siteid;
	private String orgid;
	private String servername;
	private String httpport;
	private String dbname;
	
	public TargetServer(){
		this.siteid = "";
		this.orgid = "";
		this.servername = "127.0.0.1";
		this.dbname = "";
		this.httpport = "80";
	}
	
	public void setSiteid(String siteid){
		this.siteid = siteid;
	}
	
	public void setOrgid(String orgid){
		this.orgid = orgid;
	}
	
	public void setServername(String servername){
		this.servername = servername;
	}
	
	public void setHttpport(String httpport){
		this.httpport = httpport;
	}
	
	public void setDbname(String dbname){
		this.dbname = dbname;
	}
	
	public String getSiteid(){
		return this.siteid;
	}
	
	public String getOrgid(){
		return this.orgid;
	}
	
	public String getServername(){
		return this.servername;
	}
	
	public String getHttpport(){
		return this.httpport;
	}
	
	public String getDbname(){
		return this.dbname;
	}
	
	public TargetServer getServerNameByKey(Session session){
		Vector key = new Vector();
		key.addElement(this.siteid);
		key.addElement(this.orgid);
		try{
			Database db = session.getDatabase("", "mail/maximo.nsf");
			View vw = db.getView("serverName");
			Document doc = vw.getDocumentByKey(key, true);
			if(doc!=null){
				this.setServername(doc.getItemValueString("serverName"));
				this.setDbname(doc.getItemValueString("dbName"));
				this.setHttpport(doc.getItemValueString("httpPort"));
			}
			else{
				Logger.log("不能找到目标地址 "+this.siteid+" "+this.orgid+" 的服务器名");
			}
		}catch(NotesException e){
			Logger.log(e.toString());
		}
		return this;
	}
}
