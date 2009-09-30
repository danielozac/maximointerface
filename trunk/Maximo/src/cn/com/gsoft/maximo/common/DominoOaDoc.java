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

	private Session session;	//���ʵ���Domino�Ự������MaximoThread��ȡ����
	private MaximoDoc mdoc;		//����XML���Maximo�ʼ�����������ʵ��
	private TargetServer ts;	//Ŀ�������������Ϣ�ĵ�
	private Document olddoc;	//MaximoThread��õ���Ҫ�����ĺ��������ڸ�����ػ�ȡ������
	
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
			db = session.getDatabase(ts.getServername(), ts.getDbname());	//Ŀ��������ϵ�FOAӦ�ÿ�
			Document doc = db.createDocument();	//Ŀ��Ӧ�ÿ��½���ת�ĵ�
			doc = olddoc.copyToDatabase(db);	//�������������½��ĵ�

			//��ò����ø�������������ɾ��
			Vector items = doc.getItems();
			for (int j=0; j<items.size(); j++) {
				Item item = (Item)items.elementAt(j);
				if(!item.getName().equals("$FILE")){
					item.remove();
				}
			}
			
			String attLogInfo = mdoc.getCreateby()+"|"+getTime()+"|U|";
			Vector v = session.evaluate("@AttachmentNames", doc);
			Item AttLog = doc.replaceItemValue("AttLog", null);	//����ƥ��FOA3.0�ĸ�������
			for(int i=0;i<v.size();i++){
				String attname = (String)v.get(i);
				if(!attname.equals(""))
					AttLog.appendToTextList(attLogInfo+attname);
			}
				
			doc.replaceItemValue("ticketid", mdoc.getTicketid());
			doc.replaceItemValue("extsiteid", mdoc.getExtsiteid());
			doc.replaceItemValue("extorgid", mdoc.getExtorgid());
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
			doc.replaceItemValue("classstructure", mdoc.getClassstructure());
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
			//Ӧ������
			Vector receiver = getDefaultReceiver(ts);
			if(receiver.size()<1){
				return "δ����Ĭ�Ͻ�����";
			}
			doc.replaceItemValue("category", mdoc.getCategory());
			doc.replaceItemValue("form", "mainform");
			doc.replaceItemValue("Author", receiver);
			doc.replaceItemValue("Unid", doc.getUniversalID());
			doc.replaceItemValue("ArriveTime", getArriveTime(receiver));
			doc.replaceItemValue("NotionsList0", mdoc.getLogvector());
			
			doc.save();
			db.recycle();	//�ͷ����ݿ�
			
			DominoOaAlert alert = new DominoOaAlert(session,ts,doc);
			alert.sendAlert();
		}
		catch(NotesException e){
			backinfo = e.toString();
			Logger.log(backinfo);
			setSendFlag(olddoc, "-1", mdoc.getTicketid(), mdoc.getExtsiteid(), mdoc.getExtorgid(), backinfo);
		}
		return backinfo;
	}
	
	public String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strTime = formatter.format(now);
        return strTime;
    }
	
	public Vector getArriveTime(Vector receiver){
		Vector arrivetime = new Vector();
		String timenow = getTime();
		for(int i=0;i<receiver.size();i++){
			String r = (String)receiver.get(i);
			String a = r + "#" + timenow;
			arrivetime.add(a);
		}
		return arrivetime;
	}
	
	public Vector getDefaultReceiver(TargetServer ts){//��ȡ��תӦ�ÿ���Ĭ�Ͻ�����
		Vector receiver = new Vector();
		String appdbname = ts.getDbname();
		String engdbname = appdbname.replaceFirst(".nsf", "_eng.nsf");
		try{
			Database engdb = session.getDatabase(ts.getServername(), engdbname);
			View all = engdb.getView("Deliver");
			String category = this.mdoc.getClassstructure().toUpperCase();
			category = category.replaceAll(",", "��");
			category = category.replaceAll(" ", "");
			Document doc = all.getDocumentByKey(category);
			if(doc!=null){
				receiver = doc.getItemValue("receiver");
			}else{
				doc = all.getDocumentByKey("Ĭ��");
				receiver = doc.getItemValue("receiver");
			}
			engdb.recycle();
		}
		catch(NotesException e){
			Logger.log(e.toString());
			setSendFlag(this.olddoc, "-1", this.mdoc.getTicketid(), this.mdoc.getExtsiteid(), this.mdoc.getExtorgid(), e.toString());
		}
		return receiver;
	}
	
	public void setSendFlag(Document doc, String flag, String ticketid, String siteid, String orgid, String error){
		try{
			doc.replaceItemValue("SENDFLAG", flag);
			if(!ticketid.equals("")){
				doc.replaceItemValue("ticketid", ticketid);				
			}
			if(!siteid.equals("")){
				doc.replaceItemValue("siteid", siteid);
			}
			if(!orgid.equals("")){
				doc.replaceItemValue("orgid", orgid);
			}
			if(error!=null){
				doc.replaceItemValue("error", error);
			}
			doc.save();
		}
		catch(NotesException e){
			Logger.log(e.toString());
		}		
	}
}
