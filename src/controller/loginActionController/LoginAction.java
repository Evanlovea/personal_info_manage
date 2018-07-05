package controller.loginActionController;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import dao.AddInfoToList;
import dao.LoginDao;
import dao.impl.AddInfoToListImpl;
import dao.impl.LoginDaoImpl;

public class LoginAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 登录业务逻辑实现
	 */
	private final static Logger logger=LoggerFactory.getLogger(LoginAction.class);
	private static final long serialVersionUID = 1L;
	private String userName;
    private String password;
    private ResultSet rs=null;
    private String message=ERROR;
    private HttpServletRequest request;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public void validate(){
        if(this.getUserName()==null||this.getUserName().length()==0){
            addFieldError("username","请输入登录名字！");
        }else{
            try{
               LoginDao loginDao=new LoginDaoImpl();
                rs=loginDao.selectMess(request, this.getUserName());
                logger.info("rs的值"+rs.toString());
                if(!rs.next()){
                    addFieldError("username","此用户尚未注册！");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(this.getPassword()==null||this.getPassword().length()==0){
            addFieldError("password","请输入登录密码！");
        }else{
            try{
            	 LoginDao loginDao=new LoginDaoImpl();
                 rs=loginDao.selectMess(request, this.getUserName());
                if(rs.next()){
                    rs=loginDao.selectLogin(request, this.getUserName(), this.getPassword());
                    if(!rs.next()){
                        addFieldError("password","登录密码错误！");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
     public String execute() throws Exception {
    	
        AddInfoToList addInfoToList=new AddInfoToListImpl();
        String add=addInfoToList.addList(request, this.getUserName());
        logger.info(add);
        if(add.equals("ok")){
            message=SUCCESS;
        }else {
			message=ERROR;
		}
        return message;
    }
//    public void message(String msg){
//        int type=JOptionPane.YES_NO_OPTION;
//        String title="信息提示";
//        JOptionPane.showMessageDialog(null,msg,title,type);
//    }
}
