package controller.friendManagerController;


import com.opensymphony.xwork2.ActionSupport;

import dao.FriendManagerDao;
import dao.LoginDao;
import dao.impl.FriendManagerDaoImpl;
import dao.impl.LoginDaoImpl;


import javax.servlet.http.HttpServletRequest;
//import javax.swing.JOptionPane;
import org.apache.struts2.interceptor.ServletRequestAware;

public class DeleteFriAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message=ERROR;
    private String userName;
    private String name;
    private HttpServletRequest request;
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public String execute() throws Exception {
       LoginDao loginDao=new LoginDaoImpl();
       FriendManagerDao friendManagerDao=new FriendManagerDaoImpl();
        userName=loginDao.returnLogin(request);
        
        name=friendManagerDao.returnFri(request);
        String del=friendManagerDao.deleteFri(request, userName, name);
        if(del.equals("ok")){
            message=SUCCESS;
        }
        return message;
    }


}
