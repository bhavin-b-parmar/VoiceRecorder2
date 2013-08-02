package com.example.voicerecorder2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class allTabsJava extends TabActivity

{

	 @SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	 @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	 
	   protected void onCreate(Bundle savedInstanceState) 
	 
	{
		 
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alltabs);
		
		ActionBar ab = getActionBar();
		ab.show();
		ab.setIcon(R.drawable.next);
		ab.getDisplayOptions();
		ab.setTitle("VoiceRecorder");
		ab.setHomeButtonEnabled(true);
		Resources objrec = getResources();
		TabHost objtab = getTabHost();
		
		Intent obj = new Intent().setClass(this,filelist.class);
		TabSpec tabspecall = objtab
	   .newTabSpec("All")
	   .setIndicator("All", objrec.getDrawable(R.drawable.m1))
	   .setContent(obj);
	   objtab.addTab(tabspecall);
	   
	   Intent obj_int2 = new Intent().setClass(this,categorylist.class);
	   TabSpec tabspec_cat = objtab
	   .newTabSpec("Category")
	   .setIndicator("Category", objrec.getDrawable(R.drawable.m1))
	   .setContent(obj_int2);
	   objtab.addTab(tabspec_cat);
	   
	   
	   Intent tag_int3 = new Intent().setClass(this,taglist.class);
	   TabSpec tabspec_tag = objtab
	   .newTabSpec("Tags")
	   .setIndicator("Tags", objrec.getDrawable(R.drawable.m1))
	   .setContent(tag_int3);
	   objtab.addTab(tabspec_tag);
		
	}
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) 
	 {
		 MenuInflater menuInflater = getMenuInflater();
		 menuInflater.inflate(R.menu.main, menu);

		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		  {
	      	case (R.id.item1):
	      		startActivity();
			
			break;

		default:
			break;
		}
		   
		

			return super.onOptionsItemSelected(item);
	}
	private void startActivity()
	{
		Intent intObj = new Intent(getApplicationContext(), recordingActivity.class);
		startActivity(intObj);
		
	}
}
