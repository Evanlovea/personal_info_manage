package controller.fileManagerController;


import com.opensymphony.xwork2.ActionSupport;

import dao.FileManagerDao;
import dao.LoginDao;
import dao.impl.FileManagerDaoImpl;
import dao.impl.LoginDaoImpl;


import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

public class DownFileAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message=ERROR;
    private HttpServletRequest request;
    public String getDownloadFileName() {
    	 LoginDao loginDao=new LoginDaoImpl();
         FileManagerDao fileManagerDao=new FileManagerDaoImpl();
       String downFileName = fileManagerDao.returnFile(request, "fileName");
       try {
        downFileName = new String(downFileName.getBytes(), "utf-8");
       } catch (Exception e) {
        e.printStackTrace();
       }
       return downFileName;
    }
    public InputStream getInputStream() throws Exception{
    	FileManagerDao fileManagerDao=new FileManagerDaoImpl();
        String downFileName = fileManagerDao.returnFile(request,"fileName");
        String path="/myFiles/"+downFileName;
        return ServletActionContext.getServletContext().getResourceAsStream(path);
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public String execute() throws Exception{
        message=SUCCESS;
        return message;
    }
}
