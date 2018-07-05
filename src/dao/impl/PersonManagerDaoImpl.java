package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.LoginDao;
import dao.PersonManagerDao;
import dataObject.MyMessBean;
import dataObject.UserNameBean;

import utils.DbUtil;

public class PersonManagerDaoImpl implements PersonManagerDao {
  
	private final static Logger logger=LoggerFactory.getLogger(PersonManagerDaoImpl.class);
	private Connection con=null;
    private Statement st=null;
    private ResultSet rs=null;
    private HttpServletRequest request;
    private LoginDao loginDao=new LoginDaoImpl();

	@Override
	//把个人信息通过MyMessBean，保存到session对象中
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

	@Override
	 //更新注册的个人信息
    public String updateMess(HttpServletRequest request,String userName,String name,String sex,String birth,String nation,String edu,String work,String phone,String place,String email){
        try{
            String sure=null;
            //sql语句经测试正常
            String sql="update user set name='"+name+"',sex='"+sex+"',birth='"+birth+"',nation='"+nation+"',edu='"+edu+"',work='"+work+"',phone='"+phone+"',place='"+place+"',email='"+email+"' where userName='"+userName+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            int row=st.executeUpdate(sql);
            logger.info("row"+row);
            System.out.println("row"+row);
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
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
   

	@Override

    //修改用户密码
    public String updatePass(HttpServletRequest request,String userName,String password){
		
        try{
            String sure=null;
            String sql="update user set password='"+password+"' where userName='"+userName+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            int row=st.executeUpdate(sql);
            if(row==1){
                String mess=loginDao.myLogin(request,userName);
                if(mess.equals("ok")){
                    sure="ok";
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	//查询个人信息，并返回 rs
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

	@Override
	//返回登录用户的用户名
	public String returnLogin(HttpServletRequest request){
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

}
