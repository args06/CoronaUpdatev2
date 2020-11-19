package com.example.coronaupdate.model.indo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProvinceResponse{

	@SerializedName("data")
	private List<DataItem> data;

	public List<DataItem> getData(){
		return data;
	}
}