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

	private String extsiteid;		//���ڵص�
	private String extorgid;		//��֯����
	private String servername;	//����FOA��������ַ
	private String diiopport;	//http�˿�
	private String dbname;		//����FOAӦ�ÿ�·��
	
	public TargetServer(){
		this.extsiteid = "";
		this.extorgid = "";
		this.servername = "127.0.0.1";
		this.dbname = "";
		this.diiopport = "63148";
	}
	
	public void setExtsiteid(String extsiteid){
		this.extsiteid = extsiteid;
	}
	
	public void setExtorgid(String extorgid){
		this.extorgid = extorgid;
	}
	
	public void setServername(String servername){
		this.servername = servername;
	}
	
	public void setDiiopport(String diiopport){
		this.diiopport = diiopport;
	}
	
	public void setDbname(String dbname){
		this.dbname = dbname;
	}
	
	public String getExtsiteid(){
		return this.extsiteid;
	}
	
	public String getExtorgid(){
		return this.extorgid;
	}
	
	public String getServername(){
		return this.servername;
	}
	
	public String getDiiopport(){
		return this.diiopport;
	}
	
	public String getDbname(){
		return this.dbname;
	}
	
	//����Maximo��������siteid��orgid���key��ѯĿ�������������Ϣ
	public TargetServer getServerNameByKey(Session session){
		Vector key = new Vector();
		key.addElement(this.extorgid);
		key.addElement(this.extsiteid);
		try{
			Database db = session.getDatabase("", "mail/maximo.nsf");	//��ȡ�ʼ��ݴ溯����
			View vw = db.getView("serverName");		//��ȡ������Ϣ��ͼ
			Document doc = vw.getDocumentByKey(key, true);
			if(doc!=null){
				this.setServername(doc.getItemValueString("serverName"));
				this.setDbname(doc.getItemValueString("dbName"));
				this.setDiiopport(doc.getItemValueString("diiopPort"));
			}
			else{
				Logger.log("�����ҵ�Ŀ���ַ "+this.extsiteid+" "+this.extorgid+" �ķ�������");
			}
		}catch(NotesException e){
			Logger.log(e.toString());
		}
		return this;
	}
}
