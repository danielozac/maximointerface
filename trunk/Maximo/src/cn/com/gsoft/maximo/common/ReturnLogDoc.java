/* ==================================================================   
 * Created [2009-8-6 ����09:58:16] by Wan Fei
 * ==================================================================  
 * ReturnLogDoc.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on ReturnLogDoc.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * ReturnLogDoc.java
 * @author Wan Fei
 * ��OA�е���־��д��Maximo
 *
 */
public class ReturnLogDoc {

	private String ticketid;
	private String siteid;
	private String orgid;
	private String xml;
	
	public ReturnLogDoc(Document xmldoc, String xml){
		Element docRoot = xmldoc.getDocumentElement();
		this.setTicketid(docRoot.getAttribute("ticketid"));
		this.setSiteid(docRoot.getAttribute("siteid"));
		this.setOrgid(docRoot.getAttribute("orgid"));
		this.setXml(xml);		
	}
	
	public void setTicketid(String ticketid){
		this.ticketid = ticketid;
	}
	
	public void setSiteid(String siteid){
		this.siteid = siteid;
	}
	
	public void setOrgid(String orgid){
		this.orgid = orgid;
	}
	
	public void setXml(String xml){
		this.xml = xml;
	}
	
	public String getTicketid(){
		return this.ticketid;
	}
	
	public String getSiteid(){
		return this.siteid;
	}
	
	public String getOrgid(){
		return this.orgid;
	}

	public String getXml(){
		return this.xml;
	}
	
	public String returnLogs(){
		String backinfo = "";
		String endpoint = Config.getAttribute("WebService");
		Service service = new Service();
		try {
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("addWorklog");	//��XML��ʽ����־��Ϣͨ��webservice��д
			call.invoke(new Object[] {this.xml});
		}
		catch(Exception e){
			Logger.log(e.toString());
			backinfo = e.toString();
			return backinfo;
		}
		return backinfo;		
	}
}
