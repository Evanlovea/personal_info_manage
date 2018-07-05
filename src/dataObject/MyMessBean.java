package dataObject;
/**
 * @description 个人信息bean
 * @author Evan
 *
 */
public class MyMessBean {
	
		//姓名
	    private String name;
	    //性别
	    private String sex;
	    //生日
	    private String birth;
	    //籍贯
	    private String nation;
	    //教育水平
	    private String edu;
	    //工作
	    private String work;
	    //手机号
	    private String phone;
	    //用户住址
	    private String place;
	    //用户邮箱
	    private String email;
	    //构造方法
	    public MyMessBean(){
	    }
	    /**
	     * @Getter
	     * @Setter
	     *
	     */
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public String getSex() {
	        return sex;
	    }
	    public void setSex(String sex) {
	        this.sex = sex;
	    }
	    public String getBirth() {
	        return birth;
	    }
	    public void setBirth(String birth) {
	        this.birth = birth;
	    }
	    public String getNation() {
	        return nation;
	    }
	    public void setNation(String nation) {
	        this.nation = nation;
	    }
	    public String getEdu() {
	        return edu;
	    }
	    public void setEdu(String edu) {
	        this.edu = edu;
	    }
	    public String getWork() {
	        return work;
	    }
	    public void setWork(String work) {
	        this.work = work;
	    }
	    public String getPhone() {
	        return phone;
	    }
	    public void setPhone(String phone) {
	        this.phone = phone;
	    }
	    public String getPlace() {
	        return place;
	    }
	    public void setPlace(String place) {
	        this.place = place;
	    }
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }

}
