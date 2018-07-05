package controller.fileManagerController;


import com.opensymphony.xwork2.ActionSupport;

import dao.FileManagerDao;
import dao.LoginDao;
import dao.impl.FileManagerDaoImpl;
import dao.impl.LoginDaoImpl;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class DeleteFileAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 删除文件实现
	 */
	private static final long serialVersionUID = 1L;
	private String message=ERROR;
    private String userName;
    private String title;
    private String filePath;
    private HttpServletRequest request;
    
    public String DeleteFile(String FilePath){
        try{
            String filePath=FilePath;
            filePath=filePath.toString();
            File deleFile=new File(filePath);
            deleFile.delete();
            return "ok";
        }catch(Exception e){
            return null;
        }
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public String execute() throws Exception {
     LoginDao loginDao=new LoginDaoImpl();
        userName=loginDao.returnLogin(request);
        FileManagerDao fileManagerDao=new FileManagerDaoImpl();
        title=fileManagerDao.returnFile(request,"title");
        filePath=fileManagerDao.returnFile(request, "filePath");
        String tit=fileManagerDao.deleteFile(request, userName, title);
        if(tit.equals("ok")){
            String del=DeleteFile(filePath);
            if(del.equals("ok")){
                message=SUCCESS;
            }
        }
        return message;
    }
}
