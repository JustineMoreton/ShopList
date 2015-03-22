package justine.app.shoplist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Justine on 2015/03/22.
 */
public class DetailAdapter extends CursorAdapter {
    private static final int VIEW_TYPE_DETAILLIST= 0;
    public DetailAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    public static class ViewHolder{
        public final TextView shoplistDetailView;

        public ViewHolder(View view){
            shoplistDetailView= (TextView) view.findViewById(R.id.list_detail_textview);

        }

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int viewType = getItemViewType(cursor.getPosition());
        int layoutId = 0;
        switch(viewType){
            case VIEW_TYPE_DETAILLIST:{
                layoutId = R.layout.sldetail_item;

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
        String shoplistdetail =cursor.getString(DetailFragment.SHOPLIST_DETAIL_INT);
        viewHolder.shoplistDetailView.setText(shoplistdetail);
    }
}
