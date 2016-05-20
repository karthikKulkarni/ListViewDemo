package com.android.samplelistview.model;

import java.io.Serializable;

/**
 * Created by Karthik_Kulkarni01 on 5/20/2016.
 */
public class Rows implements Serializable {

    String title;
    String description;
    String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
