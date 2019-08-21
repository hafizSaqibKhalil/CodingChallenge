package com.example.challange.network;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Saqib Khalil
 */
public class ServiceGenerator {

    private static ApiInterface mApiInterface = null;

    public static ApiInterface getService() {

        if (mApiInterface == null) {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", getCredentials())
                            //.header("Accept", "application/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            // add logs interceptor
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            // add logging as last interceptor
            httpClient.addInterceptor(logging);


            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(WebConstant.SERVER_URL)
                            .addConverterFactory(GsonConverterFactory.create());

            OkHttpClient client = httpClient.build();
            Retrofit retrofit = builder.client(client).build();
            mApiInterface = retrofit.create(ApiInterface.class);
        }

        return mApiInterface;
    }

    private static String getCredentials() {

        String credentials = "" ;
        final String basic =
               /* "Basic " +*/ Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        return credentials;
    }
}
