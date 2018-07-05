package dao;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
/**
 * 用户登录注册服务实现
 * @author Evan
 *
 */
public interface LoginDao {
	
	/**
	 * 查询登录名，密码是否存在
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 */
	public ResultSet selectLogin(HttpServletRequest request,String userName,String password);
	
	
	/**
	 * 把登录人的信息保存到session对象中
	 * @param request
	 * @param userName
	 * @return
	 */
	
	public String myLogin(HttpServletRequest request,String userName);
	
	
	/**
	 * 返回登录用户的用户名
	 * @param request
	 * @return
	 */
	public String returnLogin(HttpServletRequest request);
	
	
	/**
	 * 用户注册功能实现
	 * @param request
	 * @param userName
	 * @param password
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
	 public String insertMess(HttpServletRequest request,String userName,String password,String name,String sex,String birth,String nation,String edu,String work,String phone,String place,String email);
	 

    /**
     * 把个人信息通过MyMessBean，保存到session对象中
     * @param request
     * @param userName
     * @return
     */
	String myMessage(HttpServletRequest request, String userName);
	
	
	/**
	 * 查找个人信息并返回
	 * @param request
	 * @param userName
	 * @return
	 */
	public ResultSet selectMess(HttpServletRequest request,String userName);
}
