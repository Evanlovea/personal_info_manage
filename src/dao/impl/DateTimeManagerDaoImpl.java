package dao.impl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import dao.DateTimeManagerDao;
import dataObject.MyDayBean;
import utils.DbUtil;

public class DateTimeManagerDaoImpl implements DateTimeManagerDao,ServletRequestAware{
	 private HttpServletRequest request;
	 private Statement st=null;
	 private ResultSet rs=null;


	@Override
	public String returnDay(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
    //查询日程
    public ResultSet selectDay(HttpServletRequest request,String userName,String date){
        try{
            String sql="select * from date where userName='"+userName+"' and date='"+date+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
    //查询所有的日程信息，并把他们保存到session对象中
    public String myDayTime(HttpServletRequest request,String userName){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectDayAll(request,userName);
            if(rs.next()){
                rs=selectDayAll(request,userName);
                while(rs.next()){
                    MyDayBean mess=new MyDayBean();
                    mess.setDay(rs.getString("date"));
                    mess.setThing(rs.getString("thing"));
                    listName.add(mess);
                    session.setAttribute("day", listName);
                }
            }else{
                session.setAttribute("day", listName);
            }   
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	 //添加日程
    public String insertDay(HttpServletRequest request,String userName,String date,String thing){
        try{
            String sure=null;
            rs=selectDay(request,userName,date);
            //判断是否日程已有安排
            if(rs.next()){
                sure="one";
            }else{
                String sql="insert into date"+"(userName,date,thing)"+"values("+"'"+userName+"'"+","+"'"+date+"'"+","+"'"+thing+"'"+")";
                DbUtil dbUtil=new DbUtil();
                st=dbUtil.getStatement();
                int row=st.executeUpdate(sql);
                if(row==1){
                    //调用myDayTime方法，更新session对象中保存的日程信息
                    String day=myDayTime(request,userName);
                    if(day.equals("ok")){
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

	@Override
    //删除日程
    public String deleteDay(HttpServletRequest request,String userName,String date){
        try{
            String sure=null;
            String sql="delete from date where userName='"+userName+"' and date='"+date+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            int row=st.executeUpdate(sql);
            if(row==1){
                //调用myDayTime方法，更新session对象中保存的日程信息
                String day=myDayTime(request,userName);
                if(day.equals("ok")){
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
	   //修改日程
    public String updateDay(HttpServletRequest request,String userName,String Day,String date,String thing){
        try{
            String sure=null;
            //先删除该日程
            String del=deleteDay(request,userName,Day);
            if(del.equals("ok")){
                //从新录入修改后的信息
                String in=insertDay(request,userName,date,thing);
                if(in.equals("ok")){
                    //调用myDayTime方法，更新session对象中保存的日程信息
                    String day=myDayTime(request,userName);
                    if(day.equals("ok")){
                        sure="ok";
                    }else{
                        sure=null;
                    }
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
    //查询所有的日程信息
    public ResultSet selectDayAll(HttpServletRequest request,String userName){
        try{
            String sql="select * from date where userName='"+userName+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	  public void setServletRequest(HttpServletRequest hsr) {
	     request = hsr;
	  }



	@Override
	public String findDay(HttpServletRequest request, String userName, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
