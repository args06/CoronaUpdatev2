package com.example.coronaupdate.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.coronaupdate.CoronaListener;
import com.example.coronaupdate.R;
import com.example.coronaupdate.model.global.CountriesItem;
import com.example.coronaupdate.model.indo.AccResponse;
import com.example.coronaupdate.pop.PopGlobalActivity;
import com.example.coronaupdate.pop.PopIndoActivity;
import com.example.coronaupdate.service.CoronaService;

import java.util.List;

public class IndonesiaFragment extends Fragment implements View.OnClickListener{

    public IndonesiaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indonesia, container, false);
    }

    TextView tvConfirmed, tvDeaths, tvRecovered, tvDate;
    Button btnDetail;
    String sDate;
    String confirmed, deaths, recovered;
    int parameterA = 0, parameterB = 0, parameterC = 0;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvConfirmed = view.findViewById(R.id.tv_value_confirmed_indo);
        tvDeaths = view.findViewById(R.id.tv_value_deaths_indo);
        tvRecovered = view.findViewById(R.id.tv_value_recovered_indo);
        tvDate = view.findViewById(R.id.tv_last_update_indo);
        btnDetail = view.findViewById(R.id.btn_detail_indo);

        btnDetail.setOnClickListener(this);
        tvConfirmed.setOnClickListener(this);
        tvDeaths.setOnClickListener(this);
        tvRecovered.setOnClickListener(this);

        new CoronaService().getIndo(accListener);
        new CoronaService().getCountries(dateListener);
    }

    CoronaListener<AccResponse> accListener = new CoronaListener<AccResponse>() {
        @Override
        public void onSuccess(AccResponse items) {
            confirmed = String.valueOf(items.getJumlahKasus());
            tvConfirmed.setText(convertValue(confirmed));

            deaths = String.valueOf(items.getMeninggal());
            tvDeaths.setText(convertValue(deaths));

            recovered = String.valueOf(items.getSembuh());
            tvRecovered.setText(convertValue(recovered));

            parameterA = 1;
            parameterB = 1;
            parameterC = 1;
        }

        @Override
        public void onFailed(String msg) {
            Log.d("ISI ERROR", msg);
        }
    };

    CoronaListener<List<CountriesItem>> dateListener = new CoronaListener<List<CountriesItem>>() {
        @Override
        public void onSuccess(List<CountriesItem> items) {
            String value = String.valueOf(items.get(0).getDate());
            String month;
            int checkMonth = Integer.parseInt(value.substring(5,7));
            switch (checkMonth){
                case 1 : month = "January"; break;
                case 2 : month = "February"; break;
                case 3 : month = "March"; break;
                case 4 : month = "April"; break;
                case 5 : month = "May"; break;
                case 6 : month = "June"; break;
                case 7 : month = "July"; break;
                case 8 : month = "August"; break;
                case 9 : month = "September"; break;
                case 10 : month = "October"; break;
                case 11 : month = "November"; break;
                case 12 : month = "December"; break;
                default: month = "Month";
            }
            sDate = "Last Update : " + value.substring(8,10) + " " + month + " " + value.substring(0,4);
            tvDate.setText(sDate);
        }

        @Override
        public void onFailed(String msg) {
            Log.d("ISI ERROR", msg);
        }
    };

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
        switch (v.getId()) {
            case R.id.btn_detail_indo:
                Intent intent = new Intent(getContext(), PopIndoActivity.class);
                intent.putExtra(PopIndoActivity.EXTRA_DATE, sDate);
                startActivity(intent);
                break;
            case R.id.tv_value_confirmed_indo:
                if (parameterA == 0){
                    tvConfirmed.setText(convertValue(confirmed));
                    parameterA = 1;
                } else {
                    tvConfirmed.setText(changeValue(confirmed));
                    parameterA = 0;
                }
                break;
            case R.id.tv_value_deaths_indo:
                if (parameterB == 0){
                    tvDeaths.setText(convertValue(deaths));
                    parameterB = 1;
                } else {
                    tvDeaths.setText(changeValue(deaths));
                    parameterB = 0;
                }
                break;
            case R.id.tv_value_recovered_indo:
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