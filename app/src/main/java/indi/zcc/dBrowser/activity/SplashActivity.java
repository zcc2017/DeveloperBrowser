package indi.zcc.dBrowser.activity;

import android.os.*;
import android.view.*;
import indi.zcc.dBrowser.*;
import java.util.*;
import android.widget.*;
import android.content.*;
import android.support.design.widget.*;

public class SplashActivity extends BaseActivity
{
	private CoordinatorLayout rootLayout;
	private SharedPreferences sp;
	private static final String Count="count";
	private boolean isActivity=true;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//获取ShaerdPreferences
		sp = getSharedPreferences(StaticString.settings_Preference, MODE_WORLD_WRITEABLE);
		//初始化视图
		setContentView(R.layout.splash);
		rootLayout = (CoordinatorLayout) findViewById(R.id.splashRootLayout);
		//SnackBar
		Snackbar.make(rootLayout, getString(R.string.login_welcomeText, sp.getInt(Count, 1)), Snackbar.LENGTH_INDEFINITE)
			.setAction(getString(R.string.login_skip), new View.OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					Intent intent = new Intent(SplashActivity.this, WebActivity.class);
					startActivity(intent);
					finish();
					isActivity = false;
				}
			})
			.show();
		//配置数据
		sp.edit().putInt(Count, sp.getInt(Count, 1) + 1).commit();
		/*倒计时启动
		 延迟2S跳转*/
		Handler handler=   new Handler();
		handler.postDelayed(new Runnable()
			{
				@Override
				public void run() 
				{
					if (isActivity)
					{
						Intent intent = new Intent(SplashActivity.this, WebActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}, 2 * 1000);
	}

}
