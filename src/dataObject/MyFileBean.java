package dataObject;
/**
 * 实现我的文件bean
 * @author Evan
 *
 */
public class MyFileBean {
	
	//用户登录名
	private String userName;
	//标题
    private String title;
    //文件名字
    private String name;
    //文件类型
    private String contentType;
    //文件大小
    private String size;
    //文件路径
    private String filePath;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
