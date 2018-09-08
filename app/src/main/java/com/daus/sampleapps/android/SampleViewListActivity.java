package com.daus.sampleapps.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daus.sampleapps.android.sampleview.CollapsingToolbarActivity;

import java.util.ArrayList;

public class SampleViewListActivity extends AppCompatActivity {

    private SampleViewListActivity sampleViewListActivity;
    ArrayList<ListViewItem> listMenu = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sampleViewListActivity = this;
        setupListView();
    }


    private void setupListView() {

        RecyclerView listView = findViewById(R.id.grid_menu);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        listView.setHasFixedSize(true);

        listView.setLayoutManager(new LinearLayoutManager(this));// for listView layout

        ListViewItem itemSampleView = new ListViewItem();
        itemSampleView.setName("Collapsing Toolbar");
        itemSampleView.setDrawableId(getResources().getDrawable(R.drawable.sample_view_collapsing));
        listMenu.add(itemSampleView);


        ListViewMenuAdapter listMenuAdapter = new ListViewMenuAdapter(sampleViewListActivity, listMenu);
        listView.setAdapter(listMenuAdapter);

        listMenuAdapter.setClickListener(new ListItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(sampleViewListActivity, CollapsingToolbarActivity.class));
                        break;
                }
            }
        });
    }

    public static class ListViewItem {

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

    private class ListViewMenuAdapter extends RecyclerView.Adapter<ListViewMenuAdapter.ViewHolder> {
        private static final String TAG = "GridViewMenuAdapter";

        private final ArrayList<ListViewItem> listMenu;
        private final LayoutInflater inflater;
        private final Context context;
        private ListItemClickListener mClickListener;

        public ListViewMenuAdapter(Context context, ArrayList<ListViewItem> listMenu) {
            this.listMenu = listMenu;
            this.context = context;
            inflater = (LayoutInflater) context
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.list_menu_layout, parent, false);
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
                textView = itemView.findViewById(R.id.tvListMenuItem);
                imgView = itemView.findViewById(R.id.imgListMenuItem);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        // allows clicks events to be caught
        public void setClickListener(ListItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events


    }

    public interface ListItemClickListener {
        void onItemClick(View view, int position);
    }
}