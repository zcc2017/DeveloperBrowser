package indi.zcc.dBrowser.activity;

import indi.zcc.dBrowser.*;
import android.os.*;
import indi.zcc.dBrowser.view.*;
import android.view.*;
import android.webkit.*;
import indi.zcc.dBrowser.activity.Method.*;

public class WebActivity extends BaseActivity
{
	private DBWebView dbwv;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		initView(savedInstanceState);
		dbwv.setWebViewClient(new DeveloperBrowserWebViewClient());
		dbwv.setWebChromeClient(new DeveloperBrowserChromeClient());
	}
	
	private void initView(Bundle saveInstanceState)
	{
		//初始化视图界面
		dbwv=(DBWebView) findViewById(R.id.web_DBWebView);
		
		if(saveInstanceState!=null)
		{
			dbwv.restoreState(saveInstanceState);
		}
	}
	
	//按钮事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_BACK:
				if(dbwv.canGoBack())
				{
					dbwv.goBack();
				}
				break;
		}
		return super.onKeyDown(keyCode, event);
	}

	//程序意外终止时
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		dbwv.saveState(outState);
	}
	
	@Override
	protected void onDestroy()
	{
		//退出程序时
		WebViewMethod.releaseAllWebViewCallback();
		System.exit(0);
		super.onDestroy();
	}
	
	
	
	
	
	//WebClient帮助WebView处理各种通知、请求事件
	private class DeveloperBrowserWebViewClient extends WebViewClient
	{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			if(url.contains(getString(R.string.app_name)))
			{
				
				return true;
			}
			return true;
		}
		
	}
	
	//WebChromeClient辅助WebView处理JavaScript的对话框，网站图标，网站title，加载进度等
	private class DeveloperBrowserChromeClient extends WebChromeClient
	{
		
	}
}
