package com.example.voicerecorder2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class filelistBaseAdapter extends BaseAdapter
{
	MediaPlayer mp = new MediaPlayer();
	 int play_pause = 0;
	Activity activity;
	Context context;
	ArrayList<String> list;
	LayoutInflater inflater;

	 String clipName;
	
	 public filelistBaseAdapter(ArrayList<String> list,Activity activity, Context context)
	 {
		    this.activity = activity;
			this.context = context;
			this.list =list;
			inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	 }
	 
	@Override
	public int getCount() 
	{
		// getting the size of categoryList
		return list.size();
	}

	@Override
	public Object getItem(int position) 
	{
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView;
		if(convertView==null)
		view=inflater.inflate(R.layout.filelistlayout,null);
		
		//Getting All Controls of FileListLayout
		TextView categoryName = (TextView)view.findViewById(R.id.textView_categoryName);
//		//TextView catfam =(TextView)view.findViewById(R.id.categry_fmly);
//		//final ImageView playbtn = (ImageView)view.findViewById(R.id.imageButton1);
		categoryName.setText(list.get(position)) ;

		return view;
	}

}
