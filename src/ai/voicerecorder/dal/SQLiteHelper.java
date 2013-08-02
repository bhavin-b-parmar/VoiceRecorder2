package ai.voicerecorder.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper 
{
	Context context;
	SQLiteDatabase db;
	

	
	
	public SQLiteHelper(Context context, String databaseName,CursorFactory factory, int version)
	{
		super(context, databaseName, factory, version);
		this.context = context;
		this.db = getReadableDatabase();
	}

	public long Insert(String table, ContentValues cv) 
	{
		db = this.getWritableDatabase();
		return db.insert(table, null, cv);
	}

	public int Delete(String table,String whereClause,String[] whereArgs)
	{
		return db.delete(table, whereClause, whereArgs);
		
	}
	public boolean createTable(String sql)
	{
		boolean returnValue=true;
			
			db=this.getWritableDatabase();
			db.execSQL(sql);
			db.close();
		
			return returnValue;
	}
	public int Update(String table, ContentValues cv, String whereClause,String[] whereArgs)
	{
		return db.update(table, cv, whereClause, whereArgs);
	}

	public Cursor Select(String sql)
	{
		db = this.getReadableDatabase();
		Cursor c = db.rawQuery(sql, null);

		c.moveToFirst();
		db.close();
		return c;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) 
	{
		// TODO Auto-generated method stub
		
	}

}
