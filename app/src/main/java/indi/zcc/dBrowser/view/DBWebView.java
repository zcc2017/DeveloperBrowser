package indi.zcc.dBrowser.view;

import android.annotation.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.webkit.*;

public class DBWebView extends WebView
{
	public DBWebView(Context context)
	{
		super(context);
		if (isInEditMode())
		{Init();}
	}
	public DBWebView(Context context, AttributeSet attr)
	{
		super(context, attr);
		if (isInEditMode())
		{Init();}
	}
	public DBWebView(Context context, AttributeSet attr, int defStyle)
	{
		super(context, attr, defStyle);
		if (isInEditMode())
		{Init();}
	}

	//初始化
	@SuppressLint("JavascriptInterface")
	public void Init()
	{
		
	}



}
