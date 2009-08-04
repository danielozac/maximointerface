/* ==================================================================   
 * Created [2009-7-23 ����04:44:43] by Wan Fei
 * ==================================================================  
 * DominoOaDoc.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on DominoOaDoc.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lotus.domino.*;

/**
 * DominoOaDoc.java
 * @author Wan Fei
 *
 */
public class DominoOaDoc {

	private Session session;
	private MaximoDoc mdoc;	
	private TargetServer ts;
	private Document olddoc;
	
	public DominoOaDoc(Session session, MaximoDoc mdoc, TargetServer ts, Document olddoc){
		this.session = session;
		this.mdoc = mdoc;
		this.ts = ts;
		this.olddoc = olddoc;
	}
	
	public String createTargetServerDoc(){
		Database db;
		Document olddoc = this.olddoc;
		String backinfo = "";
		try{
			db = session.getDatabase(ts.getServername(), ts.getDbname());
			Document doc = db.createDocument();
			doc = olddoc.copyToDatabase(db);

			//��ò����ø���
			Vector items = doc.getItems();
			for (int j=0; j<items.size(); j++) {
				Item item = (Item)items.elementAt(j);
				if(!item.getName().equals("$FILE")){
					item.remove();
				}
			}
			
			String attLogInfo = mdoc.getCreateby()+"|"+getTime()+"|U|";
			Vector v = session.evaluate("@AttachmentNames", doc);
			Item AttLog = doc.replaceItemValue("AttLog", null);
			for(int i=0;i<v.size();i++){
				String attname = (String)v.get(i);
				if(!attname.equals(""))
					AttLog.appendToTextList(attLogInfo+attname);
			}
				
			doc.replaceItemValue("ticketid", mdoc.getTicketid());
			//�û���Ϣuserinfo
			doc.replaceItemValue("createby", mdoc.getCreateby());
			doc.replaceItemValue("reportedbyid", mdoc.getReportedbyid());
			doc.replaceItemValue("reportedphone", mdoc.getReportedphone());
			doc.replaceItemValue("reportedemail", mdoc.getReportedemail());
			//�ⲿ��Ϣextinfo
			doc.replaceItemValue("custremark", mdoc.getCustremark());
			doc.replaceItemValue("custusername", mdoc.getCustusername());
			doc.replaceItemValue("custcallnumber", mdoc.getCustcallnumber());
			doc.replaceItemValue("custemail", mdoc.getCustemail());
			//�¼�������ϸ����incidentinfo
			doc.replaceItemValue("description", mdoc.getDescription());
			doc.replaceItemValue("fromsiteid", mdoc.getFromsiteid());
			doc.replaceItemValue("internalpriority", mdoc.getInternalpriority());
			doc.replaceItemValue("descriptionlongdescription", mdoc.getDescriptionlongdescription());
			//������Ϣassociateinfo
			doc.replaceItemValue("assetdescription", mdoc.getAssetdescription());
			doc.replaceItemValue("locationdescription", mdoc.getLocationdescription());
			doc.replaceItemValue("siteid", mdoc.getSiteid());
			doc.replaceItemValue("orgid", mdoc.getOrgid());			
			//����date
			doc.replaceItemValue("reportdate", mdoc.getReportdate());
			doc.replaceItemValue("affecteddate", mdoc.getAffecteddate());
			doc.replaceItemValue("targetcontactdate", mdoc.getTargetcontactdate());
			doc.replaceItemValue("targetstart", mdoc.getTargetstart());
			doc.replaceItemValue("targetfinish", mdoc.getTargetfinish());
			doc.replaceItemValue("actualcontactdate", mdoc.getActualcontactdate());
			doc.replaceItemValue("actualstart", mdoc.getActualstart());
			doc.replaceItemValue("actualfinish", mdoc.getActualfinish());
			
			doc.save();
			db.recycle();
		}
		catch(NotesException e){
			backinfo = e.toString();
		}
		return backinfo;
	}
	
	public String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strTime = formatter.format(now);
        return strTime;
    }
}
