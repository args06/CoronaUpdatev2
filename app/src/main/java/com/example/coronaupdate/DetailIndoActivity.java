package com.example.coronaupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coronaupdate.model.indo.DataItem;

public class DetailIndoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_DATE = "extra_date";
    private DataItem item;
    private TextView tvProvinceName, tvLastUpdate, tvConfirmed, tvRecovered, tvDeaths;
    private String sDate;
    LinearLayout llOfcWeb, llHotline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_indo);

        item = getIntent().getParcelableExtra(EXTRA_DATA);
        sDate = getIntent().getStringExtra(EXTRA_DATE);

        tvProvinceName = findViewById(R.id.tv_province_name);
        tvLastUpdate = findViewById(R.id.tv_detail_indo_update);
        tvConfirmed = findViewById(R.id.tv_detail_value_confirmed_indo);
        tvRecovered = findViewById(R.id.tv_detail_value_recovered_indo);
        tvDeaths = findViewById(R.id.tv_detail_value_deaths_indo);
        llOfcWeb = findViewById(R.id.ll_ofc_web);
        llHotline = findViewById(R.id.ll_hotline);

        tvProvinceName.setText(item.getProvinsi());
        tvLastUpdate.setText(sDate);
        tvConfirmed.setText(String.valueOf(item.getKasusPosi()));
        tvRecovered.setText(String.valueOf(item.getKasusSemb()));
        tvDeaths.setText(String.valueOf(item.getKasusMeni()));

        llOfcWeb.setOnClickListener(this);
        llHotline.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        Intent intentCall = new Intent(Intent.ACTION_DIAL);
        switch(v.getId()){
            case R.id.ll_ofc_web:
                intent.putExtra(SearchManager.QUERY, "https://covid19.go.id/");
                startActivity(intent);
                break;

            case R.id.ll_hotline:
                String phoneNumber = "119";
                intentCall.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intentCall);
                break;
        }
    }
}