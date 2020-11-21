package com.example.retrofitpost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonApiHolder {
    @POST("posts")
    Call<Post> createPost(@Body Post post);
}
