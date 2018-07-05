package dao;

import javax.servlet.http.HttpServletRequest;

public interface AddInfoToList {
	 /**
	  * 调用myLogin、myMessage、myFriends、myDayTime、myFile方法，把所有的和用户有关的信息全部保存到session对象中,该方法，登录成功后调用
	  * @param request
	  * @param userName
	  * @return
	  */
	 public String addList(HttpServletRequest request,String userName);

}
