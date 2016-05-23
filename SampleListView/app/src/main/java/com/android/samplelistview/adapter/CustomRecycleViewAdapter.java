package com.android.samplelistview.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.samplelistview.R;
import com.android.samplelistview.model.Rows;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Karthik_Kulkarni01 on 5/20/2016.
 * This adapter is responsible to draw the list items on the fragment
 */
public class CustomRecycleViewAdapter extends RecyclerView.Adapter<CustomRecycleViewAdapter.CountryViewHolder> {

    private List<Rows> countryRow;
    private Context context;
    private Handler handler = new Handler();
    private DisplayImageOptions options;


    public CustomRecycleViewAdapter(Context context, List<Rows> countryRow) {
        this.context = context;
        this.countryRow = countryRow;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.no_image_border)
                .showImageForEmptyUri(R.drawable.no_image_border)
                .showImageOnFail(R.drawable.no_image_border)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    //A view holder to hold the View components
    public class CountryViewHolder extends RecyclerView.ViewHolder {
        public TextView heading;
        public TextView description;
        public ImageView image;

        public CountryViewHolder(View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.landing_list_row_heading);
            description = (TextView) itemView.findViewById(R.id.landing_list_row_description);
            image = (ImageView) itemView.findViewById(R.id.landing_list_row_img);
        }
    }

    //Initialize the View by inflating a layout for the 1st time.
    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .landing_fragment_row, viewGroup, false);
        return new CountryViewHolder(itemView);
    }

    //This method is called everytime a new item is to be loaded.
    @Override
    public void onBindViewHolder(CountryViewHolder countryViewHolder, int i) {
        Rows row = countryRow.get(i);

        //Check if all the properties of row object are empty, if so delete the item from the list
        // and redraw.
        if (row.getTitle() == null && row.getDescription() == null && row.getImageHref() == null) {
            countryRow.remove(i);
            final Runnable r = new Runnable() {
                public void run() {
                    notifyDataSetChanged();
                }
            };
            handler.post(r);
        }
        countryViewHolder.heading.setText(row.getTitle());
        countryViewHolder.description.setText(row.getDescription());

        //Load images from the URL. For all the configurations check SampleListViewApplication
        // class.
        ImageLoader.getInstance().displayImage(row.getImageHref(), countryViewHolder.image, options, null);
    }

    @Override
    public int getItemCount() {
        return countryRow.size();
    }

}
