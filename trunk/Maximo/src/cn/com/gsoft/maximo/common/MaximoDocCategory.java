/* ==================================================================   
 * Created [2009-9-22 ����10:20:11] by Wan Fei
 * ==================================================================  
 * MaximoDocCategory.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on MaximoDocCategory.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * MaximoDocCategory.java
 * @author Wan Fei
 *
 */
public class MaximoDocCategory {
	
	private String category;
	
	public MaximoDocCategory(String ticketid){
		
		String endpoint = Config.getAttribute("WebService");
		Service service = new Service();
		try {
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("getSystemNameByTicketId");	//ͨ��ticketid��ȡ��־
			this.category = (String) call.invoke(new Object[] {ticketid});
		}
		catch(Exception e){
			Logger.log(e.toString());
		}
	}
	
	public String getCategory(){
		return this.category;
	}

}
