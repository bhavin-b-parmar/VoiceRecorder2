package com.example.voicerecorder2;

import java.util.ArrayList;

import core.File;

import ai.voicerecorder.dal.DAL;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class filelist extends Activity
{
	ArrayList<String> allfiles = new ArrayList<String>();
	ArrayList<File>  allFiles = new ArrayList<File>();
	ArrayAdapter<String> myadpt;
	DAL dal;

@Override
protected void onCreate(Bundle savedInstanceState)
{
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.filelist);
	ListView listview = (ListView) findViewById(R.id.listView_allfiles);
	myadpt = new ArrayAdapter<String>(getApplicationContext(),
			R.layout.recordinglayout);
	
	listview.setAdapter(new filelistBaseAdapter(allfiles, this,
			getApplicationContext()));
	
	//Get the Number of Files
	dal = new DAL(getApplicationContext());
	allFiles = dal.getAllFiles();
	
	//Put All file to ArrayList
	for(int i=0;i<allFiles.size();i++)
		
	{
	
		String categoryFile = allFiles.get(i).getName();
		allfiles.add(categoryFile);
		
	}
	
}

}
