/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_liyuzhang;

import java.io.Serializable;
import java.util.ArrayList;

// A object containing info about dataset
public class Capabilities implements Serializable {
    public String name = "no name";
    public String title = "no data title";
    public String abstracts = "no abstracts";
    public String organization = "no organization";
    public String geoname = "no name";
    public ArrayList<String> keywords;
    public BBOX bbox = new BBOX();
    public int image_id;
}

