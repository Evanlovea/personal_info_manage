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

import dao.FileManagerDao;
import dataObject.MyFileBean;

import utils.DbUtil;

public class FileManagerDaoImpl implements FileManagerDao,ServletRequestAware{

	private final static Logger logger=LoggerFactory.getLogger(FileManagerDao.class);
	private Connection con=null;
    private Statement st=null;
    private ResultSet rs=null;
    private HttpServletRequest request;

	@Override
	//保存上传文件的信息
    public String insertFile(HttpServletRequest request,String userName,String title,String name,String contentType,String size,String filePath){
        try{
            String sure=null;
            //查询文件标题是否已存在
            rs=selectFile(request,userName,"title",title);
            if(rs.next()){
                sure="title";
            }else{
                //查询文件名是否已存在
                rs=selectFile(request,userName,"name",name);
                if(rs.next()){
                    sure="name";
                }else{
                    String sql="insert into file"+"(userName,title,name,contentType,size,filePath)"+"values("+"'"+userName+"'"+","+"'"+title+"'"+","+"'"+name+"'"+","+"'"+contentType+"'"+","+"'"+size+"'"+","+"'"+filePath+"'"+")";
                    DbUtil dbUtil=new DbUtil();
                    st=dbUtil.getStatement();
                    int row=st.executeUpdate(sql);
                    if(row==1){
                        //调用myFile方法，更新session中保存的文件信息
                        String file=myFile(request,userName);
                        if(file.equals("ok")){
                            sure="ok";
                        }else{
                            sure=null;
                        }
                    }else{
                        sure=null;
                    }
                }
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	//删除文件
    public String deleteFile(HttpServletRequest request,String userName,String title){
        try{
            String sure=null;
            String sql="delete from file where userName='"+userName+"' and title='"+title+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            int row=st.executeUpdate(sql);
            if(row==1){
                //调用myFile方法，更新session中保存的文件信息
                String file=myFile(request,userName);
                if(file.equals("ok")){
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
	 //修改文件
    public String updateFile(HttpServletRequest request,String userName,String Title,String title,String name,String contentType,String size,String filePath){
        try{
            String sure=null;
            //先删除该文件
            String del=deleteFile(request,userName,Title);
            if(del.equals("ok")){
                //从新录入修改后的信息
                String in=insertFile(request,userName,title,name,contentType,size,filePath);
                if(in.equals("ok")){
                    //调用myFile方法，更新session中保存的文件信息
                    String file=myFile(request,userName);
                    if(file.equals("ok")){
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
	//查询文件
    public ResultSet selectFile(HttpServletRequest request,String userName,String type,String name){
        try{
            String sql="select * from file where userName='"+userName+"' and "+type+"='"+name+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	//查询所有的文件信息
    public ResultSet selectFileAll(HttpServletRequest request,String userName){
        try{
            String sql="select * from file where userName='"+userName+"'";
            DbUtil dbUtil=new DbUtil();
            st=dbUtil.getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	//查询所有的文件信息，并把他们保存到session对象中
    public String myFile(HttpServletRequest request,String userName){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectFileAll(request,userName);
            if(rs.next()){
                rs=selectFileAll(request,userName);
                while(rs.next()){
                    MyFileBean mess=new MyFileBean();
                    mess.setTitle(rs.getString("title"));
                    mess.setName(rs.getString("name"));
                    mess.setContentType(rs.getString("contentType"));
                    mess.setSize(rs.getString("size"));
                    listName.add(mess);
                    session.setAttribute("file", listName);
                }
            }else{
                session.setAttribute("file", listName);
            }   
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    

	@Override
	//查找文件信息，并把文件的信息保存到session对象中
    public String findFile(HttpServletRequest request,String userName,String title){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectFile(request,userName,"title",title);
            if(rs.next()){
                rs=selectFile(request,userName,"title",title);
                while(rs.next()){
                    MyFileBean mess=new MyFileBean();
                    mess.setTitle(rs.getString("title"));
                    mess.setName(rs.getString("name"));
                    mess.setContentType(rs.getString("contentType"));
                    mess.setSize(rs.getString("size"));
                    mess.setFilePath(rs.getString("filePath"));
                    listName.add(mess);
                    session.setAttribute("findfile", listName);
                }
            }else{
                session.setAttribute("findfile", listName);
            }
                
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

	@Override
	//根据不同的条件，从查找到的文件session对象中获取文件相应的文件信息
    public String returnFile(HttpServletRequest request,String face){
        String file=null;
        HttpSession session=request.getSession();
        ArrayList login=(ArrayList)session.getAttribute("findfile");
            if(login==null||login.size()==0){
                file=null;
            }else{
                for(int i=login.size()-1;i>=0;i--){
                    MyFileBean nm=(MyFileBean)login.get(i);
                    if(face.equals("title")){
                        file=nm.getTitle();
                    }else if(face.equals("filePath")){
                        file=nm.getFilePath();
                    }if(face.equals("fileName")){
                        file=nm.getName();
                    }
                }
            }
            return file;
    }




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
	  public void setServletRequest(HttpServletRequest hsr) {
	     request = hsr;
	  }

}
