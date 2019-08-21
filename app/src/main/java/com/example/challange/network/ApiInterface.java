package com.example.challange.network;

import com.example.challange.network.response.ResponsePhotos;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Saqib Khalil
 */
public interface ApiInterface {

    @GET("photos")
    Call<List<ResponsePhotos>> photos();




}
