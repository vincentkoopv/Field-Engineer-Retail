package com.demo.retail.fieldengineerretaildemo;

import retrofit.http.GET;
import retrofit.http.Query;

public interface IApiMethods {

    @GET("/get/curators.json")
    CuratorPOJO getCurators(
            @Query("api_key") String key
    );
}