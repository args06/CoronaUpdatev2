package com.example.coronaupdate;

public interface CoronaListener<T> {
    void onSuccess(T items);
    void onFailed(String msg);
}
