package Dao;

import Entity.Reader;
import Util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReaderDao extends DBConnect{
    public Reader QueryReaderById(String id){
        Reader reader = new Reader();
        IODao ioDao = new IODao();
        Connection conn = null;
        try {
            conn = super.getConnection();
            String sql = "SELECT * FROM reader WHERE username=" + "'" + id + "'";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                reader.setUsername(rs.getString("username"));
                reader.setPassword(rs.getString("password"));
                reader.setName(rs.getString("name"));
                reader.setSex(rs.getString("sex"));
                reader.setStatus(rs.getInt("status"));
                reader.setMail(rs.getString("mail"));
                reader.setTel(rs.getString("tel"));
                reader.setGrade(rs.getInt("grade"));
                reader.setClassnum(rs.getInt("classnum"));
            }
            return reader;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void insertReader(Reader reader) {
    	 Connection conn = null;
         try {
             conn = super.getConnection();
             String sql = "insert into reader values(?,?,?,?,?,?,?,?,?)";
             PreparedStatement pst = null;
             pst = conn.prepareStatement(sql);
             pst.setString(1, reader.getUsername());
             pst.setString(2, reader.getPassword());
             pst.setString(3, reader.getName());
             pst.setString(4, reader.getSex());
             pst.setInt(5, 1);
             pst.setString(6, reader.getMail());
             pst.setString(7, reader.getTel());
             pst.setInt(8, reader.getGrade());
             pst.setInt(9, reader.getClassnum());
             pst.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    public ArrayList<Reader> GetAllReader(){
        ArrayList<Reader> ReaderList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = super.getConnection();
            String sql = "SELECT * FROM reader";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            Reader reader = null;
            while(rs.next()){
                reader = new Reader();
                reader.setUsername(rs.getString("username"));
                reader.setName(rs.getString("name"));
                reader.setSex(rs.getString("sex"));
                reader.setStatus(rs.getInt("status"));
                reader.setMail(rs.getString("mail"));
                reader.setGrade(rs.getInt("grade"));
                reader.setClassnum(rs.getInt("classnum"));
                reader.setTel(rs.getString("tel"));
                ReaderList.add(reader);
            }
            return ReaderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void SetBlackList(String username, boolean isblack){
        int i = 0;
        Connection conn = null;
        String sql = null;
        try {
            conn = super.getConnection();
            PreparedStatement pst = null;
            if(isblack == true)
                sql = "UPDATE reader SET status=1 WHERE username=?";
            else
                sql = "UPDATE reader SET status=-1 WHERE username=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	public boolean checkIsRegist(String username) {
	   	 Connection conn = null;
         try {
             conn = super.getConnection();
             ResultSet rs = null;
             String sql = "select * from reader where username=?";
             PreparedStatement pst = null;
             pst = conn.prepareStatement(sql);
             pst.setString(1, username);
             rs = pst.executeQuery();
             int count = 0;
             while(rs.next()) {
            	 count++;
             }
             if(count==0)
            	 return false;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return true;
        
	}

	public boolean checkReader(Reader reader) {
		System.out.println(reader);
		Connection conn = null;
		try {
             conn = super.getConnection();
             ResultSet rs = null;
             String sql = "select * from reader where username=? and password=?";
             PreparedStatement pst = null;
             pst = conn.prepareStatement(sql);
             pst.setString(1, reader.getUsername());
             pst.setString(2, reader.getPassword());
             rs = pst.executeQuery();
             int count = 0;
             while(rs.next()) {
            	 System.out.println("++");
            	 count++;
             }
             if(count==0)
            	 return false;
         } catch (Exception e) {
             e.printStackTrace();
         }
		return true;
	}

	public void editInfoByUsername(String username, Reader reader) {
		System.out.println(reader);
		Connection conn = null;
		try {
             conn = super.getConnection();
             String sql = "update reader set name=?,mail=?,sex=?,tel=?,grade=?,classnum=?,password=? where username=?";
             PreparedStatement pst = null;
             pst = conn.prepareStatement(sql);
             pst.setString(1, reader.getName());
             pst.setString(2, reader.getMail());
             pst.setString(3, reader.getSex());
             pst.setString(4, reader.getTel());
             pst.setInt(5, reader.getGrade());
             pst.setInt(6, reader.getClassnum());
             pst.setString(7, reader.getPassword());
             pst.setString(8, username);
             pst.executeUpdate();
             System.out.println("信息修改成功");
         } catch (Exception e) {
             e.printStackTrace();
         }
	}
}
