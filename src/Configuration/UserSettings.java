package Configuration;

import Configuration.Config.RecorderEncodingFormat;
import Configuration.Config.RecorderEncodingQuality;
import android.content.Context;
import android.content.SharedPreferences;

public class UserSettings
{
	public static String AppPreferenceName = "VoiceRecorderPreferences3";
	
	// PREFERENCES KEYS
	private final static String KeyIsSetupComplete = "IsSetupComplete"; 
	
	private static RecorderEncodingFormat  _EncodingFormat;
	private static RecorderEncodingQuality  _Encodinguality;
	private Context _context;
	SharedPreferences _settings; 	
	
	public void setEncodingFormat(RecorderEncodingFormat EncodingFormat) 
	{
		_EncodingFormat = EncodingFormat;
	}

	public void setEncodinguality(RecorderEncodingQuality Encodinguality) 
	{
		_Encodinguality = Encodinguality;
	}
	
	public UserSettings(Context context)
	{
		_context = context;
		_settings = _context.getSharedPreferences(AppPreferenceName, Context.MODE_PRIVATE);		
	}
	
	
	public boolean getIsSetupComplete()
	{
		boolean returnValue;

		returnValue = _settings.getBoolean(KeyIsSetupComplete, false) ;
		
		return returnValue;
	}
	
	public void setIsSetupComplete(boolean isSetupComplete)
	{
		SharedPreferences.Editor editor = _settings.edit();
		editor.putBoolean(KeyIsSetupComplete, isSetupComplete);
		editor.commit();
		
	}

}