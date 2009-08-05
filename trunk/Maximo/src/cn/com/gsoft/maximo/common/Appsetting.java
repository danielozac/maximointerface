/* ==================================================================   
 * Created [2009-7-24 上午10:16:40] by Wan Fei
 * ==================================================================  
 * Appsetting.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * 杭州中科天翔科技有限公司拥有该文件的使用、复制、修改和分发的许可权
 * 如果你想得到更多信息，请访问 <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on Appsetting.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.common;

/**
 * Appsetting.java
 * @author Wan Fei
 *
 */
public class Appsetting {

	public static boolean RUNNING = true;
	
	public static boolean getRunning(){
		return RUNNING;		//当发生严重错误时停止线程启动
	}
	
	public static void setRunninng(boolean running){
		RUNNING = running;
	}
	
}
