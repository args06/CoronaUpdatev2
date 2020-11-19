package com.example.coronaupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coronaupdate.model.global.CountriesItem;

public class DetailGlobalActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_DATE = "extra_date";
    private CountriesItem item;
    private TextView tvCountryName, tvLastUpdate, tvConfirmed, tvRecovered, tvDeaths;
    private LinearLayout llFMI;
    private String sDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_global);

        item = getIntent().getParcelableExtra(EXTRA_DATA);
        sDate = getIntent().getStringExtra(EXTRA_DATE);

        tvCountryName = findViewById(R.id.tv_country_name);
        tvLastUpdate = findViewById(R.id.tv_detail_global_update);
        tvConfirmed = findViewById(R.id.tv_detail_value_confirmed_global);
        tvRecovered = findViewById(R.id.tv_detail_value_recovered_global);
        tvDeaths = findViewById(R.id.tv_detail_value_deaths_global);
        llFMI = findViewById(R.id.ll_fmi);

        tvCountryName.setText(item.getCountry());
        tvLastUpdate.setText(sDate);
        tvConfirmed.setText(String.valueOf(item.getTotalConfirmed()));
        tvRecovered.setText(String.valueOf(item.getTotalRecovered()));
        tvDeaths.setText(String.valueOf(item.getTotalDeaths()));

        llFMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "Update COVID-19 " + item.getCountry());
                startActivity(intent);
            }
        });
    }
}