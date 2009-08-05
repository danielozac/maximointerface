/* ==================================================================   
 * Created [2009-7-22 下午01:44:20] by Wan Fei
 * ==================================================================  
 * MaximoServlet.java
 * ================================================================== 
 * Copyright (c) Gsoft S&T Co.ltd HangZhou, 2008-2009 
 * ================================================================== 
 * 杭州中科天翔科技有限公司拥有该文件的使用、复制、修改和分发的许可权
 * 如果你想得到更多信息，请访问 <http://www.g-soft.com.cn>
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
 * 启动整个多线程程序的servlet
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
