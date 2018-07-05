package utils;

import javax.swing.JOptionPane;

/**
 * 信息提示
 * @author Evan
 *
 */
public class MessageUtil {
	//一个带参数的信息提示框，供调试使用
	public void message(String msg){
	    int type=JOptionPane.YES_NO_OPTION;
	    String title="信息提示";
	    JOptionPane.showMessageDialog(null,msg,title,type);
	}

}
