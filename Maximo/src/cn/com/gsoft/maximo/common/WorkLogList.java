/* ==================================================================   
 * Created [2009-7-24 下午12:21:01] by Wan Fei
 * ==================================================================  
 * WorkLogList.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * 杭州中科天翔科技有限公司拥有该文件的使用、复制、修改和分发的许可权
 * 如果你想得到更多信息，请访问 <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on WorkLogList.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * WorkLogList.java
 * @author Wan Fei
 *
 */
public class WorkLogList {

	private List loglist = new ArrayList();
	
	public WorkLogList(String ticketid){
		
		String endpoint = Config.getAttribute("WebService");
		Service service = new Service();
		try {
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("getWorklogByTicketId");
			String strlogs = (String) call.invoke(new Object[] {ticketid});

        	strlogs = strlogs.replaceAll("\r\n", "");
			Document xmldoc = null;
			StringReader sr = new StringReader(strlogs);
			InputSource iSrc = new InputSource(sr);
			DOMParser parser = new DOMParser();
			try{
				parser.parse(iSrc);
				xmldoc = parser.getDocument();
				Element docRoot = xmldoc.getDocumentElement();
				NodeList worklogs = docRoot.getElementsByTagName("worklog");
				for(int i=0;i<worklogs.getLength();i++){
					WorkLog log = new WorkLog();
					Element worklog = (Element)worklogs.item(i);
					log.setWorklogid(getChildNodeValue(worklog, "worklogid"));
					log.setLogclass(getChildNodeValue(worklog, "logclass"));
					log.setCreateby(getChildNodeValue(worklog, "createby"));
					log.setCreatedate(getChildNodeValue(worklog, "createdate"));
					log.setLogtype(getChildNodeValue(worklog, "logtype"));
					log.setClientviewable(getChildNodeValue(worklog, "clientviewable"));
					log.setDescription(getChildNodeValue(worklog, "description"));
					log.setLongdescription(getChildNodeValue(worklog, "longdescription"));
					this.loglist.add(log);
				}
			}
			catch(IOException e){
				Logger.log(e.toString());
			}
			catch(SAXException e){
				Logger.log(e.toString());
			}

		}
		catch(Exception e){
			Logger.log(e.toString());
		}
	}
	
	public List getLoglist(){
		return this.loglist;
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
