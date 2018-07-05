package dataObject;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
/**
 * 日程信息管理bean
 * @author Evan
 *
 */
public class MyDayBean implements ServletRequestAware{
	//日程时间
    private String Day;
    //日程内容
    private String thing;
    private ResultSet rs=null;
    private HttpServletRequest request;
    public MyDayBean(){
    }
   public String getDay() {
        return Day;
    }
    public void setDay(String Day) {
        this.Day = Day;
    }
    public String getThing() {
        return thing;
    }
    public void setThing(String thing) {
        this.thing = thing;
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
}