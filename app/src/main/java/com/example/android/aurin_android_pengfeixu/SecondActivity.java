/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity{

    ArrayList<Capabilities> cap2 = new ArrayList<>();
    ArrayList<String> spinner_Items = new ArrayList<>();
    ArrayAdapter<String> adapter1;
    public Spinner spinner;
    public EditText eSearch;

    @Override
    /*
     If user does not specify any keyword but select dataset directly,
     should jump to DetailActivity, otherwise jump to ThirdActivity.
      */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Receive intent from previous activity
        Intent intent = getIntent();
        if ("action".equals(intent.getAction())){
            BBOX filter_bbox;
            filter_bbox = (BBOX)intent.getSerializableExtra("bbox");
            Picked_City.picked_city = filter_bbox;

            // cap2 contains all dataset from MainActivity
            // Add all capabilities from MainActivity to cap2
            for (int i = 0; i< AllDatasets.lists.size();i++){
                cap2.add(AllDatasets.lists.get(i));
            }

            // Ensure all organizations are shown in the spinner
            for(int i =0; i<cap2.size(); i++){
                String org = cap2.get(i).organization;
                if(! spinner_Items.contains(org))
                    spinner_Items.add(org);
            }

            // Setting for organization spinner
            spinner_Items.add("All Organizations");

            spinner = (Spinner) findViewById(R.id.organization);

            adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,spinner_Items);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setSelection(spinner_Items.size()-1);
            spinner.setOnItemSelectedListener(new SpinnerSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner.setVisibility(View.VISIBLE);

            // Setting for listView
            CapAdapter adapter = new CapAdapter(SecondActivity.this, R.layout.list_view_sub, cap2);
            ListView listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(adapter);
            System.out.println(filter_bbox.getHigherLa());

            // If an item in listView is selected, then go to DetailActivity, carrying that cap object
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Capabilities cap = cap2.get(position);
                    Intent intent = new Intent(SecondActivity.this, DetailActivity.class);
                    intent.setAction("action");
                    intent.putExtra("capobj", cap);
                    startActivity(intent);
                }
            });

            // Search bar
            eSearch = (EditText) findViewById(R.id.etSearch);
            // Search button
            ImageButton go;
            go = (ImageButton) findViewById(R.id.keyword);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String orgselec = spinner.getSelectedItem().toString();
                    final String search = eSearch.getText().toString();
                    ArrayList<String> organdselec = new ArrayList<>();
                    organdselec.add(orgselec);
                    organdselec.add(search);

                    // Pass selected organization and keyword to ThirdActivity
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.setAction("action");
                    intent.putExtra("organdselec",organdselec);
                    startActivity(intent);
                }
            });
        }


    }

    private double max(double a, double b) {
        if(a>=b)
            return a;
        else
            return b;
    }

    private double min(double a, double b) {
        if (a<=b)
            return a;
        else
            return b;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

        }

        public void onNothingSelected(AdapterView<?> parent){

        }
    }

}