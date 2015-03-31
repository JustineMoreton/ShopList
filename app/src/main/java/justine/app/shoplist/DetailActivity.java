package justine.app.shoplist;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
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
    DetailFragment detailFragment;
    Bundle args = new Bundle();
    SListDBHelper sListDBHelper;

    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("activity on create");
        super.onCreate(savedInstanceState);
        id=getIntent().getStringExtra("_ID");
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            detailFragment = new DetailFragment();
            sListDBHelper = new SListDBHelper(DetailActivity.this);
            args.putString("_ID",id);
            detailFragment.setArguments(args);
            getFragmentManager().beginTransaction()
                    .add(R.id.list_detail_container, detailFragment,"detailtag")
                    .commit();
        }
        if(savedInstanceState != null){
            detailFragment= (DetailFragment) getFragmentManager()
                    .findFragmentByTag("detailtag");
        }


    }


    @Override
    protected void onSaveInstanceState (Bundle outState){
    super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState){
    super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        System.out.println("DetailActivity onStart");

            final EditText editText = detailFragment.getEditText();
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    System.out.println("in onEDitorAct");
                    String inputString = editText.getText().toString();
                    if (editText.length() > 0) {
                        editText.getText().clear();
                    }
                    if (actionId == EditorInfo.IME_NULL
                            || (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP)) {
                        System.out.println("action!send " + inputString + " " + id);
                        ContentValues values = new ContentValues();
                        values.put(SListContract.DetailListColumns.SHOPLIST_ITEMS_COL, inputString);
                        values.put(SListContract.DetailListColumns.SHOPLIST_PARENT_NAME, id);
                        ContentResolver contentResolver = getContentResolver();
                        contentResolver.insert(SListContract.DetailListColumns.CONTENT_URI, values);
                        getBaseContext().getContentResolver().notifyChange((SListContract.DetailListColumns.CONTENT_URI), null);
                        Log.d("DetailActivity", inputString);

                    }
                    return true;
                }
            });
        }

}

