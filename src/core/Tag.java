package core;

import java.util.ArrayList;
import java.util.UUID;

import ai.voicerecorder.dal.DAL;
import android.content.Context;

public class Tag 
{
	String _TagId;
	String _Name;
	Boolean _IsCategory;
	int _NumOfRecords;
	
	private Tag(String tagId)
	{	
		// Validate it is a valid UUID
		
		UUID validateUUID = UUID.fromString(tagId);
		_TagId = validateUUID.toString();
		initializeMembers();
	}

	private Tag()
	{
		_TagId = "";
		initializeMembers();
	}
	
	private void initializeMembers()
	{
		_NumOfRecords = 0;
		_IsCategory = false;

	}
	
	
	public static Tag createTagObject()
	{
		Tag newTag = new Tag();
		
		return newTag;
	}

	public static Tag createTagObject(String UUIDInString)
	{
		Tag newTag = new Tag(UUIDInString);
		return newTag;
	}
	
	public static Tag createCategoryObject()
	{
		Tag newCategory = new Tag();
		newCategory.setIsCategory(true);
		return newCategory;
	}
	
	public static Tag createCategoryObject(String UUIDInString)
	{
		Tag newCategory = new Tag(UUIDInString);
		newCategory.setIsCategory(true);
		return newCategory;
	}
	
	public static Tag createNewCategory(String categoryName, Context context)
	{
		DAL dal = new DAL(context);
		Tag newCategory = createCategoryObject(UUID.randomUUID().toString());
		
		newCategory.setTag(categoryName);
		
		long result = dal.addTag(newCategory); 
		if(result > 0)		
			return newCategory;
		else
			return null;
	}

//	public static Tag createNewTag(String tagName, Context context)
//	{
//		DAL dal = new DAL(context);
//		Tag newTag = createNewTagObject();
//		newTag.setTag(tagName);
//		if(dal.addTag(newTag)>0)
//			return newTag;
//		else
//			return null;
//	}
	
	public static ArrayList<Tag> getAllTags(Context context)
	{
		DAL dal = new DAL(context);
		return dal.getallTags();
	}
	
	public static ArrayList<Tag> getAllCategories(Context context)
	{
		DAL dal = new DAL(context);
		return dal.getAllCategories();
	}
	
	public String  getTagId()
	{
		return _TagId;
	}
	
	public void setTag (String Tag)
	{
		_Name = Tag;
	}
	
	public String getTag()
	{
		return _Name;
	}
	
	public  void setIsCategory(Boolean IsCategory)
	{
		_IsCategory = IsCategory;
	}
	
	public Boolean getIsCategory()
	{
		return _IsCategory;
	}

	public void setNoOfRecords(int NoOfecords)
	{
		_NumOfRecords = NoOfecords;
				
	}
	public int getNoOfRecords()
	{
		return _NumOfRecords;
	}
	
	//INCOMPLETE
	public Tag getDefaultCategory()
	{
		Tag defaultCategory = null; 
		
		return defaultCategory;
	}

}
