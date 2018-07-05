package dao;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
/**
 * 文件管理服务实现
 * @author Evan
 *
 */
public interface FileManagerDao {
	
	 
	
	/**
	 * 保存上传文件的信息
	 * @param request
	 * @param userName
	 * @param title
	 * @param name
	 * @param contentType
	 * @param size
	 * @param filePath
	 * @return
	 */
    public String insertFile(HttpServletRequest request,String userName,String title,String name,String contentType,String size,String filePath);
    

	 /**
	  * 删除文件
	  * @param request
	  * @param userName
	  * @param title
	  * @return
	  */
	 public String deleteFile(HttpServletRequest request,String userName,String title);
	 
	
	 /**
	  * 修改文件
	  * @param request
	  * @param userName
	  * @param Title
	  * @param title
	  * @param name
	  * @param contentType
	  * @param size
	  * @param filePath
	  * @return
	  */
	public String updateFile(HttpServletRequest request,String userName,String Title,String title,String name,String contentType,String size,String filePath);
	
	
	/**
	 *  查询文件
	 * @param request
	 * @param userName
	 * @param type
	 * @param name
	 * @return
	 */
    public ResultSet selectFile(HttpServletRequest request,String userName,String type,String name);
    
  
    /**
     * 查询所有的文件信息
     * @param request
     * @param userName
     * @return
     */
    public ResultSet selectFileAll(HttpServletRequest request,String userName);
    
    
    /**
     * 查询所有的文件信息，并把他们保存到session对象中
     * @param request
     * @param userName
     * @return
     */
    public String myFile(HttpServletRequest request,String userName);
    
    
    /**
     * 查找文件信息，并把文件的信息保存到session对象中
     * @param request
     * @param userName
     * @param title
     * @return
     */
    public String findFile(HttpServletRequest request,String userName,String title);
    
    
    /**
     * 根据不同的条件，从查找到的文件session对象中获取文件相应的文件信息
     * @param request
     * @param face
     * @return
     */
    public String returnFile(HttpServletRequest request,String face);
}
