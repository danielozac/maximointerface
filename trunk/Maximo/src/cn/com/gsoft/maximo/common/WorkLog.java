/* ==================================================================   
 * Created [2009-7-24 ����11:44:09] by Wan Fei
 * ==================================================================  
 * WorkLog.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on WorkLog.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

/**
 * WorkLog.java
 * @author Wan Fei
 *
 */
public class WorkLog {

	private String worklogid;	//��־id
	private String logclass;	//�¼���������
	private String createby;	//������
	private String createdate;	//��������
	private String logtype;		//��־����
	private String clientviewable;	//�Ƿ�ɼ�0|1
	private String description;	//����
	private String longdescription;//��ϸ����
	
	public WorkLog(){
		
	}
	
	public String getWorklogid(){
		return this.worklogid;
	}
	
	public String getLogclass(){
		return this.logclass;
	}
	
	public String getCreateby(){
		return this.createby;
	}
	
	public String getCreatedate(){
		return this.createdate;
	}
	
	public String getLogtype(){
		return this.logtype;
	}
	
	public String getClientviewable(){
		return this.clientviewable;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getLongdescription(){
		return this.longdescription;
	}
		
	public void setWorklogid(String worklogid){
		this.worklogid = worklogid;
	}
	
	public void setLogclass(String logclass){
		this.logclass = logclass;
	}

	public void setCreateby(String createby){
		this.createby = createby;
	}
	
	public void setCreatedate(String createdate){
		this.createdate = createdate;
	}
	
	public void setLogtype(String logtype){
		this.logtype = logtype;
	}

	public void setClientviewable(String clientviewable){
		this.clientviewable = clientviewable;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setLongdescription(String longdescription){
		this.longdescription = longdescription;
	}
	
	public String toString(){
		String html = "";
		html = html + "<table cellpadding=0 cellspacing=0 style='border-collapse:collapse' width=90%>";
		html = html + "<tr>";
		html = html + "<td width=15%>������</td><td width=35%>"+this.getCreateby()+"</td><td width=15%>����ʱ��</td><td width=35%>"+this.getCreatedate()+"</td>";
		html = html + "</tr>";
		html = html + "<tr>";
		html = html + "<td width=15%>����</td><td colspan=3 width=85%>"+this.getDescription()+"</td>";
		html = html + "</tr>";
		html = html + "<tr>";
		html = html + "<td width=15%>��ϸ����</td><td colspan=3 width=85%>"+this.getLongdescription()+"</td>";
		html = html + "</tr>";
		html = html + "</table>";
		return html;
	}
	
}
