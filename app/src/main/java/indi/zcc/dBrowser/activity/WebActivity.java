package indi.zcc.dBrowser.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import indi.zcc.dBrowser.BaseActivity;
import indi.zcc.dBrowser.R;
import indi.zcc.dBrowser.utils.WebUtils;
import indi.zcc.dBrowser.view.DBWebView;
import android.widget.*;

public class WebActivity extends BaseActivity
{
	private DBWebView dbwv;//主视图
	private TextView textRes;
	private EditText editTextUrl;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		InitViewAndApplyDefaultSettings(savedInstanceState);
		dbwv.setWebViewClient(new DeveloperBrowserWebViewClient());
		dbwv.setWebChromeClient(new DeveloperBrowserChromeClient());
	}

	private void InitViewAndApplyDefaultSettings(Bundle saveInstanceState)
	{
		//初始化视图界面
		dbwv = (DBWebView) findViewById(R.id.webDBWebView);
		textRes = (TextView) findViewById(R.id.webTextResource);
		editTextUrl=(EditText) findViewById(R.id.webEditSearch);
		//完成一些常规配置
		dbwv.getSettings().setJavaScriptEnabled(true);//支持JavaScript

		if (saveInstanceState != null)
		{
			dbwv.restoreState(saveInstanceState);
		}
		else
		{
			dbwv.loadUrl("http://m.baidu.com");
		}
	}

	//按钮事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_BACK:
				if (dbwv.canGoBack())
				{
					dbwv.goBack();
				}
				else
				{
					System.exit(0);
				}
				break;
		}
		return true;
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
		WebUtils.releaseAllWebViewCallback();
		System.exit(0);
		super.onDestroy();
	}

	@Override
	protected int setLayoutViewId()
	{
		return R.layout.web;
	}






	//WebClient帮助WebView处理各种通知、请求事件
	private class DeveloperBrowserWebViewClient extends WebViewClient
	{
		/*每次加载网页都会通过这个方法，所以通过这个方法可以实现过滤网页的功能*/
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			if (url.contains(getString(R.string.app_name)))
			{

			}
			if (url.startsWith("mailto:")  || url.startsWith("geo:") || url.startsWith("tel:"))
			{  
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri  .parse(url));  
				startActivity(intent);  
				return false;  
			}  
			return false;
		}
		//加载资源时
		public void onLoadResource(android.webkit.WebView view, java.lang.String url)
        {
            textRes.setText(getString(R.string.web_textResLoading, url));
        }


	}

	//WebChromeClient辅助WebView处理JavaScript的对话框，网站图标，网站title，加载进度等
	private class DeveloperBrowserChromeClient extends WebChromeClient
	{

	}
}
