package dataObject;
/**
 * @Des 我的朋友信息Bean
 * @author Evan
 *
 */
public class MyFriBean {
		//朋友姓名
	  	private String name;
	  	//朋友电话号码
	    private String phone;
	    //朋友email
	    private String email;
	    //朋友工作地点
	    private String workplace;
	    //好友住址
	    private String place;
	    //好友qq号
	    private String QQ;
	    
	    public MyFriBean(){
	    }
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public String getPhone() {
	        return phone;
	    }
	    public void setPhone(String phone) {
	        this.phone = phone;
	    }
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public String getWorkplace() {
	        return workplace;
	    }
	    public void setWorkplace(String workplace) {
	        this.workplace = workplace;
	    }
	    public String getPlace() {
	        return place;
	    }
	    public void setPlace(String place) {
	        this.place = place;
	    }
	    public String getQQ() {
	        return QQ;
	    }
	    public void setQQ(String QQ) {
	        this.QQ = QQ;
	    }
}
