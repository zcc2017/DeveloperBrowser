package indi.zcc.dBrowser.activity.Method;

import android.support.coreui.*;
import android.webkit.*;
import java.lang.reflect.*;

public class WebViewMethod
{
	public static void releaseAllWebViewCallback()
	{
		if (android.os.Build.VERSION.SDK_INT < 16)
		{
			try
			{
				Field field = WebView.class.getDeclaredField("mWebViewCore");
				field = field.getType().getDeclaredField("mBrowserFrame");
				field = field.getType().getDeclaredField("sConfigCallback");
				field.setAccessible(true);
				field.set(null, null);
			}
			catch (NoSuchFieldException e)
			{
				if (BuildConfig.DEBUG)
				{
					e.printStackTrace();
					return;
				}
			}
			catch (IllegalAccessException e)
			{
				if (BuildConfig.DEBUG)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			try
			{
				Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
				if (sConfigCallback != null)
				{
					sConfigCallback.setAccessible(true);
					sConfigCallback.set(null, null);
				}
			}
			catch (NoSuchFieldException e)
			{
				if (BuildConfig.DEBUG)
				{
					e.printStackTrace();
				}
			}
			catch (ClassNotFoundException e)
			{
				if (BuildConfig.DEBUG)
				{
					e.printStackTrace();
				}
			}
			catch (IllegalAccessException e)
			{
				if (BuildConfig.DEBUG)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
