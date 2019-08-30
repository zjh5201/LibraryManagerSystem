package Action;

import Dao.IODao;
import Entity.Log;
import Entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IOAction extends HttpServlet {
	
//	public void responseJson(List<T> list, HttpServletResponse response) throws ServletException, IOException{
//		response.getWriter().print(JSONArray.fromObject(list));
//	}
	//dopost请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    	String action = request.getParameter("action");
        if(action.equals("borrow")){
            this.borrow(request, response);
        }
        else if(action.equals("getlog")){
            this.getlog(request, response);
        }
        else if(action.equals("return")){
            this.ReturnBook(request, response);
        }else if(action.equals("xujie")) {
        	this.xujie(request,response);
        }
    }
    //续借功能
    private void xujie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	int addDays = Integer.parseInt(request.getParameter("days"));
    	String borrowTime = request.getParameter("borrowtime");
    	IODao ioDao = new IODao();
    	//先获取到目前的借阅时间
    	int day = ioDao.getBorrowDay(borrowTime);
    	//修改为续借后的天数
    	ioDao.updateBorrowDay(borrowTime,day+addDays);
    	//然后请求以下URL，跳转到借阅记录页
    	request.getRequestDispatcher("IOAction?action=getlog&user=1").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
	
	//图书借阅
    protected void borrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
    	IODao ioDao = new IODao();
        //设置存储到数据库中的时间格式
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date date = new Date();
        String time = sdf.format(date);
        String readerid = request.getParameter("readerid");
        String bookid = request.getParameter("bookid");
        int borrowday = Integer.parseInt(request.getParameter("borrowday"));
        //借阅
        ioDao.borrow(bookid, readerid, time, borrowday);
        //去指定方法查看
        this.getlog(request,response);
    }
    
    //获取所有的借阅记录
    protected void getlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        IODao ioDao = new IODao();
        ArrayList<Log> loglist = ioDao.getLogList();
        String user = request.getParameter("user");
        HttpSession session = request.getSession();
        Reader reader = (Reader)session.getAttribute("reader");
        //如果是用户，返回给用户界面，如果是管理员，返回给管理员界面
        if(user!=null&&user.equals("1")) {
        	ArrayList<Log> curReaderLogList = new ArrayList<Log>();
        	for(int i = 0;i<loglist.size();i++) {
        		if(loglist.get(i).getReaderid().equals(reader.getUsername())){
        			curReaderLogList.add(loglist.get(i));
        		}
        	}
        	session.setAttribute("loglist", curReaderLogList);
        	request.getRequestDispatcher("/reader/IOLog.jsp").forward(request, response);
        }else {
        	session.setAttribute("loglist", loglist);
        	request.getRequestDispatcher("/IOLog.jsp").forward(request, response);
        }
        
    }
    //还书功能。
    protected void ReturnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        IODao ioDao = new IODao();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date date = new Date();
        String Returntime = sdf.format(date);
        String bookid = request.getParameter("bookid");
        String readerId = request.getParameter("ReaderId");
        String borrowtime = request.getParameter("borrowtime");
        //还书
        ioDao.ReturnBook(bookid, readerId, borrowtime, Returntime);
        this.getlog(request,response);
    }
    
    
    
}
