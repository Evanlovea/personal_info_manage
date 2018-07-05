package dao;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public interface FriendManagerDao {
	/**
	 * 增加联系人
	 * @param request
	 * @param userName
	 * @param name
	 * @param phone
	 * @param email
	 * @param workplace
	 * @param place
	 * @param QQ
	 * @return
	 */
	 public String insertFri(HttpServletRequest request,String userName,String name,String phone,String email,String workplace,String place,String QQ);
	 
	/**
	 * 删除联系人
	 * @param request
	 * @param userName
	 * @param name
	 * @return
	 */
	 public String deleteFri(HttpServletRequest request,String userName,String name);
	 /**
	  * 查询联系人
	  * @param request
	  * @param userName
	  * @param name
	  * @return
	  */
	 public ResultSet selectFri(HttpServletRequest request,String userName,String name);
	 /**
	  * 获取通讯录中所有联系人的信息
	  * @param request
	  * @param userName
	  * @return
	  */
	 public ResultSet selectFriAll(HttpServletRequest request,String userName);
	 
	 /**
	  * 获取通讯录中所有联系人的信息，并把他们保存到session对象中
	  * @param request
	  * @param userName
	  * @return
	  */
	 public String myFriends(HttpServletRequest request,String userName);
	 
	 
	 /**
	  * 修改联系人
	  * @param request
	  * @param userName
	  * @param friendName
	  * @param name
	  * @param phone
	  * @param email
	  * @param workplace
	  * @param place
	  * @param QQ
	  * @return
	  */
	 public String updateFri(HttpServletRequest request,String userName,String friendName,String name,String phone,String email,String workplace,String place,String QQ);
	 
	 
	
	 /**
	  * 查找联系人，并保存其信息保存到session对象中
	  * @param request
	  * @param userName
	  * @param name
	  * @return
	  */
	public String findFri(HttpServletRequest request,String userName,String name);
	
	 
	/**
	 * 从查找到的联系人session对象中获取联系人姓名，并返回
	 * @param request
	 * @return
	 */
    public String returnFri(HttpServletRequest request);
}
