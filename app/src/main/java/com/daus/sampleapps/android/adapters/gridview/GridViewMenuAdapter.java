package com.daus.sampleapps.android.adapters.gridview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daus.sampleapps.android.R;

import java.util.ArrayList;

public class GridViewMenuAdapter extends RecyclerView.Adapter<GridViewMenuAdapter.ViewHolder> {

    private static final String TAG = "GridViewMenuAdapter";

    private final ArrayList<GridViewItem> listMenu;
    private final LayoutInflater inflater;
    private final Context context;
    private GridItemClickListener mClickListener;

    public GridViewMenuAdapter(Context context, ArrayList<GridViewItem> listMenu) {
        this.listMenu = listMenu;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    // parent activity will implement this method to respond to click events
    public interface GridItemClickListener {
        void onItemClick(View view, int position);
    }

}
