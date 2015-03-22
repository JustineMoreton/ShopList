package justine.app.shoplist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import justine.app.shoplist.data.SListContract.DetailListColumns;
import justine.app.shoplist.data.SListContract.MainListColumns;
/**
 * Created by Justine on 2015/03/16.
 */
public class SListDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "shopList.db";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sLiteDB;
    public SListDBHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.sLiteDB = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    final String SQL_CREATE_SHOPLIST_NAMES_TABLE ="CREATE TABLE " +
           MainListColumns.SHOPLIST_TABLE_NAME + "(" +
           MainListColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
           MainListColumns.SHOPLIST_NAME_COL + " TEXT NOT NULL);";


    final String SQL_CREATE_SHOPLIST_DETAIL_TABLE= "CREATE TABLE " +
            DetailListColumns.SHOPLIST_DETAIL_TABLE_NAME +"(" +
            DetailListColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DetailListColumns.SHOPLIST_ITEMS_COL +" TEXT NOT NULL, " +
            DetailListColumns.SHOPLIST_PARENT_NAME +" INTEGER NOT NULL, " +
            " FOREIGN KEY (" + DetailListColumns.SHOPLIST_PARENT_NAME + ") REFERENCES " +
            MainListColumns.SHOPLIST_TABLE_NAME+
            "("+ MainListColumns._ID+ "));";



        db.execSQL(SQL_CREATE_SHOPLIST_NAMES_TABLE);
        db.execSQL(SQL_CREATE_SHOPLIST_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
public void insertNewShopListName(){

}

}
