/* ==================================================================   
 * Created [2009-7-23 下午01:46:27] by Wan Fei
 * ==================================================================  
 * MaximoDoc.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * 杭州中科天翔科技有限公司拥有该文件的使用、复制、修改和分发的许可权
 * 如果你想得到更多信息，请访问 <http://www.g-soft.com.cn>
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

	private String ticketid;				//事件工单号
	//用户信息userinfo
	private String createby;				//创建人
	private String reportedbyid;						//报告人
	private String reportedphone;						//报告人电话
	private String reportedemail;						//报告人email
	//外部信息extinfo
	private String custremark;						//呼叫记录来源
	private String custusername;						//联系人
	private String custcallnumber;						//电话
	private String custemail;						//email
	//事件工单详细描述incidentinfo
	private String description;						//描述
	private String fromsiteid;						//问题来源
	private String internalpriority;						//优先级
	private String descriptionlongdescription;						//详细描述
	//关联信息associateinfo
	private String assetdescription;						//关联配置项
	private String locationdescription;						//配置项位置
	private String siteid;						//所在地点
	private String orgid;						//组织机构
	//日期date
	private String reportdate;						//汇报时间
	private String affecteddate;						//受影响时间
	private String targetcontactdate;						//目标联系时间
	private String targetstart;						//预计开始时间
	private String targetfinish;						//预计结束时间
	private String actualcontactdate;						//实际联系时间
	private String actualstart;						//实际开始时间
	private String actualfinish;						//实际结束时间
	//日志
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
