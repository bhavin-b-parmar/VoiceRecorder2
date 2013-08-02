package Configuration;

import android.content.Context;
import android.content.SharedPreferences;

public class Config 
{

	public enum RecorderEncodingFormat
	{
		MP4,
		MP3
	}
	public enum RecorderEncodingQuality
	{
		High,
		Medium,
		Low
	}

	public final static String DBName = "VR1.db";
	public final static int  DBVersion = 1;
	
	public final static String[] PredefinedCategories = {"Personal", "Work", "College", "Lectures", "Concerts"};
	
	public final static String DefaultCategory = "Personal";
	
	// SQLITE QUERIES
	// SCHEMA CREATION
	public static final String sqlCreateTableTags = "CREATE TABLE if not exists  [Tags] ([TagID] GUID NOT NULL,[Tag] [VARCHAR(15)],[IsCategory] BOOLEAN, [NumOfRecords] INTEGER DEFAULT 0,CONSTRAINT [sqlite_autoindex_Tags_1] PRIMARY KEY ([TagID]))";
	public static final String sqlCreateTableFile = "CREATE TABLE if not exists [File] ([FileID] GUID NOT NULL, [Name] [VARCHAR(30)], [Location] [VARCHAR(50)], [IsDeleted] BOOLEAN, [DateCreated] DATETIME, [Size] NUMBER, CONSTRAINT [sqlite_autoindex_File_1] PRIMARY KEY ([FileID]))";
	public static final String sqlCreateTableFileTags = "CREATE TABLE if not exists [FileTags] ([UID] GUID NOT NULL, [FileID] GUID, [TagID] GUID,CONSTRAINT [sqlite_autoindex_FileTags_1] PRIMARY KEY ([UID]))";
	
	//FILE RELATED QUERIES
	public static final String sqlSelectAllFiles = "Select * from File";
	public static final String sqlGetCategoryOfFile = "select FileTags.[TagID], Tags.[Tag], FileTags.[FileID] from FileTags inner join Tags ON FileTags.[TagID] = Tags.TagID where Tags.IsCategory = 1 AND FileTags.[FileID] LIKE ";
	public static final String sqlGetTagsOfFile = "select FileTags.[TagID], Tags.[Tag], FileTags.[FileID] from FileTags inner join Tags ON FileTags.[TagID] = Tags.TagID where Tags.IsCategory = 0 AND FileTags.[FileID] LIKE ";
	
	//TAG CATEGORY RELATED QUERIES
	public static final String sqlGetTagByBaseQuery = "Select  * from Tags where IsCategory = false";
	public static final String sqlGetCategoryByBaseQuery = sqlGetTagByBaseQuery + "where IsCategory=true";
	public static final String sqlSelectAllTags = "Select * from Tags where IsCategory = 0";
	public static final String sqlSelectAllCategories = "Select * from Tags where IsCategory = 1";
	
	//private static final String sql_EncodingQuality = "CREATE TABLE if not exists  [EncodingQuality]([EncodingQualityID] GUID NOT NULL,[EncodingQualityType] [VARCHAR(10)],[IsDefaultQuality] BOOLEAN,CONSTRAINT [sqlite_autoindex_EncodingQuality_1] PRIMARY KEY ([EncodingQualityID]))";
	//private static final String sql_EncodingFormat = "CREATE TABLE if not exists  [EncodingFormat]([EncodingFormatID] GUID NOT NULL,[Encoding] [VARCHAR(10)], [IsDefaultFormat] BOOLEAN, CONSTRAINT [sqlite_autoindex_EncodingFormat_1] PRIMARY KEY ([EncodingFormatID]))";
	//private static final String sql_FileType = "CREATE TABLE if not exists  [FileType] ([FileTypeID] GUID NOT NULL,[Type][VARCHAR(5)],[ExtendedPropertiesTable] [VARCHAR(140)],CONSTRAINT [sqlite_autoindex_FileType_1] PRIMARY KEY ([FileTypeID]))";
	//private static final String sql_totalcategories = "select FileTags.TagID,Tags.[Tag] from FileTags LEFT JOIN Tags on [FileTags].TagID = Tags.TagID where (FileTags.[FileID] = F6A1740C-068E-4AD1-BBB6-ADDB2E9540F8)&& (Tags.[IsCategory]=1)";
	//private static final String sqlDeleteFile = "delete * from File where fileName = ?";
	//private static final String sql_AudioFileExtendedProperties = "CREATE TABLE if not exists [AudioFileExtendedProperties]([UID] GUID NOT NULL,[FileID] GUID,[PlayTime] BIGINT,[EncodingFormatID] GUID,[EncodingQualityID] GUID,CONSTRAINT [sqlite_autoindex_AudioFileExtendedProperties_1] PRIMARY KEY ([UID]))";

	
	
	// SQLITE TABLE NAMES
	public final static String TableFileTags = "FileTags";
	public final static String TableFile = "File";
	public final static String TableTags  = "Tags";
	
	
	// SQLITE TABLE FIELD NAMES
	// TABLE TAG
	public final static String TableTags_TagId = "TagID";
	public final static String TableTags_Tag = "Tag";
	public final static String TableTags_IsCategory = "IsCategory";
	public final static String TableTags_NumOfRecords = "NumOfRecords";
	
	// TABLE FILE
	public final static String TableFile_FileID = "FileID";
	public final static String TableFile_Name = "Name";
	public final static String TableFile_Location = "Location";
	public final static String TableFile_IsDeleted = "IsDeleted";
	public final static String TableFile_DateCreated = "DateCreated";
	public final static String TableFile_Size = "Size";
	
	// TABLE FILE TAGS
	public final static String TableFileTags_UID = "UID";
	public final static String TableFileTags_FileID = "FileID";
	public final static String TableFileTags_TagID = "TagID";
	
	
	
	
}
