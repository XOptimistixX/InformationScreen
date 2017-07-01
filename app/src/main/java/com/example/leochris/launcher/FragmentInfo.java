package com.example.leochris.launcher;

import android.os.Parcelable;

import java.io.Serializable;

public class FragmentInfo implements Serializable {
    private String name;
    private String uri;

    public FragmentInfo(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public FragmentInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
