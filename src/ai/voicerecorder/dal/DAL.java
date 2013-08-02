package ai.voicerecorder.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import Configuration.Config;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import core.File;
import core.Tag;

public class DAL {
	
	static SQLiteHelper _sqliteObj;	
	private static final CursorFactory factory = null;
	Context _context;
	
	static HashMap<String, Object> IndexOfTableFile;

	
	//COMPLETE
	public boolean createDatabaseSchema() {
		boolean returnValue = true;
		
		try {
			_sqliteObj.createTable(Config.sqlCreateTableFile);
			_sqliteObj.createTable(Config.sqlCreateTableFileTags);
			_sqliteObj.createTable(Config.sqlCreateTableTags);
			//TODO: Uncomment this once you have setup manager
			populateInitialStaticData();

		} catch (Exception e)
		{
			returnValue = false;
		}
		return returnValue;
	}
	
	//COMPLETE
	private boolean populateInitialStaticData()
	{
		boolean returnValue = true; 
		
		// Populate standard Categories
		String[] categories = Config.PredefinedCategories;

		for (int i = 0; i < categories.length; i++) 
		{
			Tag newCategory = Tag.createNewCategory(categories[i], _context);
			if(newCategory == null)
				returnValue = false;
		}
		
		return returnValue;
	}

	//COMPLETE
	public DAL(Context context) {
		_context = context;
		_sqliteObj = new SQLiteHelper(context, Config.DBName, factory, Config.DBVersion);

	}


	//CODING COMPLETE
	public long addFile(File newFile) 
	{
		long returnValue = -1;
		ContentValues cnFile = new ContentValues();
		
		cnFile.put(Config.TableFile_FileID, newFile.getFileID());
		cnFile.put(Config.TableFile_Name, newFile.getName());
		cnFile.put(Config.TableFile_Location, newFile.getLocation());
		cnFile.put(Config.TableFile_IsDeleted, newFile.getIsdeleted());
		cnFile.put(Config.TableFile_DateCreated, newFile.getDateCreated().toString());
		cnFile.put(Config.TableFile_Size, newFile.getSize());
		

		try 
		{
			returnValue = _sqliteObj.Insert(Config.TableFile, cnFile);
			addFileTagsAndCategory(newFile);
			// for each tag, insert into FileTagList
		} catch (Exception e) {
			returnValue = -1;
		}
		return returnValue;
	}
	
	// This method will process file tags. If it is a unique tag, it creates a new entry for it in tags table
	// It adds mapping of file and tags in FileTags table
	//CODING COMPLETE
	private void addFileTagsAndCategory(File file)
	{
		// Process file tags
		for (Iterator<Tag> iterator = file.getTags().iterator(); iterator.hasNext();) 
		{
			
			Tag tagToAdd = (Tag)iterator.next();
			// If Tag ID is empty, it is a new tag
			// If Tag ID is not empty, it is existing tag. Just link to existing tag ID.
			processTag(file.getFileID(), tagToAdd);
		}
		
		// Process file category
		processTag(file.getFileID(), file.getCategory());
	}
	
	private void processTag(String fileId, Tag tag)
	{
		String tagId = tag.getTagId();
		if(tagId.isEmpty())
		{
			Tag newTag = Tag.createTagObject(UUID.randomUUID().toString());
			newTag.setTag(tag.getTag());
			newTag.setIsCategory(tag.getIsCategory());
			addTag(newTag);
			insertIntoFileTagTable(fileId, newTag.getTagId());
		}
		else
		{
			insertIntoFileTagTable(fileId, tag.getTagId());
		}		
	}
	
	
	private boolean insertIntoFileTagTable(String fileId, String tagId)
	{
		boolean returnValue = true;
		
		ContentValues fileTagsData = new ContentValues();
		fileTagsData.put(Config.TableFileTags_UID, UUID.randomUUID().toString());
		fileTagsData.put(Config.TableFileTags_FileID, fileId);
		fileTagsData.put(Config.TableFileTags_TagID,tagId);
		try
		{
			_sqliteObj.Insert(Config.TableFileTags, fileTagsData);
		}
		catch(Exception e)
		{
			returnValue = false;
		}
		
		return returnValue;
	}
	
	public Tag getTagByName(String tagName)
	{
		
		Tag returnValue = null; 
		
		String queryToRun = Config.sqlGetTagByBaseQuery + " AND " + Config.TableTags_Tag + " = " + tagName; 
		
		//pass tagName to query
		ArrayList<Tag> tags = getTagData(queryToRun);
		
		if(tags.size()>0)
			returnValue = tags.get(0);
		
		return returnValue;
	}
	
	public Tag getCategoryByName(String categoryName)
	{
		
		Tag returnValue = null; 
		
		String queryToRun = Config.sqlGetCategoryByBaseQuery + " AND " + Config.TableTags_Tag + " = " + categoryName; 
		
		//pass tagName to query
		ArrayList<Tag> tags = getTagData(queryToRun);
		
		if(tags.size()>0)
			returnValue = tags.get(0);
		
		return returnValue;
	}
	
	public Tag getCategoryByFileId(String fileId)
	{
		Tag returnValue = null; 
		
		String queryToRun = Config.sqlGetCategoryOfFile + " '" + fileId.trim() + "'";
		
		//pass tagName to query
		ArrayList<Tag> tags = getTagData(queryToRun);
		
		if(tags.size()>0)
			returnValue = tags.get(0);
		
		return returnValue;		
	}

	public ArrayList<Tag> getTagsByFileId(String fileId)
	{
		 
		
		String queryToRun = Config.sqlGetTagsOfFile + " '" + fileId.trim() + "'";
		
		//pass tagName to query
		ArrayList<Tag> tags = getTagData(queryToRun);
		
		return tags;		
	}
	
	// ------------------------------------------------------------------------------------------------------------------
	// TODO: Needs testing, do it with planned feature
	//INCOMPLETE
	public long updateFile(File fileToUpdate)

	{
		long returnValue = -1;

		ContentValues cnfileup = new ContentValues();

		cnfileup.put(Config.TableFile_Name, fileToUpdate.getName());
		cnfileup.put(Config.TableFile_Location, fileToUpdate.getLocation());
		cnfileup.put(Config.TableFile_IsDeleted, fileToUpdate.getIsdeleted());
		cnfileup.put(Config.TableFile_DateCreated, fileToUpdate.getDateCreated().toString());
		cnfileup.put(Config.TableFile_Size, fileToUpdate.getSize());

		try {
			returnValue = _sqliteObj.Update(Config.TableFile, cnfileup, Config.TableFile_FileID + "?", new String[] { fileToUpdate.getFileID() });
		} catch (Exception e) {
			returnValue = -1;
		}

		return returnValue;

	}

	// ------------------------------------------------------------------------------------------------------------------------------

	
	public long deleteFile(File fileToDelete)

	{
		long returnValue = -1;
		
		try {
			returnValue = _sqliteObj.Delete(Config.TableFile, Config.TableFile_FileID + "?",new String[] { fileToDelete.getFileID() });
		} catch (Exception e) {
			returnValue = -1;
		}

		return returnValue;

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------
	//INCOMPLETE
	public ArrayList<File> getAllFiles()
	{
		return getFileData(Config.sqlSelectAllFiles);

	}
	
	private ArrayList<File> getFileData(String sqlQueryToRun)
	{
		ArrayList<File> allFiles = new ArrayList<File>();

		Cursor cursor = null;
		
		cursor = _sqliteObj.Select(sqlQueryToRun);
		
		// Initialize index table
		if(IndexOfTableFile == null)
		{
			IndexOfTableFile = new HashMap<String,Object>(); 
			IndexOfTableFile.put(Config.TableFile_DateCreated,cursor.getColumnIndex(Config.TableFile_DateCreated) );
			IndexOfTableFile.put(Config.TableFile_FileID, cursor.getColumnIndex(Config.TableFile_FileID));
			IndexOfTableFile.put(Config.TableFile_IsDeleted,cursor.getColumnIndex(Config.TableFile_IsDeleted) );
			IndexOfTableFile.put(Config.TableFile_Location,cursor.getColumnIndex(Config.TableFile_Location) );
			IndexOfTableFile.put(Config.TableFile_Name, cursor.getColumnIndex(Config.TableFile_Name));
			IndexOfTableFile.put(Config.TableFile_Size,cursor.getColumnIndex(Config.TableFile_Size) );
		}		
		
		if (cursor.moveToFirst()) {
			do {
				String fileId = cursor.getString((Integer)IndexOfTableFile.get(Config.TableFile_FileID));
				File newFile = File.createNewFile(fileId);
				newFile.setDatecreated(cursor.getString((Integer) IndexOfTableFile.get(Config.TableFile_DateCreated)));
				newFile.setIsdeleted(cursor.getInt((Integer)IndexOfTableFile.get(Config.TableFile_DateCreated)));
				newFile.setLocation(cursor.getString((Integer) IndexOfTableFile.get(Config.TableFile_DateCreated)));
				newFile.setName(cursor.getString((Integer) IndexOfTableFile.get(Config.TableFile_DateCreated)));
				newFile.setsize(cursor.getInt((Integer) IndexOfTableFile.get(Config.TableFile_DateCreated)));
				
				//TODO: Set Category
				newFile.setCategory(getCategoryByFileId(fileId));
				
				//TODO: Set Tags
				ArrayList<Tag> tags = getTagsByFileId(fileId);
				for(int i=0; i<tags.size(); i++)
			{
				newFile.addTag(tags.get(i));
			}
				
				
				//allFiles.add(crAllFils.getString(3));
				allFiles.add(newFile);
				//((List<File>) allFiles).add(newFile);
				// ((List<File>) allFiles).add(crAllFils.getString(2));

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {

			cursor.close();
		}


		return allFiles;		
	}

	//COMPLETE
	public long addTag(core.Tag newTags) {

		long returnValue = -1;

		ContentValues cnTags = new ContentValues();
		cnTags.put(Config.TableTags_TagId, newTags.getTagId());
		cnTags.put(Config.TableTags_Tag, newTags.getTag());
		cnTags.put(Config.TableTags_IsCategory, newTags.getIsCategory());
		cnTags.put(Config.TableTags_NumOfRecords, newTags.getNoOfRecords());

		try {
			returnValue = _sqliteObj.Insert(Config.TableTags, cnTags);
		} catch (Exception e) {
			returnValue = -1;
		}

		return returnValue;
	}


	public long updateTags(core.Tag tagToUpdate)
	{
		long returnValue = -1;

		ContentValues cnTagseup = new ContentValues();
		cnTagseup.put(Config.TableTags_Tag, tagToUpdate.getTag());
		cnTagseup.put(Config.TableTags_IsCategory, tagToUpdate.getIsCategory());
		cnTagseup.put(Config.TableTags_NumOfRecords, tagToUpdate.getNoOfRecords());

		try {
			returnValue = _sqliteObj.Update(Config.TableTags, cnTagseup, Config.TableTags_TagId + "=?",
					new String[] { tagToUpdate.getTagId() });
		} catch (Exception e) {
			returnValue = -1;
		}

		return returnValue;

	}

	// CODED
	public long deleteTag(core.Tag tagToDelete)
	{
		long returnValue = -1;
		try {
			returnValue = _sqliteObj.Delete(Config.TableTags, Config.TableTags_TagId + "=?",
					new String[] { tagToDelete.getTagId() });

		} catch (Exception e) {
			returnValue = -1;
		}
		return returnValue;
	}



	// CODED
	public ArrayList<Tag> getallTags() 
	{
		return getTagData(Config.sqlSelectAllTags);
	}
	
	// CODED
	public ArrayList<Tag> getAllCategories()
	{
		return getTagData(Config.sqlSelectAllCategories);
	}
	
	// CODED
	private ArrayList<Tag> getTagData(String queryToRun)
	{
		ArrayList<Tag> tags = new ArrayList<Tag>();;
		
		Cursor cursor = null;
		
		//pass tagName to query
		
		cursor = _sqliteObj.Select(queryToRun);
		
		
		// Get indices of all fields
		int indexTagId = cursor.getColumnIndex(Config.TableTags_TagId);
		int indexTagName = cursor.getColumnIndex(Config.TableTags_Tag);
		int indexNoOfRecords = cursor.getColumnIndex(Config.TableTags_NumOfRecords);
		int indexIsCategory = cursor.getColumnIndex(Config.TableTags_IsCategory);
		
		// IF Category IS FOUND
		if (cursor.moveToFirst()) 
		{
			do 				
			{
				Tag newTag = Tag.createTagObject(cursor.getString(indexTagId));
				newTag.setTag(cursor.getString(indexTagName));
				newTag.setNoOfRecords(cursor.getInt(indexNoOfRecords));
				newTag.setIsCategory((cursor.getInt(indexIsCategory)>0));
				tags.add(newTag);
			} while (cursor.moveToNext());
		}

		
		return tags;		
	}
	
	
}
