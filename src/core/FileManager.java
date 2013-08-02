package core;

import ai.voicerecorder.dal.DAL;
import android.content.Context;

public class FileManager {
	
	Context _context;
	DAL _dal;
	

	
	public FileManager(Context context)
	{
		_context = context;
		_dal = new DAL(context);
	}
	
	public boolean AddFile(File newFile)
	{
		boolean returnValue = true;

		//TODO: Add verification code to check if the file exists in the storage as well.
		// entries to have bounds check
		long result = _dal.addFile(newFile);
		returnValue = result > 0;
		
		return returnValue;
	}

}
