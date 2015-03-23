package justine.app.shoplist.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import justine.app.shoplist.R;

/**
 * Created by Justine on 2015/03/17.
 */
public class SListProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private SListDBHelper sListDBHelper;
    private static final int SHOPLIST_NAME =100;
    private static final int SHOPLIST_ITEM=101;
    String [] fromCols ={SListContract.MainListColumns._ID,SListContract.MainListColumns.SHOPLIST_NAME_COL};
    String [] fromDetCols ={SListContract.DetailListColumns._ID,SListContract.DetailListColumns.SHOPLIST_ITEMS_COL, SListContract.DetailListColumns.SHOPLIST_PARENT_NAME};
    int [] toTextViews ={R.id.listView};

    static  UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = SListContract.CONTENT_AUTHORITY;

        matcher.addURI(authority,SListContract.SLIST_NAME,SHOPLIST_NAME);
        matcher.addURI(authority,SListContract.SLIST_DETAIL,SHOPLIST_ITEM);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        sListDBHelper = new SListDBHelper(getContext());
        return true;
    }
   // private Cursor getShopListNames() {
   // }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
       Cursor mCursor;
        switch (sUriMatcher.match(uri)){
            case SHOPLIST_NAME:{
                sortOrder = "_ID ASC";
                mCursor = sListDBHelper.getReadableDatabase().query(
                        SListContract.MainListColumns.SHOPLIST_TABLE_NAME,
                        fromCols,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                        );
                        break;
            }
            case SHOPLIST_ITEM:{
                sortOrder ="_ID ASC";
                mCursor = sListDBHelper.getReadableDatabase().query(
                        SListContract.DetailListColumns.SHOPLIST_DETAIL_TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                        );
                        break;
                }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        mCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return mCursor;

    }
    @Override
    public String getType(Uri uri){
        // this returns the content type
        final int match = sUriMatcher.match(uri);

        switch (match){
            case SHOPLIST_NAME:
                return ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+SListContract.SLIST_NAME;
            case SHOPLIST_ITEM:
                return ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+SListContract.SLIST_DETAIL;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = sListDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match){
            case SHOPLIST_NAME:
                db.insert(SListContract.MainListColumns.SHOPLIST_TABLE_NAME,
                        null,
                        values

                );
                break;
            case SHOPLIST_ITEM:
                db.insert(SListContract.DetailListColumns.SHOPLIST_DETAIL_TABLE_NAME,
                        null,
                        values);
                System.out.print("db inserted");
                break;

        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = sListDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SHOPLIST_NAME:
                db.delete(SListContract.MainListColumns.SHOPLIST_TABLE_NAME,
                        selection,
                        selectionArgs

                );
                break;
            case SHOPLIST_ITEM:
                db.delete(SListContract.DetailListColumns.SHOPLIST_DETAIL_TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
