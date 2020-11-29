package com.example.coronaupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    int parameterA = 1, parameterB = 1, parameterC = 1;
    String confirmed, deaths, recovered;

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

        confirmed = String.valueOf(item.getKasusPosi());
        tvConfirmed.setText(convertValue(confirmed));

        recovered = String.valueOf(item.getKasusSemb());
        tvRecovered.setText(convertValue(recovered));

        deaths = String.valueOf(item.getKasusMeni());
        tvDeaths.setText(convertValue(deaths));

        llOfcWeb.setOnClickListener(this);
        llHotline.setOnClickListener(this);
        tvConfirmed.setOnClickListener(this);
        tvRecovered.setOnClickListener(this);
        tvDeaths.setOnClickListener(this);
    }

    private StringBuilder changeValue(String sValue){
        int panjang = sValue.length();
        StringBuilder nilai = new StringBuilder();
        for (int i = 1; i <= panjang; ++i)
        {
            nilai.append(sValue.substring(i - 1, i));
            if (i!=panjang){
                if (panjang % 3 == 0){
                    if (i % 3 == 0)
                    {
                        nilai.append(".");
                    }
                } else if (panjang % 3 == 2){
                    if ((i-2) % 3 == 0)
                    {
                        nilai.append(".");
                    }
                } else if (panjang % 3 == 1){
                    if ((i-1) % 3 == 0)
                    {
                        nilai.append(".");
                    }
                }
            }
        }
        return nilai;
    }

    private StringBuilder convertValue(String sValue) {
        int panjang = sValue.length();
        StringBuilder nilai = new StringBuilder();
        int iNilai = Integer.parseInt(sValue);

        if (panjang < 4) {
            nilai.append(sValue);
        } else if (panjang < 7) {
            nilai.append(iNilai / 1000).append("K");
        } else {
            nilai.append(sValue.substring(0, 1)).append(",").append(sValue.substring(1, 2));
            if (panjang < 10) {
                nilai.append("M");
            } else if (panjang < 13) {
                nilai.append("B");
            } else if (panjang < 16) {
                nilai.append("T");
            }
        }
        return nilai;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        Intent intentCall = new Intent(Intent.ACTION_DIAL);
        switch (v.getId()) {
            case R.id.ll_ofc_web:
                intent.putExtra(SearchManager.QUERY, "https://covid19.go.id/");
                startActivity(intent);
                break;
            case R.id.ll_hotline:
                String phoneNumber = "119";
                intentCall.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intentCall);
                break;
            case R.id.tv_detail_value_confirmed_indo:
                if (parameterA == 0){
                    tvConfirmed.setText(convertValue(confirmed));
                    parameterA = 1;
                } else {
                    tvConfirmed.setText(changeValue(confirmed));
                    parameterA = 0;
                }
                break;
            case R.id.tv_detail_value_deaths_indo:
                if (parameterB == 0){
                    tvDeaths.setText(convertValue(deaths));
                    parameterB = 1;
                } else {
                    tvDeaths.setText(changeValue(deaths));
                    parameterB = 0;
                }
                break;
            case R.id.tv_detail_value_recovered_indo:
                if (parameterC == 0){
                    tvRecovered.setText(convertValue(recovered));
                    parameterC = 1;
                } else {
                    tvRecovered.setText(changeValue(recovered));
                    parameterC = 0;
                }
                break;
        }
    }
}