package com.example.android.aurin_android_pengfeixu;

import java.util.ArrayList;

/**
 * Created by liam on 29/11/17.
 */

public class Capability {
    private String name;
    private String title;
    private String abstracts;
    private String organization;
    private String geoname;
    private ArrayList<String> keywords;
    private BBOX bbox;
    private int image_id;

    public Capability(String name, String title, String abstracts, String organization, String geoname, ArrayList<String> keywords, BBOX bbox, int image_id) {
        this.name = name;
        this.title = title;
        this.abstracts = abstracts;
        this.organization = organization;
        this.geoname = geoname;
        this.keywords = keywords;
        this.bbox = bbox;
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getGeoname() {
        return geoname;
    }

    public void setGeoname(String geoname) {
        this.geoname = geoname;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public BBOX getBbox() {
        return bbox;
    }

    public void setBbox(BBOX bbox) {
        this.bbox = bbox;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
