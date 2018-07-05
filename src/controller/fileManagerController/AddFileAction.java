package controller.fileManagerController;


import com.opensymphony.xwork2.ActionSupport;

import dao.FileManagerDao;

import dao.LoginDao;
import dao.impl.FileManagerDaoImpl;

import dao.impl.LoginDaoImpl;


import java.io.*;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddFileAction extends ActionSupport implements ServletRequestAware{
    /**
	 * 上传文件业务逻辑实现
	 * 文件上传到了本地tomacat
	 * D:\tomcat\apache-tomcat-9.0.6-windows-x64\apache-tomcat-9.0.6
	 */
	private final static Logger LOGGER=LoggerFactory.getLogger(AddFileAction.class);
	private static final long serialVersionUID = 1L;
	private String title;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String savePath;
    private String userName;
    private String message=ERROR;
    private HttpServletRequest request;
    private ResultSet rs;
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public File getUpload() {
        return this.upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getUploadContentType() {
        return this.uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    public String getUploadFileName() {
        return this.uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    public String getSavePath(){
        String Path=null;
        try{
        Path=ServletActionContext.getServletContext().getRealPath(this.savePath);
        }catch(Exception e){
            message("异常："+e);
            e.printStackTrace();
        }
        return Path;
    }
    public void setSavePath(String value) {
        this.savePath = value;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public String Path(String Path){
        String filePath="";
        String p="";
        StringTokenizer token=new StringTokenizer(Path,"\\");
        while(!(p.equals("myFiles"))){
            p=token.nextToken();
            filePath=filePath+p+"/";
        }
        return filePath;
    }
    public void message(String msg){
        int type=JOptionPane.YES_NO_OPTION;
        String title2="信息提示";
        JOptionPane.showMessageDialog(null,msg,title2,type);
    }
    public void validate(){
        if(this.getTitle()==null||this.getTitle().length()==0){
            addFieldError("title","文件标题不允许为空！");
        }
        if(this.getUpload()==null||this.getUpload().length()==0){
            addFieldError("upload","请选择一个文件！");
        }else{
                try{
                	LoginDao loginDao=new LoginDaoImpl();
                   FileManagerDao fileManagerDao=new FileManagerDaoImpl();
                    userName=loginDao.returnLogin(request);
                    rs=fileManagerDao.selectFile(request, userName, "title", this.getTitle());
                    if(rs.next()){
                        addFieldError("title","文件标题已存在！");
                    }else{
                        rs=fileManagerDao.selectFile(request, userName, "name", this.getUploadFileName());
                        if(rs.next()){
                            addFieldError("upload","文件名已存在！");
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
    }
    public String execute() throws Exception{
        FileOutputStream fos=new FileOutputStream(getSavePath()+"\\"+getUploadFileName());
        FileInputStream fis=new FileInputStream(getUpload());
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=fis.read(buffer))>0){
            fos.write(buffer,0,len);
        }
        fos.close();
        LoginDao loginDao=new LoginDaoImpl();
      FileManagerDao fileManagerDao=new FileManagerDaoImpl();
        userName=loginDao.returnLogin(request);
        String size=null;
        DecimalFormat dcmFmt = new DecimalFormat("0.00");
        float length=this.getUpload().length();
        if(length<1024){
            size=(dcmFmt.format(length))+"字节";
        }else if(length<1024*1024){
            size=(dcmFmt.format(length/1024))+"K";
        }else{
            size=(dcmFmt.format(length/1024*1024))+"M";
        }
        String filePath=Path(this.getSavePath())+this.getUploadFileName();
        System.out.println("dsdsadaasdasdasdasdasdsaasdassdasd");
        System.out.println(filePath);
        String file=fileManagerDao.insertFile(request, userName, this.getTitle(), this.getUploadFileName(), this.getUploadContentType(), size, filePath);
        System.out.println(file);
        LOGGER.info(file);
        if("ok".equals(file)){
            message=SUCCESS;
        }else if(file.equals("title")){
            message=INPUT;
        }else if(file.equals("name")){
            message=INPUT;
        }
        return message;
    }  
}
