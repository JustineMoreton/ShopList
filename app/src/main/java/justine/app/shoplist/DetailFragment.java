package justine.app.shoplist;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import justine.app.shoplist.data.SListContract;

/**
 * Created by Justine on 2015/03/21.
 */
public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView mlistView;
    private DetailAdapter detailAdapter;
    private EditText edittext;
    private Bundle mBundle;

    public DetailFragment(){
        setHasOptionsMenu(true);
    }

    final static int SHOPLIST_DETAIL_INT=0;
    final static int SHOPLIST_DETAIL_COL=1;
    final  String[] fromCols ={SListContract.DetailListColumns._ID,SListContract.DetailListColumns.SHOPLIST_ITEMS_COL};
    final  String selection= SListContract.DetailListColumns.SHOPLIST_PARENT_NAME +"=?";
    String[] selectionArgs = new String[1];

    @Override
    public void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);

            Bundle args = getArguments();
            mBundle = args;
            if (args != null) {
                this.setSelectionArgument(args.getString("_ID"));
            } else {
                this.setSelectionArgument("0");
            }
            getLoaderManager().initLoader(SHOPLIST_DETAIL_INT, null, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        mlistView = (ListView) rootView.findViewById(R.id.list_items_view);
        detailAdapter = new DetailAdapter(getActivity(), null, 0);
        mlistView.setAdapter(detailAdapter);

      /*  if(mBundle!=null) {
            rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            mlistView = (ListView) rootView.findViewById(R.id.list_items_view);
            detailAdapter = new DetailAdapter(getActivity(), null, 0);
            mlistView.setAdapter(detailAdapter);

        }else{
            rootView = inflater.inflate(R.layout.fragment_detail_no_list, container,false);
            mlistView = (ListView) rootView.findViewById(R.id.list_items_view);
            detailAdapter = new DetailAdapter(getActivity(), null, 0);
            mlistView.setAdapter(detailAdapter);
            }*/
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstantState){
        super.onActivityCreated(savedInstantState);
        edittext = (EditText) getView().findViewById(R.id.list_item_editText);

    }
    public EditText getEditText() {
        return edittext;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = SListContract.DetailListColumns.CONTENT_URI;
        CursorLoader cursorLoader = null;
        switch (id){
            case SHOPLIST_DETAIL_INT:
                cursorLoader = new CursorLoader(
                        this.getActivity(),
                        uri,
                        fromCols,
                        selection,
                        selectionArgs,
                        null
                );
        }
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
     detailAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        detailAdapter.swapCursor(null);
    }


   private void setSelectionArgument(String args)
   {
           this.selectionArgs[0] = args;

   }



}
