package controller.personManagerController;


import com.opensymphony.xwork2.ActionSupport;

import dao.PersonManagerDao;
import dao.impl.PersonManagerDaoImpl;


import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdatePassAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 更新个人密码业务逻辑实现
	 */
	private final static Logger logger=LoggerFactory.getLogger(UpdateMessAction.class);
	private static final long serialVersionUID = 1L;
	private String password1;
    private String password2;
    private String userName;
    private HttpServletRequest request;
    private String message=ERROR;
    public String getPassword1() {
        return password1;
    }
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public void message(String msg){
        int type=JOptionPane.YES_NO_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null,msg,title,type);
    }
    public void validate(){
        if(!(password1.equals(password2))){
            message("两次密码不同！");
            addFieldError("password2","两次密码不同！");
        }
    }
    public String execute() throws Exception {
        PersonManagerDao personManagerDao=new PersonManagerDaoImpl();
        userName=personManagerDao.returnLogin(request);
        String pass=personManagerDao.updatePass(request, userName, this.getPassword1());
        if(pass.equals("ok")){
            message=SUCCESS;
        }
        return message;
    } 
}
