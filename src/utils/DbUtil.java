package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import constant.DbConstant;

public class DbUtil {
	    private Connection con=null;
	    private Statement st=null;
	    private ResultSet rs=null;
	    private HttpServletRequest request;

	    //完成连接数据库操作，并生成容器返回
	    public Statement getStatement(){
	        try{
	            Class.forName(DbConstant.driverName);
		    	
		    	Connection con=DriverManager.getConnection(DbConstant.url,DbConstant.user,DbConstant.password);
		    	return con.createStatement();
	          
	        }catch(Exception e){
	            e.printStackTrace();
	            return null;
	        }
	    }
}
