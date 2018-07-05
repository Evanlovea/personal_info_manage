package dao;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public interface PersonManagerDao {

    /**
     * 查询个人信息并返回
     * @param request
     * @param userName
     * @return
     */
	ResultSet selectMess(HttpServletRequest request, String userName);

	/**
	 * 把个人信息通过MyMessBean，保存到session对象中
	 * @param request
	 * @param userName
	 * @return
	 */
	public String myMessage(HttpServletRequest request,String userName);
	
	/**
	 * 更新个人信息
	 * @param request
	 * @param userName
	 * @param name
	 * @param sex
	 * @param birth
	 * @param nation
	 * @param edu
	 * @param work
	 * @param phone
	 * @param place
	 * @param email
	 * @return
	 */
	 public String updateMess(HttpServletRequest request,String userName,String name,String sex,String birth,String nation,String edu,String work,String phone,String place,String email);
	 

	    
	 /**
	  * 修改用户密码
	  * @param request
	  * @param userName
	  * @param password
	  * @return
	  */
	 public String updatePass(HttpServletRequest request,String userName,String password);
	 	

		/**
		 * 返回登录用户的用户名
		 * @param request
		 * @return
		 */
		public String returnLogin(HttpServletRequest request);
		

	 
	
	 
}
