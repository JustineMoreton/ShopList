package justine.app.shoplist.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Justine on 2015/03/16.
 */
public class SListContract {
    public static final String CONTENT_AUTHORITY ="justine.app.shoplist.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String SLIST_NAME = "shopList";
    public static final String SLIST_DETAIL = "shopList_Items_Table_Name";
    public SListContract(){

    }

    public static final class MainListColumns implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(SLIST_NAME).build();

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                "/vnd." + CONTENT_AUTHORITY + "." + SLIST_NAME;
        public static final String CONTENT_TYPE =   ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/vnd." + CONTENT_AUTHORITY + "." + SLIST_NAME;

        public static final String SHOPLIST_TABLE_NAME = "shopList";
        public static final String SHOPLIST_NAME_COL = "shopList_Name";

    }
    public static class DetailListColumns implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(SLIST_DETAIL).build();
        public static final String SHOPLIST_DETAIL_TABLE_NAME = "shopList_Items_Table_Name";
        public static final String SHOPLIST_PARENT_NAME ="shopList_Parent";
        public static final String SHOPLIST_ITEMS_COL = "shopList_Items";
    }
}
