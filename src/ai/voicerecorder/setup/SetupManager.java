package ai.voicerecorder.setup;

import Configuration.UserSettings;
import ai.voicerecorder.dal.DAL;
import android.content.Context;

public class SetupManager 
{
	private UserSettings _userSettings;
	private Context _context;
	
	public SetupManager(Context context)
	{
		_context = context;
		_userSettings = new UserSettings(context);
	}
	
	public boolean Setup()
	{
		boolean returnValue = true;
		
		if(getIsSetupComplete()== false)
		{
			boolean isDBSetupSuccessful = setupDB();
			if(isDBSetupSuccessful == false)
				returnValue = false;
			

			// Update setup state if the setup was successful
			if(returnValue == true)
				_userSettings.setIsSetupComplete(true);
		}
		
		return returnValue;
	}
	
	public boolean getIsSetupComplete()
	{
		boolean isSetupComplete = _userSettings.getIsSetupComplete();
		return isSetupComplete;
	}
	
	public void setIsSetupComplete(boolean isSetupComplete)
	{
		_userSettings.setIsSetupComplete(isSetupComplete);
	}
	
	private boolean setupDB()
	{
		boolean returnValue = true;
		DAL _dal = new DAL(_context);
		
		if(_dal.createDatabaseSchema() == false)
			returnValue = false;
		
		return returnValue;
	}
}
