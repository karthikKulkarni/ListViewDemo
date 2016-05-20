package com.android.samplelistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.samplelistview.R;
import com.android.samplelistview.model.Rows;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by karthik_kulkarni01 on 5/20/2016.
 * This adapter is responsible to draw the list times on the fragment
 */
public class CustomListAdapter extends BaseAdapter {


    private LayoutInflater inflater;
    private DisplayImageOptions options;
    private List<Rows> countryRow;
    private Rows row;

    public CustomListAdapter(Context context, List<Rows> countryRow) {
        this.countryRow = countryRow;

        inflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.no_image_border)
                .showImageForEmptyUri(R.drawable.no_image_border)
                .showImageOnFail(R.drawable.no_image_border)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public int getCount() {
        return countryRow.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.landing_fragment_row, parent, false);
            holder = new ViewHolder();
            holder.heading = (TextView) view.findViewById(R.id.landing_list_row_heading);
            holder.description = (TextView) view.findViewById(R.id.landing_list_row_description);
            holder.image = (ImageView) view.findViewById(R.id.landing_list_row_img);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //Get the item at position from list
        row = countryRow.get(position);

        //Check if all the properties of row object are empty, if so delete the item from the list
        // and redraw.
        if (row.getTitle() == null && row.getDescription() == null && row.getImageHref() == null) {
            countryRow.remove(position);
            notifyDataSetChanged();
        }
        holder.heading.setText(row.getTitle());
        holder.description.setText(row.getDescription());
        ImageLoader.getInstance().displayImage(row.getImageHref(), holder.image, options, null);

        return view;
    }

    /**
     * A static class to hold each item from the list
     */
    static class ViewHolder {
        TextView heading;
        TextView description;
        ImageView image;
    }
}

