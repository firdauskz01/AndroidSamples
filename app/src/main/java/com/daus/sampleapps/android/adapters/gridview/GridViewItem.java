package com.daus.sampleapps.android.adapters.gridview;

import android.graphics.drawable.Drawable;

public class GridViewItem {

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
