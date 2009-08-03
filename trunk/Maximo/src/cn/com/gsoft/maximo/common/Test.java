
package cn.com.gsoft.maximo.common;

import java.io.IOException;
import java.io.StringReader;

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

public class Test {
	
	public static void main(String[] args){
		/*
		String endpoint = Config.getAttribute("WebService");
		Service service = new Service();
		try {
		Call call = (Call)service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName("addWorklog");
		String ret = (String) call.invoke(new Object[] {"<?xml version=\"1.0\" encoding=\"GB2312\"?><xml siteid=\"浙江省\" orgid=\"省财政厅\" recordkey=\"1039\"><worklog><createby>sddsds</createby><createtime>2009-11-10 21:12:12</createtime><description>sasas</description><DESCRIPTIONLONGDESCRIPTION>assasasa</DESCRIPTIONLONGDESCRIPTION></worklog><worklog><createby>asaas</createby><createtime>2009-12-21 21:12:12</createtime><description>sddsdsds</description><DESCRIPTIONLONGDESCRIPTION>sdsdsds</DESCRIPTIONLONGDESCRIPTION></worklog></xml>"});
		System.out.println(ret);
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		Test t = new Test();
		
	}
	
	public Test(){
		String strlogs = "<?xml version='1.0\' encoding='GB2312'?><worklogs><worklog><worklogid><![CDATA[1601]]></worklogid><logclass><![CDATA[INCIDENT]]></logclass><createby><![CDATA[sddsds]]></createby><createdate><![CDATA[09-11-10 12:00:00]]></createdate><logtype><![CDATA[CLIENTNOTE]]></logtype><clientviewable><![CDATA[0]]></clientviewable><description><![CDATA[sasas]]></description><longdescription><![CDATA[assasasa]]></longdescription></worklog><worklog><worklogid><![CDATA[1602]]></worklogid><logclass><![CDATA[INCIDENT]]></logclass><createby><![CDATA[asaas]]></createby><createdate><![CDATA[09-12-21 12:00:00]]></createdate><logtype><![CDATA[CLIENTNOTE]]></logtype><clientviewable><![CDATA[0]]></clientviewable><description><![CDATA[sddsdsds]]></description><longdescription><![CDATA[sdsdsds]]></longdescription></worklog><worklog><worklogid><![CDATA[1621]]></worklogid><logclass><![CDATA[INCIDENT]]></logclass><createby><![CDATA[sddsds]]></createby><createdate><![CDATA[09-11-10 12:00:00]]></createdate><logtype><![CDATA[CLIENTNOTE]]></logtype><clientviewable><![CDATA[0]]></clientviewable><description><![CDATA[sasas]]></description><longdescription><![CDATA[assasasa]]></longdescription></worklog><worklog><worklogid><![CDATA[1622]]></worklogid><logclass><![CDATA[INCIDENT]]></logclass><createby><![CDATA[asaas]]></createby><createdate><![CDATA[09-12-21 12:00:00]]></createdate><logtype><![CDATA[CLIENTNOTE]]></logtype><clientviewable><![CDATA[0]]></clientviewable><description><![CDATA[sddsdsds]]></description><longdescription><![CDATA[sdsdsds]]></longdescription></worklog></worklogs>";
		strlogs = strlogs.replaceAll("\r\n", "");
    	System.out.println(strlogs);
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
				Element worklog = (Element)worklogs.item(i);
				String worklogid = getChildNodeValue(worklog, "worklogid");
				System.out.println(worklogid);
			}
		}
		catch(Exception e){
			System.out.println(0);
			Logger.log(e.toString());
		}
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
