package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.LoginDao;
import dataObject.MyMessBean;
import dataObject.UserNameBean;

import utils.DbUtil;
/**
 * 用户登录注册接口实现
 * @author Evan
 *
 */
//实现ServletRequestAware 通过IoC方式直接访问Servlet，并通过 request获取 session对象
public class LoginDaoImpl implements LoginDao,ServletRequestAware{
	private final static Logger logger=LoggerFactory.getLogger(LoginDaoImpl.class);
	 private Statement st=null;
	 private ResultSet rs=null;
	 private Connection con=null;
	 private HttpServletRequest request;
	@Override
	//查询登录名，密码是否存在
	public ResultSet selectLogin(HttpServletRequest request,String userName,String password){
	    try{
	        String sql="select * from user where userName='"+userName+"' and password='"+password+"'";
	        DbUtil dbUtil=new DbUtil();
	        st=dbUtil.getStatement();
	        return st.executeQuery(sql);
	    }catch(Exception e){
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	//把登录人的信息保存到session对象中
	public String myLogin(HttpServletRequest request,String userName){
	    try{
	        ArrayList listName=null;
	        HttpSession session=request.getSession();
	        listName=new ArrayList();
	        rs=selectMess(request,userName);
	        if(rs.next()){
	            rs=selectMess(request,userName);
	            while(rs.next()){
	                UserNameBean mess=new UserNameBean();
	                mess.setUserName(rs.getString("userName"));
	                mess.setPassword(rs.getString("password"));
	                listName.add(mess);
	                session.setAttribute("userName", listName);
	            }
	        }else{
	            session.setAttribute("userName", listName);
	        }
	        return "ok";
	    }catch(Exception e){
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public String returnLogin(HttpServletRequest request) {
		 String loginName=null;
		    HttpSession session=request.getSession();
		    ArrayList login=(ArrayList)session.getAttribute("userName");
		        if(login==null||login.size()==0){
		            loginName=null;
		        }else{
		            for(int i=login.size()-1;i>=0;i--){
		                UserNameBean nm=(UserNameBean)login.get(i);
		                loginName=nm.getUserName();
		            }
		        }
		        return loginName;
	}

	@Override
	public String insertMess(HttpServletRequest request, String userName, String password, String name, String sex,
			String birth, String nation, String edu, String work, String phone, String place, String email) {
		 try{
	            String sure=null;
	            rs=selectMess(request,userName);
	            //判断是否用户名已存在，如果存在返回one
	            if(rs.next()){
	                sure="one";
	            }else{
	                String sql="insert into user"+"(userName,password,name,sex,birth,nation,edu,work,phone,place,email)"+"values("+"'"+userName+"'"+","+"'"+password+"'"+","+"'"+name+"'"+","+"'"+sex+"'"+","+"'"+birth+"'"+","+"'"+nation+"'"+","+"'"+edu+"'"+","+"'"+work+"'"+","+"'"+phone+"'"+","+"'"+place+"'"+","+"'"+email+"'"+")";
	                DbUtil dbUtil=new DbUtil();
	                st=dbUtil.getStatement();
	                int row=st.executeUpdate(sql);
	                if(row==1){
	                    //调用，myMessage方法，更新session中保存的用户信息
	                    String mess=myMessage(request,userName);
	                    if(mess.equals("ok")){
	                        sure="ok";
	                    }else{
	                        sure=null;
	                    }
	                }else{
	                    sure=null;
	                }
	            }
	            return sure;
	        }catch(Exception e){
	            e.printStackTrace();
	            return null;
	        }
	}

	/*@Override
	//调用myLogin、myMessage、myFriends、myDayTime、myFile方法，把所有的和用户有关的信息全部保存到session对象中,该方法，登录成功后调用
	public String addList(HttpServletRequest request,String userName){
	    String sure=null;
	    String login=myLogin(request,userName);
	    String mess=myMessage(request,userName);
	    String fri=myFriends(request,userName);
	    String day=myDayTime(request,userName);
	    String file=myFile(request,userName);
	    if(login.equals("ok")&&mess.equals("ok")&&fri.equals("ok")&&day.equals("ok")&&file.equals("ok")){
	        sure="ok";
	        
	    }else{
	        sure=null;
	    }
	    return sure;
	}*/


	@Override
	  public void setServletRequest(HttpServletRequest hsr) {
	     request = hsr;
	  }

	
	//查询个人信息，并返回 rs
	@Override
	public ResultSet selectMess(HttpServletRequest request,String userName){
	    try{
	        String sql="select * from user where userName='"+userName+"'";
	        logger.info(sql);
	        DbUtil dbUtil=new DbUtil();
	        st=dbUtil.getStatement();
	        return st.executeQuery(sql);
	    }catch(Exception e){
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//把个人信息通过MyMessBean，保存到session对象中
	@Override
	public String myMessage(HttpServletRequest request,String userName){
	    try{
	        ArrayList<MyMessBean> listName=null;
	        HttpSession session=request.getSession();
	        listName=new ArrayList<MyMessBean>();
	        rs=selectMess(request,userName);
	        while(rs.next()){
	            MyMessBean mess=new MyMessBean();
	            mess.setName(rs.getString("name"));
	            mess.setSex(rs.getString("sex"));
	            mess.setBirth(rs.getString("birth"));
	            mess.setNation(rs.getString("nation"));
	            mess.setEdu(rs.getString("edu"));
	            mess.setWork(rs.getString("work"));
	            mess.setPhone(rs.getString("phone"));
	            mess.setPlace(rs.getString("place"));
	            mess.setEmail(rs.getString("email"));
	            listName.add(mess);
	            session.setAttribute("MyMess", listName);
	        }
	        return "ok";
	    }catch(Exception e){
	        e.printStackTrace();
	        return null;
	    }
	}
	
}
