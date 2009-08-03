/* ==================================================================   
 * Created [2009-7-22 下午02:52:26] by Wan Fei
 * ==================================================================  
 * Logger.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * 杭州中科天翔科技有限公司拥有该文件的使用、复制、修改和分发的许可权
 * 如果你想得到更多信息，请访问 <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on Logger.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

/**
 * Logger.java
 * @author Wan Fei
 *
 */
public class Logger {
	
	public static boolean logswitch;
	
	public static void ini(){
		String islog = Config.getAttribute("IsLog").toLowerCase();
		if(!islog.equals("true")) islog = "false";
		logswitch = Boolean.parseBoolean(islog);
	} 
	
	public static void log(String strlog){		
		if(logswitch){
			System.out.println(strlog);
		}
	}
}
