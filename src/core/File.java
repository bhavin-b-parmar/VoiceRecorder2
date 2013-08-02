package core;

import java.util.ArrayList;
import java.util.UUID;

import ai.voicerecorder.dal.DAL;
import android.content.Context;

public class File
{
	static Context _context;
	static DAL _dbhelper;;

	// Properties
	String _FileID;
	String _Name;
	String _location;
	String _DateCreated;
	int _IsDeleted;
	int _size;
	ArrayList<Tag> _tags;
	Tag _category;
	
private File(String fileId)
{	
	// Validate it is a valid UUID
	 UUID validateUUID = UUID.fromString(fileId);
	_FileID = validateUUID.toString();
	_IsDeleted = 0;
	initializeMembers();
}

private File()
{
	this(UUID.randomUUID().toString());
}

private void initializeMembers()
{
	_IsDeleted = 0;
	_tags = new ArrayList<Tag>();
}

public static File createNewFile()
{
	File newFile = new File();
	
	return newFile;
}

public static File createNewFile(String UUIDInString)
{
	File newFile = new File(UUIDInString);
	return newFile;
}
	public String getFileID()
	
	{
		return _FileID;
	}

	public void setName(String Name) 
	{
		_Name = Name;
	}

	public String getName() 
	{
		return _Name;

	}

	public void setLocation(String Location) 
	{
		_location = Location;
	}

	public String getLocation() 
	{
		return _location;
	}
	public void setIsdeleted(int i)
	{
		_IsDeleted = i;
		
	}
	public int getIsdeleted()
	{
		return _IsDeleted;
	}
	public void setDatecreated(String date)
	{
		_DateCreated = date;
	}
	public String getDateCreated()
	{
		return _DateCreated;
	}
	public void setsize(int size)
	{
		_size = size;
	}
	public int getSize()
	{
		return _size;
	}
	
	public ArrayList<Tag> getTags()
	{
		return _tags;
	}
	
	public void addTag(Tag tag)
	{
		_tags.add(tag);
	}
	
	public void addTag(String tag)
	{
		Tag newTag = Tag.createTagObject();
		newTag.setTag(tag);
		_tags.add(newTag);
	}

	public boolean deleteTag(Tag tagToDelete)
	{
		return _tags.remove(tagToDelete);
	}
	
	public Tag getCategory()
	{
		return _category;
	}
	
	public void setCategory(Tag categoryToSet)
	{
		if(categoryToSet != null)
			_category = categoryToSet;
	}
	

}
