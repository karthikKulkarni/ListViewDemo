package com.android.samplelistview.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Karthik_Kulkarni01 on 5/20/2016.
 */
public class Country implements Serializable {

    String title;
    List<Rows> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }
}
