package justine.app.shoplist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import justine.app.shoplist.data.SListContract;
import justine.app.shoplist.data.SListDBHelper;


public class MainActivity extends Activity {
    //SListProvider sListProvider;
    SListDBHelper sListDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
          // sListProvider = new SListProvider();
            sListDBHelper = new SListDBHelper(MainActivity.this);
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ListFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                ContentResolver resolver = getContentResolver();
                resolver.delete(SListContract.DetailListColumns.CONTENT_URI,null,null);
                getBaseContext().getContentResolver().notifyChange((SListContract.DetailListColumns.CONTENT_URI),null);
                resolver.delete(SListContract.MainListColumns.CONTENT_URI,null,null);
                getBaseContext().getContentResolver().notifyChange((SListContract.MainListColumns.CONTENT_URI),null);

                return true;
            case R.id.add_item:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add new ShopList");
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //SQLiteDatabase db = sListDBHelper.getsWritableDatabase();
                        ContentValues values= new ContentValues();
                        values.put(SListContract.MainListColumns.SHOPLIST_NAME_COL,inputField.getText().toString());

                        ContentResolver resolver = getContentResolver();
                        System.out.println(SListContract.MainListColumns.CONTENT_URI);
                        resolver.insert((SListContract.MainListColumns.CONTENT_URI),values);
                        getBaseContext().getContentResolver().notifyChange((SListContract.MainListColumns.CONTENT_URI),null);
                        Log.d("MainActivity", inputField.getText().toString());

                    }

                });


                builder.setNegativeButton("Cancel",null);

                builder.create().show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
