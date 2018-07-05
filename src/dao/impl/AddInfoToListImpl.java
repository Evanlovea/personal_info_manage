package dao.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import dao.AddInfoToList;
import dao.DateTimeManagerDao;
import dao.FileManagerDao;
import dao.FriendManagerDao;
import dao.LoginDao;

public class AddInfoToListImpl implements AddInfoToList,ServletRequestAware{
	private HttpServletRequest request;
	private LoginDao loginDao=new LoginDaoImpl();
	private DateTimeManagerDao dateTimeManagerDao=new DateTimeManagerDaoImpl();
	private FileManagerDao fileManagerDao=new FileManagerDaoImpl();
	private FriendManagerDao friendManagerDao=new FriendManagerDaoImpl();
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
	request=arg0;
		
	}

	@Override
	public String addList(HttpServletRequest request, String userName) {
	    String sure=null;
	    String login=loginDao.myLogin(request,userName);
	    String mess=loginDao.myMessage(request,userName);
	    String fri=friendManagerDao.myFriends(request,userName);
	    String day=dateTimeManagerDao.myDayTime(request,userName);
	    String file=fileManagerDao.myFile(request,userName);
	    if(login.equals("ok")&&mess.equals("ok")&&fri.equals("ok")&&day.equals("ok")&&file.equals("ok")){
	        sure="ok";
	        
	    }else{
	        sure=null;
	    }
	    return sure;
	}

}
