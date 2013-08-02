package com.example.voicerecorder2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ai.voicerecorder.dal.DAL;
import ai.voicerecorder.setup.SetupManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import core.File;
import core.FileManager;
import core.Tag;

public class MainActivity extends Activity {

	EditText txtFileName;
	EditText txtTagName;
	ListView lstTagList;
	Spinner lstCategory;
	TextView txtTestResult;
	Button btnSave;
	Button btnAddCategory;
	DAL dal;

	List<String> categories ;
	ArrayList<String> tagList = null;

	// DATA
	ArrayList<Tag> allCategories;
	ArrayAdapter<String> dataAdapter1 = null;
	ArrayAdapter<String> dataAdapter2 = null;
	protected void onCreate(Bundle savedInstanceState)

	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Setup application
		SetupManager setupManager = new SetupManager(getApplicationContext());
		boolean setupResult = setupManager.Setup();
		
		

		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		txtFileName = (EditText) findViewById(R.id.editTextFileName);
		txtTagName = (EditText) findViewById(R.id.tagName);
		lstTagList = (ListView) findViewById(R.id.TagList);
		lstCategory = (Spinner) findViewById(R.id.sprCategory);
		txtTestResult = (TextView) findViewById(R.id.testResult);
		btnSave = (Button) findViewById(R.id.button_addFil);
	

		// Populate category list
		dal = new DAL(getApplicationContext());
		allCategories = dal.getAllCategories();
		categories = new ArrayList<String>();
		tagList = new ArrayList<String>();

		for(int i=0; i<allCategories.size(); i++)
		{
			String tag = allCategories.get(i).getTag();
			categories.add(tag);
		}
		dataAdapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, categories);
		((ArrayAdapter<String>) dataAdapter1)
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lstCategory.setAdapter(dataAdapter1);

		// Bind to list of tags, these tags will be attached to the new file object
		dataAdapter2 = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, tagList);
		lstTagList.setAdapter(dataAdapter2);
		txtTagName.setOnKeyListener(new View.OnKeyListener() 
		{
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
				if (event.getAction() == KeyEvent.ACTION_DOWN)
					if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
							|| (keyCode == KeyEvent.KEYCODE_ENTER)) {
						// String TagName = txtTagName.getText().toString();
						tagList.add(0, txtTagName.getText().toString());
						dataAdapter2.notifyDataSetChanged();
						txtTagName.setText("");
						return true;
					}
				return false;
			}
		});
		btnSave.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// File ID, IsDeleted are auto populated
				File newFile = File.createNewFile();
				
				// Retrieve Category Object and set it on new file
				int categoryIndex= lstCategory.getSelectedItemPosition();
				Tag category = allCategories.get(categoryIndex);
				newFile.setCategory(category);

				//Set creation date to today
				Date d = new Date();
			    String strDate  = (String) DateFormat.format("MMMM d, yyyy ", d.getDate());
				newFile.setDatecreated(strDate);
				Calendar cal = Calendar.getInstance();
				int date = cal.get(Calendar.DATE);
				newFile.setLocation("SD/Recordings/Record"+ date);
				
				//Set file name
				String setFileName=txtFileName.getText().toString();
				newFile.setName(setFileName);
				
				// Set size
				newFile.setsize(cal.get(Calendar.SECOND));
				
				// Set Tags
				int numOfTags = tagList.size();
				
				for(int i = 0; i< numOfTags; i++)
				{
					newFile.addTag(tagList.get(i));
				}
				
				FileManager fileManager = new FileManager(getApplicationContext());
				fileManager.AddFile(newFile);				
			}
		});
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}
}
