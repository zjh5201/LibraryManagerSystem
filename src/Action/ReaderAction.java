package Action;

import Dao.IODao;
import Dao.ReaderDao;
import Entity.Log;
import Entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ReaderAction extends HttpServlet {
	private ReaderDao readerDao = new ReaderDao();
	
	//Controller,转发请求。
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        if(action.equals("QueryReaderById")) {
            this.QueryReaderById(request, response);
        }
        else if (action.equals("GetBorrowListById")){
            this.GetBorrowListById(request, response);
        }
        else if (action.equals("GetAllReader")){
            this.GetAllReader(request, response);
        }
        else if(action.equals("SetBlackList")){
            this.SetBlackList(request, response);
        }else if(action.equals("getReaderInfo")) {
        	this.getReaderInfo(request,response);
        }else if(action.equals("editInfo")) {
        	this.editInfo(request,response);
        }
    }

    //修改读者信息
    private void editInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    	String name = request.getParameter("name");
    	String sex = request.getParameter("sex");
    	String mail = request.getParameter("mail");
    	String password = request.getParameter("password");
    	String tel = request.getParameter("tel");
    	int grade = Integer.parseInt(request.getParameter("grade"));
    	int classnum = Integer.parseInt(request.getParameter("classnum"));
    	Reader reader = new Reader();
    	HttpSession session = request.getSession();
    	Reader currentReader = (Reader)session.getAttribute("reader");
    	reader.setName(name);
    	reader.setSex(sex);
    	reader.setPassword(password);
    	reader.setClassnum(classnum);
    	reader.setGrade(grade);
    	reader.setMail(mail);
    	reader.setTel(tel);
    	System.out.println("info:"+reader);
    	//修改
    	readerDao.editInfoByUsername(currentReader.getUsername(),reader);
    	this.getReaderInfo(request, response);
	}
    //获取读者信息，打到页面
	private void getReaderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Reader reader = (Reader)session.getAttribute("reader");
    	String username = reader.getUsername();
    	Reader readerInfo = readerDao.QueryReaderById(username);
    	System.out.println(readerInfo);
    	request.setAttribute("reader", readerInfo);
    	request.getRequestDispatcher("/reader/readerInfo.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
	//根据id查找读者
    protected void QueryReaderById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String readerid = request.getParameter("readerid");
        System.out.println("还书readerId："+readerid);
        ReaderDao readerDao = new ReaderDao();
        Reader reader = readerDao.QueryReaderById(readerid);
        System.out.println(reader);
        String stauts = (reader.getStatus() == 1) ? "正常" : "异常";
        String callback = reader.getName() + "||" + reader.getGrade() + "年级" + reader.getClassnum() + "班||" + stauts + "||" + reader.getBorrow();
        PrintWriter out = response.getWriter();
        out.write(callback);
    }
    //根据读者号查找借阅记录。也就是我的借书记录功能实现
    protected void GetBorrowListById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("readerid");
        String borrowtime = request.getParameter("borrowtime");
        System.out.println("borrowTime="+borrowtime);
        IODao ioDao = new IODao();
        ioDao.updateBorrow(id,borrowtime);
//        ArrayList<Log> loglist = ioDao.QueryBorrowNumByReaderid(id);
//        HttpSession session = request.getSession();
//        session.setAttribute("loglist", loglist);
//        request.getRequestDispatcher("/borrowlist.jsp").forward(request,response);
        request.getRequestDispatcher("IOAction?action=getlog").forward(request,response);
    }

    //获取所有的读者
    protected void GetAllReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ReaderDao readerDao = new ReaderDao();
        ArrayList<Reader> readerlist = readerDao.GetAllReader();
        HttpSession session = request.getSession();
        session.setAttribute("readerlist", readerlist);
        request.getRequestDispatcher("/ReaderList.jsp").forward(request, response);
    }

    //设置黑名单
    protected void SetBlackList (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("id");
        String edit = request.getParameter("edit");
        ReaderDao readerDao = new ReaderDao();
        if(edit.equals("设置为正常")){
            readerDao.SetBlackList(username, true);
        }
        else{
            readerDao.SetBlackList(username, false);
        }
        this.GetAllReader(request, response);
    }
}
