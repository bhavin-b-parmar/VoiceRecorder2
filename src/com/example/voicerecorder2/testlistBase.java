package com.example.voicerecorder2;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class testlistBase extends BaseAdapter{

	ArrayList<String> list;
	Context _context;
	Activity _activity;
	private static LayoutInflater inflater=null;
	public testlistBase(ArrayList<String> list,Activity activity,Context context) {
		// TODO Auto-generated constructor stub
		this.list=list;
		_activity=activity;
		_context=context;
		inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view=convertView;
		if(convertView==null)
		view=inflater.inflate(R.layout.testresultlayout,null);
		return null;
	}

}
