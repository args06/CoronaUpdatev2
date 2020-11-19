package com.example.coronaupdate.fragment;

import android.content.Intent;
import android.os.Bundle;

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

public class IndonesiaFragment extends Fragment {

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvConfirmed = view.findViewById(R.id.tv_value_confirmed_indo);
        tvDeaths = view.findViewById(R.id.tv_value_deaths_indo);
        tvRecovered = view.findViewById(R.id.tv_value_recovered_indo);
        tvDate = view.findViewById(R.id.tv_last_update_indo);
        btnDetail = view.findViewById(R.id.btn_detail_indo);

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopIndoActivity.class);
                intent.putExtra(PopIndoActivity.EXTRA_DATE, sDate);
                startActivity(intent);
            }
        });

        new CoronaService().getIndo(accListener);
        new CoronaService().getCountries(dateListener);
    }

    CoronaListener<AccResponse> accListener = new CoronaListener<AccResponse>() {
        @Override
        public void onSuccess(AccResponse items) {
            String confirmed = String.valueOf(items.getJumlahKasus());
            tvConfirmed.setText(confirmed);

            String deaths = String.valueOf(items.getMeninggal());
            tvDeaths.setText(deaths);

            String recovered = String.valueOf(items.getSembuh());
            tvRecovered.setText(recovered);
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
}