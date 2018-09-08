package com.daus.sampleapps.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.daus.sampleapps.android.adapters.gridview.GridViewItem;
import com.daus.sampleapps.android.adapters.gridview.GridViewMenuAdapter;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    private GridViewActivity gridViewActivity;

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

        ArrayList<GridViewItem> listMenu = new ArrayList<>();

        GridViewItem itemSampleView = new GridViewItem();
        itemSampleView.setName("Sample View");
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

        gridmenuAdapter.setClickListener(new GridViewMenuAdapter.GridItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(gridViewActivity, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
