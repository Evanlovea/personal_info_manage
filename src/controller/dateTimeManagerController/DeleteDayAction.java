package controller.dateTimeManagerController;


import com.opensymphony.xwork2.ActionSupport;

import dao.DateTimeManagerDao;
import dao.LoginDao;
import dao.impl.DateTimeManagerDaoImpl;
import dao.impl.LoginDaoImpl;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class DeleteDayAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message=ERROR;
    private String userName;
    private String day;
    private HttpServletRequest request;
        public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public String execute() throws Exception {
    	LoginDao loginDao=new LoginDaoImpl();
        userName=loginDao.returnLogin(request);
        DateTimeManagerDao dateTimeManagerDao=new DateTimeManagerDaoImpl();
        day=dateTimeManagerDao.returnDay(request);
        String dd=dateTimeManagerDao.deleteDay(request, userName, day);
        if(dd.equals("ok")){
            message=SUCCESS;
        }
        return message;
    }

    
}
