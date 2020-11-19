package com.example.coronaupdate.pop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.coronaupdate.CoronaIndoAdapter;
import com.example.coronaupdate.CoronaListener;
import com.example.coronaupdate.R;
import com.example.coronaupdate.model.indo.DataItem;
import com.example.coronaupdate.service.CoronaService;

import java.util.List;

public class PopIndoActivity extends AppCompatActivity {

    public static final String EXTRA_DATE = "extra_date";
    RecyclerView rvRecyclerView;
    CoronaIndoAdapter rvAdapter;
    String sDate;
    EditText searchBar;
    CharSequence searchSequence = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_indo);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int iWidth = displayMetrics.widthPixels;
        int iHeight = displayMetrics.heightPixels;

        getWindow().setLayout((int) (iWidth * .9), (int) (iHeight * .8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 30;
        getWindow().setAttributes(params);

        sDate = getIntent().getStringExtra(EXTRA_DATE);

        searchBar = findViewById(R.id.et_search_indo);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rvAdapter.getFilter(sDate).filter(s);
                searchSequence = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rvRecyclerView = findViewById(R.id.rv_recyclerviewIndo);
        new CoronaService().getProvince(provinceListener);
    }

    CoronaListener<List<DataItem>> provinceListener = new CoronaListener<List<DataItem>>() {
        @Override
        public void onSuccess(List<DataItem> items) {

            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvRecyclerView.setLayoutManager(linearLayoutManager);
            rvAdapter = new CoronaIndoAdapter(items,sDate);
            rvRecyclerView.setAdapter(rvAdapter);

            for(int i = 0; i < items.size(); i++){
                Log.d("Hasil : NAMA KOTA -> ", items.get(i).getProvinsi());
                Log.d("Hasil : CONFIRMED -> ", String.valueOf(items.get(i).getKasusPosi()));
            }
        }

        @Override
        public void onFailed(String msg) {
            Log.d("ISI ERROR", msg);
        }
    };
}