package Action;

import Dao.BookDao;
import Entity.Book;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BookAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    //处理get请求，这个doGet方法就是MVC中的C层
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//设置编码为Utf-8
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //从request域中获取action参数，参数里存放的是要调用该类中的方法。
        String action = request.getParameter("action");

        //比如这里，如果action=getAll,就跳到底下的getAll方法。这个getAll方法就相当于Service层，专门用来处理业务逻辑的
        if(action.equals("getall")){
        	//看代码第55行。
            this.getAll(request, response);
        }
        else if(action.equals("addtemp")) {
            this.addtemp(request, response);
        }
        else if(action.equals("gettemp")) {
            this.gettemp(request, response);
        }
        else if(action.equals("confirm")) {
            this.confirm(request, response);
        }
        else if(action.equals("querybookbyid")){
            this.QueryBookById(request, response);
        }
        else if(action.equals("DeleteById")){
            this.DeleteById(request, response);
        }
        else if(action.equals("EditDone")){
            this.EditDone(request, response);
        }
        else if(action.equals("truncatetable")){
           this.truncatetable(request, response); 
        }
    }
    //调用该方法后，该方法是获取所有的图书信息。
    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先创建一个Dao，Dao是用来和数据库进行交互的
    	BookDao bdao = new BookDao();
    	//从request域中获取user参数对应的值。
        String user = request.getParameter("user");
        System.out.println("getAll");
        //获取用户传来的bookname
        String bookName = request.getParameter("bookname");
        System.out.println("bookName="+bookName);
        //调用dao的getAllBook()方法。
        ArrayList<Book> bookArrayList = bdao.getAllBook();
        //定义一个最终的存储图书的book集合。
        ArrayList<Book> finalBookList = new ArrayList<Book>();
        //对bookArrayList进行遍历，如果前台传递来bookName参数时，那么就是代表模糊查询，把查询出来的图书添加到最终集合里。
        if(bookName!=null&&!bookName.equals("")) {
        	for(Book book:bookArrayList) {
        		if(book.getName().contains(bookName)) {
        			finalBookList.add(book);
        		}
        	}
        }else {
        	//否则就添加全部
        	finalBookList.addAll(bookArrayList);
        }
        //存到request域中
        request.setAttribute("allbooklist", finalBookList);
        //如果是读者登录，就转发到读者的图书展示界面
        if(user!=null&&user.equals("1")) {
        	request.getRequestDispatcher("/reader/booklist.jsp").forward(request, response);
        }else {
        	//如果是管理员登录，就转到管理员的图书列表界面。
        	System.out.println("跑到admin");
        	request.getRequestDispatcher("/booklist.jsp").forward(request, response);
        }
    }

    //添加图书到预添加列表。
    private void addtemp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取over参数，如果为0，代表预添加未结束，返回页面，继续添加，如果不是，则返回到图书总表，添加成功。
    	String next = request.getParameter("over");
        BookDao bdao = new BookDao();
        Book book = new Book();
        //封装图书信息到实体类。
        if(!request.getParameter("bookid").equals(""))
            book.setId(request.getParameter("bookid"));
        if(!request.getParameter("bookname").equals(""))
            book.setName(request.getParameter("bookname"));
        if(!request.getParameter("bookauthor").equals(""))
            book.setAuthor(request.getParameter("bookauthor"));
        if(!request.getParameter("bookpublisher").equals(""))
            book.setPublisher(request.getParameter("bookpublisher"));
        if(!request.getParameter("bookcategory").equals(""))
            book.setCategory(request.getParameter("bookcategory"));
        if(!request.getParameter("bookprice").trim().equals(""))
            book.setPrice(Integer.parseInt(request.getParameter("bookprice")));
        if(!request.getParameter("bookstore").trim().equals(""))
            book.setStore(Integer.parseInt(request.getParameter("bookstore")));
        if(!request.getParameter("booklocation").equals(""))
            book.setLocation(request.getParameter("booklocation"));
        if(!request.getParameter("bookdesc").equals(""))
            book.setDesc(request.getParameter("bookdesc"));
        if(!request.getParameter("bookid").equals(""))
        	//添加到数据库
            bdao.addtemp(book);
        if(next.equals("0"))
        	//返回到添加图书界面
            request.getRequestDispatcher("/AddBook.jsp").forward(request, response);
        else
        	
            this.gettemp(request,response);
    }
    //返回图书总表
    private void gettemp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BookDao bdao = new BookDao();
        ArrayList<Book> addbooklist = bdao.getadd();
        HttpSession session = request.getSession();
        session.setAttribute("addbooklist", addbooklist);
        request.getRequestDispatcher("/addconfirm.jsp").forward(request,response);
    }
    //把预添加的图书添加到图书表中，然后调用getAll返回给前台。
    private void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BookDao bdao = new BookDao();
        bdao.confirm();
        this.getAll(request, response);
    }
    //根据Id查找图书
    private void QueryBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BookDao bdao = new BookDao();
        String id = request.getParameter("id");
        String next = request.getParameter("next");
        Book book = bdao.QueryBookById(id);
        HttpSession session = request.getSession();
        session.setAttribute("resultbook", book);
        PrintWriter out = response.getWriter();
        if(next.equals("check"))
            request.getRequestDispatcher("/detail.jsp").forward(request, response);
        else if(next.equals("edit"))
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        else if(next.equals("borrowcheck")){
            out.write(book.getName() + "||" + book.getAuthor() + "||" + book.getPublisher() + "||" + book.getRemain());
        }
        else if(next.equals("existcheck")){
            out.write(book.getName());
        }else if(next.equals("r_check")) {
        	request.getRequestDispatcher("/reader/detail.jsp").forward(request, response);
        }
    }
    //根据Id删除图书
    private void DeleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BookDao bdao = new BookDao();
        String id = request.getParameter("id");
        bdao.DeleteById(id);
        this.getAll(request, response);
    }
    //修改图书信息
    private void EditDone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BookDao bdao = new BookDao();
        Book book = new Book();
        if(!request.getParameter("id").equals(""))
            book.setId(request.getParameter("id"));
        if(!request.getParameter("name").equals(""))
            book.setName(request.getParameter("name"));
        if(!request.getParameter("author").equals(""))
            book.setAuthor(request.getParameter("author"));
        if(!request.getParameter("publisher").equals(""))
            book.setPublisher(request.getParameter("publisher"));
        if(!request.getParameter("category").equals(""))
            book.setCategory(request.getParameter("category"));
        if(!request.getParameter("price").trim().equals(""))
            book.setPrice(Integer.parseInt(request.getParameter("price")));
        if(!request.getParameter("store").trim().equals(""))
            book.setStore(Integer.parseInt(request.getParameter("store")));
        if(!request.getParameter("location").equals(""))
            book.setLocation(request.getParameter("location"));
        if(!request.getParameter("desc").equals(""))
            book.setDesc(request.getParameter("desc"));
        if(!request.getParameter("id").equals(""))
            bdao.addtemp(book);
        bdao.EditDone(book);
        request.getRequestDispatcher("BookAction?action=querybookbyid&id=<%=b.getId()%>&next=check").forward(request, response);
    }
    //清空所有预添加图书
    private void truncatetable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BookDao bookDao = new BookDao();
        bookDao.truncatetable();
        this.gettemp(request, response);
    }
}
