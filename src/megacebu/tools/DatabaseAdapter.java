package megacebu.tools;

import java.util.ArrayList;
import java.util.List;

import megacebu.data_objects.SearchedItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {
	
	private static final String DB_NAME = "megacebu.db";
	private static final String DB_TABLE_ITEM = "item";
	private static final String DB_TABLE_ITEM_AMNETIES = "item_amneties";
	private static final String DB_TABLE_AMNETY = "amnety";
	private static final int DB_VERSION = 1;
	
	public static final String KEY_ID = "_id";
	
	// ITEM TABLE
	public static final String KEY_ITEM_NAME = "item_name";
	public static final String KEY_ITEM_IMAGE = "item_image";
	public static final String KEY_ITEM_LOCATION = "item_location";
	public static final String KEY_ITEM_PRICE_FROM = "item_price_from";
	public static final String KEY_ITEM_PRICE_TO = "item_price_to";
	public static final String KEY_ITEM_TYPE = "item_type";
	public static final String KEY_ITEM_RATING= "item_rating";
	public static final String KEY_ITEM_DESCRIPTION = "item_description";
	public static final String KEY_ITEM_LONG = "item_long";
	public static final String KEY_ITEM_LAT = "item_lat";
	
	// ITEM_AMNETIES TABLE
	public static final String KEY_ITEM_AMNETIES_ITEM_ID = "item_id";
	public static final String KEY_ITEM_AMNETIES_AMNETY_ID = "amnety_id";
	
	// AMNETY TABLE
	public static final String KEY_AMNETY_NAME = "amnety_name";
	
	private SQLiteDatabase databaseHolder;
	private Context contextHolder;
	private DBHelper dbHelper;
	
	public DatabaseAdapter(Context ctx)
	{
		contextHolder = ctx;
		dbHelper = new DBHelper(contextHolder, DB_NAME, null, DB_VERSION);
	}
	
	public DatabaseAdapter open() throws SQLException {
		databaseHolder = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		databaseHolder.close();
	}
	
	public List<SearchedItem> getAllHotels()
	{
		Cursor c;
		List<SearchedItem> searchedItems = new ArrayList<SearchedItem>();
		
		c = databaseHolder.query(true, DB_TABLE_ITEM, new String[] 
				{KEY_ITEM_NAME, KEY_ITEM_IMAGE, KEY_ITEM_PRICE_FROM, KEY_ITEM_PRICE_TO,
				KEY_ITEM_DESCRIPTION, KEY_ITEM_RATING, KEY_ITEM_LAT, KEY_ITEM_LONG, KEY_ITEM_LOCATION}
		, "WHERE " + KEY_ITEM_TYPE + " = 'hotel'", null, null, null, null, null);
		
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			
			// TODO Research on how to show images from external source
			
			SearchedItem item = new SearchedItem();
			item.setItem_name(c.getString(0));
		//	item.setItem_icon(item.convertStringResource(c.getString(1)));
			item.setItem_type("Hotel");
			item.setItem_price_from(c.getFloat(2));
			item.setItem_price_to(c.getFloat(3));
			item.setItem_description(c.getString(4));
		//	item.setItem_rating(item.convertStringResource(c.getString(5)));
			item.setItem_lat(c.getDouble(6));
			item.setItem_long(c.getDouble(7));
			item.setItem_location(c.getString(8));
			
			searchedItems.add(item);
			c.moveToNext();
		}
		
		c.close();
		
		return searchedItems;
	}
	
	public Cursor getAllTouristDestination()
	{
		return databaseHolder.query(true, DB_TABLE_ITEM, new String[] 
				{KEY_ITEM_NAME, KEY_ITEM_IMAGE, KEY_ITEM_PRICE_FROM, KEY_ITEM_PRICE_TO,
				KEY_ITEM_DESCRIPTION, KEY_ITEM_RATING, KEY_ITEM_LAT, KEY_ITEM_LONG, KEY_ITEM_LOCATION}
		, "WHERE " + KEY_ITEM_TYPE + " = 'destination'", null, null, null, null, null);
	}
	
	@SuppressLint("NewApi")
	public Cursor getHotelAmneties(String hotel_id)
	{
		String amnety_id;
		Cursor c;
		
		c = databaseHolder.query(true, DB_TABLE_ITEM_AMNETIES, new String[]
				{KEY_ITEM_AMNETIES_AMNETY_ID}, "WHERE " + KEY_ITEM_AMNETIES_ITEM_ID + " = " + hotel_id, 
				null, null, null, null, null, null);
		
		amnety_id = c.getString(0);
		
		return databaseHolder.query(true, DB_TABLE_AMNETY, new String[]
				{KEY_AMNETY_NAME}, "WHERE " + KEY_ID + " = " + amnety_id, 
				null, null, null, null, null, null);
	}
	
	private static class DBHelper extends SQLiteOpenHelper
	{

		public DBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL("CREATE TABLE " + DB_TABLE_ITEM + " (" + KEY_ID + " int primary key auto increment, "
					+ KEY_ITEM_NAME + " text not null, "
					+ KEY_ITEM_TYPE + " text not null, "
					+ KEY_ITEM_IMAGE + " text not null, "	// drawable
					+ KEY_ITEM_PRICE_FROM + " int not null, "
					+ KEY_ITEM_PRICE_TO + "int, "
					+ KEY_ITEM_LOCATION + " text not null, "
					+ KEY_ITEM_DESCRIPTION + " text not null, "
					+ KEY_ITEM_RATING + " int null, "
					+ KEY_ITEM_LAT + " number, "
					+ KEY_ITEM_LONG + " number");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_ITEM);
			onCreate(db);
			
		}
		
	}
}

