package justine.app.shoplist;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import justine.app.shoplist.data.SListContract;

/**
 * Created by Justine on 2015/03/16.
 */
public class ListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView mListView;
    String [] fromCols ={SListContract.MainListColumns._ID,SListContract.MainListColumns.SHOPLIST_NAME_COL};
    Boolean idIsPresent;
   SListAdapter mAdapter;
 final static int SHOPLIST_INT =0;
 final static int COL_SHOPLIST_ID=0;
 final static int COL_SHOPLIST_NAME=1;
   public ListFragment(){

   }

    @Override
    public void onCreate(Bundle savedInstantState){
        super.onCreate(savedInstantState);
        //SListProvider sListProvider = new SListProvider();
        setHasOptionsMenu(true);
        getLoaderManager().initLoader(SHOPLIST_INT,null,this);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mListView =(ListView) rootView.findViewById(R.id.listView);

        mAdapter = new SListAdapter(getActivity(),null,0);
        mListView.setAdapter(mAdapter);
        System.out.println("item clicked");
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("item clicked");
                Long itemId = mAdapter.getItemId(position);
                String itemIdStr = Long.toString(itemId);
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("_ID",itemIdStr);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = SListContract.MainListColumns.CONTENT_URI;
        CursorLoader cursorLoader = null;
        switch (id){
            case SHOPLIST_INT:
               cursorLoader = new CursorLoader(
                       this.getActivity(),
                        uri,
                        fromCols,
                        null,
                        null,
                        null
                );
        }
       return cursorLoader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    //mAdapter.notifyDataSetChanged();
    mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mAdapter.swapCursor(null);
    }
    public void setTwopane(Boolean mTwoPane){
        idIsPresent =mTwoPane;
    }

}

