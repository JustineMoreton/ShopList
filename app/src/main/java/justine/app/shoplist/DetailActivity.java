package justine.app.shoplist;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import justine.app.shoplist.data.SListContract;
import justine.app.shoplist.data.SListDBHelper;

/**
 * Created by Justine on 2015/03/21.
 */
public class DetailActivity extends Activity {
    DetailFragment detailFragment = new DetailFragment();
    Bundle args = new Bundle();
    SListDBHelper sListDBHelper;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id=getIntent().getStringExtra("_ID");
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            // sListProvider = new SListProvider();
            sListDBHelper = new SListDBHelper(DetailActivity.this);
            args.putString("_ID",id);
            detailFragment.setArguments(args);
            getFragmentManager().beginTransaction()
                    .add(R.id.container, detailFragment)
                    .commit();
        }


    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("onactivity result");


        final EditText editText = detailFragment.getEditText();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                System.out.println("in onEDitorAct");

                String inputString = editText.getText().toString();
                //actionId == EditorInfo.IME_ACTION_SEND && event.getKeyCode() ==KeyEvent.KEYCODE_ENTER
                /**if (editText.length() > 0) {
                    editText.getText().clear();
                }*/
                if (actionId == EditorInfo.IME_NULL ) {
                    System.out.println("action!send");
                    ContentValues values = new ContentValues();
                    values.put(SListContract.DetailListColumns.SHOPLIST_ITEMS_COL,inputString);
                    values.put(SListContract.DetailListColumns.SHOPLIST_PARENT_NAME,id);
                    ContentResolver contentResolver = getContentResolver();
                    contentResolver.insert(SListContract.DetailListColumns.CONTENT_URI,values);

                }
                return true;
            }
        });
}

}

