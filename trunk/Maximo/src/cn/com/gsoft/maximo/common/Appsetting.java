/* ==================================================================   
 * Created [2009-7-24 ����10:16:40] by Wan Fei
 * ==================================================================  
 * Appsetting.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
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
		return RUNNING;		//���������ش���ʱֹͣ�߳�����
	}
	
	public static void setRunninng(boolean running){
		RUNNING = running;
	}
	
}
