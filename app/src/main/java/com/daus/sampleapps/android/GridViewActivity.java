package com.daus.sampleapps.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    private GridViewActivity gridViewActivity;

    ArrayList<GridViewItem> listMenu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewActivity = this;
        setupGridview();

    }

    private void setupGridview() {

        RecyclerView gridView = findViewById(R.id.grid_menu);
        int numberOfColumns = 2;

        gridView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));// for grid layout


        GridViewItem itemSampleView = new GridViewItem();
        itemSampleView.setName("View");
        itemSampleView.setDrawableId(getResources().getDrawable(R.drawable.sample_view_icon));
        listMenu.add(itemSampleView);


        GridViewItem itemFile = new GridViewItem();
        itemFile.setName("File");
        itemFile.setDrawableId(getResources().getDrawable(R.drawable.folder_icon));
        listMenu.add(itemFile);

        GridViewItem itemGPS = new GridViewItem();
        itemGPS.setName("GPS");
        itemGPS.setDrawableId(getResources().getDrawable(R.drawable.gps_icon));
        listMenu.add(itemGPS);

        GridViewItem itemInternet = new GridViewItem();
        itemInternet.setName("Internet");
        itemInternet.setDrawableId(getResources().getDrawable(R.drawable.internet_icon));
        listMenu.add(itemInternet);

        GridViewItem itemCamera = new GridViewItem();
        itemCamera.setName("Camera");
        itemCamera.setDrawableId(getResources().getDrawable(R.drawable.camera_icon));
        listMenu.add(itemCamera);

        GridViewMenuAdapter gridmenuAdapter = new GridViewMenuAdapter(gridViewActivity, listMenu);
        gridView.setAdapter(gridmenuAdapter);

        gridmenuAdapter.setClickListener(new GridItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(gridViewActivity, SampleViewListActivity.class));
                        break;
                }
            }
        });
    }

    public static class GridViewItem {

        private String name;
        private Drawable drawableId;


        public Drawable getDrawableId() {
            return drawableId;
        }

        public void setDrawableId(Drawable drawableId) {
            this.drawableId = drawableId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class GridViewMenuAdapter extends RecyclerView.Adapter<GridViewMenuAdapter.ViewHolder> {

        private static final String TAG = "GridViewMenuAdapter";

        private final ArrayList<GridViewItem> listMenu;
        private final LayoutInflater inflater;
        private final Context context;
        private GridItemClickListener mClickListener;

        public GridViewMenuAdapter(Context context, ArrayList<GridViewItem> listMenu) {
            this.listMenu = listMenu;
            this.context = context;
            inflater = (LayoutInflater) context
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.grid_menu_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Log.e(TAG, "onBindViewHolder position : " + position);
            Log.e(TAG, "onBindViewHolder name : " + listMenu.get(position).getName());
            try {
                holder.textView.setText(listMenu.get(position).getName());
                holder.imgView.setImageDrawable(listMenu.get(position).getDrawableId());
            } catch (Exception e) {
                Log.e(TAG, "onBindViewHolder : " + e.toString());
                e.printStackTrace();
            }
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getItemCount() {
            return listMenu.size();
        }

        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView textView;
            ImageView imgView;

            ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.tvGridMenuItem);
                imgView = itemView.findViewById(R.id.imgGridMenuItem);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        // allows clicks events to be caught
        public void setClickListener(GridItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }



    }

    // parent activity will implement this method to respond to click events
    public interface GridItemClickListener {
        void onItemClick(View view, int position);
    }
}
