/* ==================================================================   
 * Created [2009-7-23 ����03:27:30] by Wan Fei
 * ==================================================================  
 * TargetServer.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
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

	private String siteid;		//���ڵص�
	private String orgid;		//��֯����
	private String servername;	//����FOA��������ַ
	private String httpport;	//http�˿�
	private String dbname;		//����FOAӦ�ÿ�·��
	
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
	
	//����Maximo��������siteid��orgid���key��ѯĿ�������������Ϣ
	public TargetServer getServerNameByKey(Session session){
		Vector key = new Vector();
		key.addElement(this.siteid);
		key.addElement(this.orgid);
		try{
			Database db = session.getDatabase("", "mail/maximo.nsf");	//��ȡ�ʼ��ݴ溯����
			View vw = db.getView("serverName");		//��ȡ������Ϣ��ͼ
			Document doc = vw.getDocumentByKey(key, true);
			if(doc!=null){
				this.setServername(doc.getItemValueString("serverName"));
				this.setDbname(doc.getItemValueString("dbName"));
				this.setHttpport(doc.getItemValueString("httpPort"));
			}
			else{
				Logger.log("�����ҵ�Ŀ���ַ "+this.siteid+" "+this.orgid+" �ķ�������");
			}
		}catch(NotesException e){
			Logger.log(e.toString());
		}
		return this;
	}
}
