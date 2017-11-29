package com.example.android.aurin_android_pengfeixu;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by liam on 29/11/17.
 */

public class CapabilitiesAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Capabilities> capabilitiesArrayList;

    public CapabilitiesAdapter(Context context, ArrayList<Capabilities> capabilitiesArrayList) {
        this.mContext = context;
        this.capabilitiesArrayList = capabilitiesArrayList;
    }

    @Override
    public int getCount() {
        return capabilitiesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return capabilitiesArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView = View.inflate(mContext, R.layout.list_view_sub, null);
        TextView capTitle = (TextView) mView.findViewById(R.id.cap_title);
        TextView capOrg = (TextView) mView.findViewById(R.id.cap_org);
        ImageView capImage = (ImageView) mView.findViewById(R.id.cap_image);
        TextView capKeywords = (TextView) mView.findViewById(R.id.cap_keywords);

        capTitle.setText(capabilitiesArrayList.get(i).title);
        capOrg.setText(capabilitiesArrayList.get(i).organization);
        capKeywords.setText("Keywords: " + capabilitiesArrayList.get(i).keywords);
        capImage.setImageResource(capabilitiesArrayList.get(i).image_id);
//        Log.i("CapAdapter###Title", capabilitiesArrayList.get(i).title);
//        Log.i("CapAdapter###Org", capabilitiesArrayList.get(i).organization);
        return mView;
    }
}
