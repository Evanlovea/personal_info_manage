package dao;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public interface DateTimeManagerDao {
	
	/**
	 * 查找日程，并把日程信息保存到session对象中
	 * @param request
	 * @param userName
	 * @param date
	 * @return
	 */
    public String findDay(HttpServletRequest request,String userName,String date);
    

    /**
     * 从查找到的日程session中获取日程信息，并返回
     * @param request
     * @return
     */
    public String returnDay(HttpServletRequest request);

	
	 /**
	  * 查询日程
	  * @param request
	  * @param userName
	  * @param date
	  * @return
	  */
	 public ResultSet selectDay(HttpServletRequest request,String userName,String date);
	 
	
	 /**
	  * 查询所有的日程信息，并把他们保存到session对象中
	  * @param request
	  * @param userName
	  * @return
	  */
	 public String myDayTime(HttpServletRequest request,String userName);
	
	 
	 /**
	  * 添加日程
	  * @param request
	  * @param userName
	  * @param date
	  * @param thing
	  * @return
	  */
	 public String insertDay(HttpServletRequest request,String userName,String date,String thing);
	 
	 
	/**
	  * 删除日程
	  * @param request
	  * @param userName
	  * @param date
	  * @return
	  */
	 public String deleteDay(HttpServletRequest request,String userName,String date);
	 
	 
	 
	 /**
	  * 修改日程
	  * @param request
	  * @param userName
	  * @param Day
	  * @param date
	  * @param thing
	  * @return
	  */
	 public String updateDay(HttpServletRequest request,String userName,String Day,String date,String thing);
	 
	 
	 /**
	  * 查询所有日程信息
	  * @param request
	  * @param userName
	  * @return
	  */
	 public ResultSet selectDayAll(HttpServletRequest request,String userName);

	
}
