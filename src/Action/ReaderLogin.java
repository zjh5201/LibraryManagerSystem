package Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ReaderDao;
import Entity.Reader;
import cn.itcast.commons.CommonUtils;

public class ReaderLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReaderDao readerDao = new ReaderDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		request.setCharacterEncoding("utf-8");
 		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
        if (action.equals("login")) {
            this.login(request, response);
        }else if(action.equals("logout")){
            this.logout(request, response);
        }else if(action.equals("regist")) {
        	this.regist(request, response);
        }
	}
	//读者登录
	 private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        Reader reader = new Reader();
        reader.setUsername(username);
        reader.setPassword(password);
         if(readerDao.checkReader(reader)) {
        	session.setAttribute("reader", reader);
        	request.getRequestDispatcher("/reader/main.jsp").forward(request, response);
        }else {
        	out.write("false");
        }
    }
	 //读者注册、
	 	private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 		Reader reader = CommonUtils.toBean(request.getParameterMap(), Reader.class);
	 		if(!readerDao.checkIsRegist(reader.getUsername())) {
	 			readerDao.insertReader(reader);
		 		request.setAttribute("username", reader.getUsername());
		 		request.getRequestDispatcher("/readerLogin.jsp").forward(request, response);
	 		}else {
	 			request.setAttribute("msg", "您的用户名已注册");
	 			request.setAttribute("reader", reader);
	 			request.getRequestDispatcher("/readerRegist.jsp").forward(request, response);
	 		}
	 		
	    }


	    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	        HttpSession session = request.getSession();
	        session.setMaxInactiveInterval(1);
	        session.setAttribute("logout", "1");
	        response.sendRedirect("/Library/readerLogin.jsp");
	    }
	

}
