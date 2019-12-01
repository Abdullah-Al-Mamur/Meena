package com.shimul.meena;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MeenaProductClient {
    @GET("cereals?page=1")
    Call<Response> listProducts();

}
