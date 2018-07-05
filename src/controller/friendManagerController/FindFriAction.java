package controller.friendManagerController;


import com.opensymphony.xwork2.ActionSupport;

import dao.FriendManagerDao;
import dao.LoginDao;
import dao.impl.FriendManagerDaoImpl;
import dao.impl.LoginDaoImpl;


import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import org.apache.struts2.interceptor.ServletRequestAware;

public class FindFriAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 查询联系人
	 */
	private static final long serialVersionUID = 1L;
	private String friendname;
    private String userName;
    private ResultSet rs=null;
    private String message=ERROR;
    private HttpServletRequest request;
    public String getFriendname() {
        return friendname;
    }
    public void setFriendname(String friendname) {
        this.friendname = friendname;
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
        if(this.getFriendname().equals("")||this.getFriendname().length()==0){
            message("联系人姓名不允许为空！");
            addFieldError("friendname","联系人姓名不允许为空！");
        }else{
            try{
                LoginDao loginDao=new LoginDaoImpl();
                FriendManagerDao friendManagerDao=new FriendManagerDaoImpl();
                userName=loginDao.returnLogin(request);
                rs=friendManagerDao.selectFri(request, userName, this.getFriendname());
                if(!rs.next()){
                    message("联系人姓名不存在！");
                    addFieldError("friendname","联系人姓名不存在！");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
   public String execute() throws Exception {
	   LoginDao loginDao=new LoginDaoImpl();
       FriendManagerDao friendManagerDao=new FriendManagerDaoImpl();
        userName=loginDao.returnLogin(request);
        String fri=friendManagerDao.findFri(request, userName, this.getFriendname());
        if(fri.equals("ok")){
            message=SUCCESS;
        }
        return message;
    }
}
