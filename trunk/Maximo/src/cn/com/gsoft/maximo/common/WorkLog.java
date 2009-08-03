/* ==================================================================   
 * Created [2009-7-24 上午11:44:09] by Wan Fei
 * ==================================================================  
 * WorkLog.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * 杭州中科天翔科技有限公司拥有该文件的使用、复制、修改和分发的许可权
 * 如果你想得到更多信息，请访问 <http://www.g-soft.com.cn>
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

	private String worklogid;
	private String logclass;
	private String createby;
	private String createdate;
	private String logtype;
	private String clientviewable;
	private String description;
	private String longdescription;
	
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
	
}
