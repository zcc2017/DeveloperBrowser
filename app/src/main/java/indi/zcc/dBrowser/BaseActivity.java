package indi.zcc.dBrowser;


import android.os.*;
import android.view.*;
import android.widget.*;

public abstract class BaseActivity extends android.support.v7.app.AppCompatActivity
{

	@Override
	public void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
        setContentView(setLayoutViewId());//把设置布局文件的操作交给继承的子类
		
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14)
		{
            parentView.setFitsSystemWindows(true);
        }
    }

    /**
     * 返回当前Activity布局文件的id
     *
     * @return layout布局文件，如R.layout.splash
     */
    abstract protected int setLayoutViewId();
}
		
