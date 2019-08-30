package Action;

import Dao.UserDao;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginAction extends HttpServlet {
    UserDao udao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    	String action = request.getParameter("action");
        if (action.equals("login")) {
            this.login(request, response);
        }
        else if(action.equals("logout")){
            this.logout(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    //登录功能的实现
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        String password = null;
        //获取PrintWriter
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        username = request.getParameter("username");
        password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //用户登录检测。
        String result = udao.login(user);
        if(result.equals("true")){
            session.setAttribute("adminname", user.getName());
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        }
        else{
        	//打数据给前台，前台接收到利用js进行处理，显示用户登录的错误信息
            out.write(result);
        }
    }

    //用户退出
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1);
        session.setAttribute("logout", "1");
        response.sendRedirect("/Library/index.jsp");
    }

}