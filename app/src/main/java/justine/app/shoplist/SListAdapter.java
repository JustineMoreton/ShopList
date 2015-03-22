package justine.app.shoplist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Justine on 2015/03/19.
 */
public class SListAdapter extends CursorAdapter {
    private static final int VIEW_TYPE_MAINLIST = 0;

    public SListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

public static class ViewHolder{
    public final TextView shoplistNameView;

    public ViewHolder(View view){
        shoplistNameView = (TextView) view.findViewById(R.id.list_name);

    }

}

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        int viewType = getItemViewType(cursor.getPosition());
        int layoutId = 0;
        switch(viewType){
            case VIEW_TYPE_MAINLIST:{
                layoutId = R.layout.slname_item;

                break;
            }
        }
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        int viewType = getItemViewType(cursor.getPosition());
        String shoplistname =cursor.getString(ListFragment.COL_SHOPLIST_NAME);
        viewHolder.shoplistNameView.setText(shoplistname);
    }
}
