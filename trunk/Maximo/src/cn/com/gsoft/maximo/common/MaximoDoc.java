/* ==================================================================   
 * Created [2009-7-23 ����01:46:27] by Wan Fei
 * ==================================================================  
 * MaximoDoc.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on MaximoDoc.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;
import java.util.List;

import org.w3c.dom.*;

/**
 * MaximoDoc.java
 * @author Wan Fei
 *
 */
public class MaximoDoc {

	private String ticketid;				//�¼�������
	//�û���Ϣuserinfo
	private String createby;				//������
	private String reportedbyid;						//������
	private String reportedphone;						//�����˵绰
	private String reportedemail;						//������email
	//�ⲿ��Ϣextinfo
	private String custremark;						//���м�¼��Դ
	private String custusername;						//��ϵ��
	private String custcallnumber;						//�绰
	private String custemail;						//email
	//�¼�������ϸ����incidentinfo
	private String description;						//����
	private String fromsiteid;						//������Դ
	private String internalpriority;						//���ȼ�
	private String descriptionlongdescription;						//��ϸ����
	//������Ϣassociateinfo
	private String assetdescription;						//����������
	private String locationdescription;						//������λ��
	private String siteid;						//���ڵص�
	private String orgid;						//��֯����
	//����date
	private String reportdate;						//�㱨ʱ��
	private String affecteddate;						//��Ӱ��ʱ��
	private String targetcontactdate;						//Ŀ����ϵʱ��
	private String targetstart;						//Ԥ�ƿ�ʼʱ��
	private String targetfinish;						//Ԥ�ƽ���ʱ��
	private String actualcontactdate;						//ʵ����ϵʱ��
	private String actualstart;						//ʵ�ʿ�ʼʱ��
	private String actualfinish;						//ʵ�ʽ���ʱ��
	//��־
	private List worklogs;
	
	public MaximoDoc(Document doc){
		
		Element docRoot = doc.getDocumentElement();
		NodeList nlticketid = docRoot.getElementsByTagName("ticketid");
		Element elticketid = (Element)nlticketid.item(0);
		NodeList nluserinfo = docRoot.getElementsByTagName("userinfo");
		Element eluserinfo = (Element)nluserinfo.item(0);
		NodeList nlextinfo = docRoot.getElementsByTagName("extinfo");
		Element elextinfo = (Element)nlextinfo.item(0);
		NodeList nlincidentinfo = docRoot.getElementsByTagName("incidentinfo");
		Element elincidentinfo = (Element)nlincidentinfo.item(0);
		NodeList nlassociateinfo = docRoot.getElementsByTagName("associateinfo");
		Element elassociateinfo = (Element)nlassociateinfo.item(0);
		NodeList nldate = docRoot.getElementsByTagName("date");
		Element eldate = (Element)nldate.item(0);

		try{
			this.ticketid = getElementValue(elticketid);
			
			this.createby = getChildNodeValue(eluserinfo, "createby");
			this.reportedbyid = getChildNodeValue(eluserinfo, "reportedbyid");
			this.reportedphone = getChildNodeValue(eluserinfo, "reportedphone");
			this.reportedemail = getChildNodeValue(eluserinfo, "reportedemail");
			
			this.custremark = getChildNodeValue(elextinfo, "custremark");
			this.custusername = getChildNodeValue(elextinfo, "custusername");
			this.custcallnumber = getChildNodeValue(elextinfo, "custcallnumber");
			this.custemail = getChildNodeValue(elextinfo, "custemail");
			
			this.description = getChildNodeValue(elincidentinfo, "description");
			this.fromsiteid = getChildNodeValue(elincidentinfo, "fromsiteid");
			this.internalpriority = getChildNodeValue(elincidentinfo, "internalpriority");
			this.descriptionlongdescription = getChildNodeValue(elincidentinfo, "descriptionlongdescription");
			
			this.assetdescription = getChildNodeValue(elassociateinfo, "assetdescription");
			this.locationdescription = getChildNodeValue(elassociateinfo, "locationdescription");
			this.siteid = getChildNodeValue(elassociateinfo, "siteid");
			this.orgid = getChildNodeValue(elassociateinfo, "orgid");
			
			this.reportdate = getChildNodeValue(eldate, "reportdate");
			this.affecteddate = getChildNodeValue(eldate, "affecteddate");
			this.targetcontactdate = getChildNodeValue(eldate, "targetcontactdate");
			this.targetstart = getChildNodeValue(eldate, "targetstart");
			this.targetfinish = getChildNodeValue(eldate, "targetfinish");
			this.actualcontactdate = getChildNodeValue(eldate, "actualcontactdate");
			this.actualstart = getChildNodeValue(eldate, "actualstart");
			this.actualfinish = getChildNodeValue(eldate, "actualfinish");
			WorkLogList wl = new WorkLogList(this.ticketid);
			this.setWorkLogs(wl.getLoglist());

		}catch(Exception e){
			Logger.log(e.toString());
		}
	}
	
	public String getTicketid(){
		return this.ticketid;
	}
	
	public String getCreateby(){
		return this.createby;
	}

	public String getReportedbyid(){
		return this.reportedbyid;
	}

	public String getReportedphone(){
		return this.reportedphone;
	}

	public String getReportedemail(){
		return this.reportedemail;
	}

	public String getCustremark(){
		return this.custremark;
	}

	public String getCustusername(){
		return this.custusername;
	}

	public String getCustcallnumber(){
		return this.custcallnumber;
	}

	public String getCustemail(){
		return this.custemail;
	}

	public String getDescription(){
		return this.description;
	}

	public String getFromsiteid(){
		return this.fromsiteid;
	}

	public String getInternalpriority(){
		return this.internalpriority;
	}

	public String getDescriptionlongdescription(){
		return this.descriptionlongdescription;
	}

	public String getAssetdescription(){
		return this.assetdescription;
	}

	public String getLocationdescription(){
		return this.locationdescription;
	}

	public String getSiteid(){
		return this.siteid;
	}

	public String getOrgid(){
		return this.orgid;
	}

	public String getReportdate(){
		return this.reportdate;
	}

	public String getAffecteddate(){
		return this.affecteddate;
	}

	public String getTargetcontactdate(){
		return this.targetcontactdate;
	}

	public String getTargetstart(){
		return this.targetstart;
	}

	public String getTargetfinish(){
		return this.targetfinish;
	}

	public String getActualcontactdate(){
		return this.actualcontactdate;
	}

	public String getActualstart(){
		return this.actualstart;
	}

	public String getActualfinish(){
		return this.actualfinish;
	}
	
	public List getWorkLogs(){
		return this.worklogs;
	}
	
	public void setWorkLogs(List worklogs){
		this.worklogs = worklogs;
	}
	
	private String getChildNodeValue(Element node, String childtag) throws Exception{
		NodeList children = node.getElementsByTagName(childtag);
		Element child = (Element)children.item(0);
		return getElementValue(child);
	}
	
	private String getElementValue(Element element) throws Exception{
		String value = "";
  		NodeList children = element.getChildNodes();
  		Node child = children.item(0);
		if(child==null) return "";
		switch(child.getNodeType()){
			case Node.ELEMENT_NODE:
				getElementValue((Element)child);
				break;
			case Node.TEXT_NODE:
				value = child.getNodeValue();
				break;
			case Node.CDATA_SECTION_NODE:
				value = ((CDATASection)child).getData();
				break;
		}
		return value;
	}
}
