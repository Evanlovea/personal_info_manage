package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import dao.FriendManagerDao;
import dataObject.MyFriBean;
import utils.DbUtil;

public class FriendManagerDaoImpl implements FriendManagerDao,ServletRequestAware{
	private Connection con=null;
    private Statement st=null;
    private ResultSet rs=null;
	private HttpServletRequest request;
	@Override
	//添加联系人
    public String insertFri(HttpServletRequest request,String userName,String name,String phone,String email,String workplace,String place,String QQ){
        try{
            String sure=null;
            rs=selectFri(request,userName,name);
            //判断通讯人姓名是否已存在
            if(rs.next()){
                sure="one";
            }else{
                String sql="insert into friends"+"(userName,name,phone,email,workplace,place,QQ)"+"values("+"'"+userName+"'"+","+"'"+name+"'"+","+"'"+phone+"'"+","+"'"+email+"'"+","+"'"+workplace+"'"+","+"'"+place+"'"+","+"'"+QQ+"'"+")";
                DbUtil dbUtil=new DbUtil();
                st=dbUtil.getStatement();
                int row=st.executeUpdate(sql);
                if(row==1){
                    //调用myFridnds方法，更新session中保存的通讯录中的信息
                    String fri=myFriends(request,userName);
                    if(fri.equals("ok")){
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
	//删除联系人
	    public String deleteFri(HttpServletRequest request,String userName,String name){
	        try{
	            String sure=null;
	            String sql="delete from friends where userName='"+userName+"' and name='"+name+"'";
	            DbUtil dbUtil=new DbUtil();
	            st=dbUtil.getStatement();
	            int row=st.executeUpdate(sql);
	            if(row==1){
	                //调用myFridnds方法，更新session中保存的通讯录中的信息
	                String fri=myFriends(request,userName);
	                if(fri.equals("ok")){
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
	 //查询联系人
    public ResultSet selectFri(HttpServletRequest request,String userName,String name){
        try{
            String sql="select * from friends where userName='"+userName+"' and name='"+name+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	 //获取通讯录中所有联系人的信息
    public ResultSet selectFriAll(HttpServletRequest request,String userName){
        try{
            String sql="select * from friends where userName='"+userName+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	 //获取通讯录中所有联系人的信息，并把他们保存到session对象中
    public String myFriends(HttpServletRequest request,String userName){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectFriAll(request,userName);
            if(rs.next()){
                rs=selectFriAll(request,userName);
                while(rs.next()){
                    MyFriBean mess=new MyFriBean();
                    mess.setName(rs.getString("name"));
                    mess.setPhone(rs.getString("phone"));
                    mess.setEmail(rs.getString("email"));
                    mess.setWorkplace(rs.getString("workplace"));
                    mess.setPlace(rs.getString("place")); 
                    mess.setQQ(rs.getString("QQ"));
                    listName.add(mess);
                    session.setAttribute("friends", listName);
                }
            }else{
                session.setAttribute("friends", listName);
            }
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	 //修改联系人
    public String updateFri(HttpServletRequest request,String userName,String friendName,String name,String phone,String email,String workplace,String place,String QQ){
        try{
            String sure=null;
            //先删除该联系人的信息
            String del=deleteFri(request,userName,friendName );
            if(del.equals("ok")){
                //重新录入修改后的信息
                String in=insertFri(request,userName,name,phone,email,workplace,place,QQ);
                if(in.equals("ok")){
                    //调用myFridnds方法，更新session中保存的通讯录中的信息
                    String fri=myFriends(request,userName);
                    if(fri.equals("ok")){
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
	//查找联系人，并保存其信息保存到session对象中
    public String findFri(HttpServletRequest request,String userName,String name){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectFri(request,userName,name);
            if(rs.next()){
                rs=selectFri(request,userName,name);
                while(rs.next()){
                    MyFriBean mess=new MyFriBean();
                    mess.setName(rs.getString("name"));
                    mess.setPhone(rs.getString("phone"));
                    mess.setEmail(rs.getString("email"));
                    mess.setWorkplace(rs.getString("workplace"));
                    mess.setPlace(rs.getString("place")); 
                    mess.setQQ(rs.getString("QQ"));
                    listName.add(mess);
                    session.setAttribute("findfriend", listName);
                }
            }else{
                session.setAttribute("findfriend", listName);
            }
                
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	//从查找到的联系人session对象中获取联系人姓名，并返回
    public String returnFri(HttpServletRequest request){
        String FriendName=null;
        HttpSession session=request.getSession();
        ArrayList login=(ArrayList)session.getAttribute("findfriend");
            if(login==null||login.size()==0){
                FriendName=null;
            }else{
                for(int i=login.size()-1;i>=0;i--){
                    MyFriBean nm=(MyFriBean)login.get(i);
                    FriendName=nm.getName();
                }
            }
            return FriendName;
    }
	@Override
	  public void setServletRequest(HttpServletRequest hsr) {
	     request = hsr;
	  }

}
