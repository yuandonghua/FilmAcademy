package starpark.filmacademy.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 *@description:输入法工具类
 *@author:袁东华
 *created at 2016/7/20 0020 下午 2:02
 */
public class InputMethodUtil {
	private static InputMethodUtil mInstance = null;
	private InputMethodUtil(){}
	public static InputMethodUtil getInstance() {
		if (mInstance == null) {
			synchronized (InputMethodUtil.class){
				if (mInstance == null) {
					mInstance = new InputMethodUtil();
				}
			}

		}
		return mInstance;
	}
	/**
	 *@description:设置是否显示输入法软键盘
	 *@author:袁东华
	 *created at 2016/7/20 0020 下午 2:03
	 */
	public void setInputMethodShow(Activity activity, View view, boolean b) {
		InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean active = imm.isActive();
		if (b&&!active){
			//显示输入法
			imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
		}else{
			//隐藏输入法
			imm.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
}
