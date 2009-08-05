/* ==================================================================   
 * Created [2009-7-22 ����01:44:20] by Wan Fei
 * ==================================================================  
 * MaximoServlet.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * �����п�����Ƽ����޹�˾ӵ�и��ļ���ʹ�á����ơ��޸ĺͷַ������Ȩ
 * �������õ�������Ϣ������� <http://www.g-soft.com.cn>
 *
 * Gsoft S&T Co.ltd HangZhou owns permission to use, copy, modify and 
 * distribute this documentation.
 * For more information on MaximoServlet.java, please 
 * see <http://www.g-soft.com.cn>.  
 * ================================================================== 
 */

package cn.com.gsoft.maximo.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import cn.com.gsoft.maximo.common.*;

/**
 * MaximoServlet.java
 * @author Wan Fei
 * �����������̳߳����servlet
 */
public class MaximoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);
		Logger.ini();
		MaximoThread thread = new MaximoThread();
		thread.start();
	}
	
	public void destroy(){

	}
}
