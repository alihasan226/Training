package com.example.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Jsonplaceholder {

    @GET("posts")//Here we declare the relative URL
    Call<List<Post>> getPost(@Query("userId") Integer[] userId,
                             @Query("_sort")    String sort,
                             @Query("_order")   String order
    );//we only declare the method here    and we use this get useriD of respective post.

    @GET("posts/{id}/comments")//Here the end point is posts/{id}/comment       and we use this to get the comment of respective post id
    Call<List<PostComment>> getComment(@Path("id") int postId);


    @Headers("Cache-Control: max-age=640000")
    @GET("posts")//Here we declare the relative URL
    Call<List<Post>> getPost(@QueryMap Map<String,String> parameters);


    @GET
    Call<List<PostComment>> getComment(@Url String url);


    @POST("posts")
    Call<Post> createPost(@Body Post post);

        //Call<Post> we get a post back when we execute our post request
        //HERE we serializing the post object into the json format after that put that into the HTTP body and server can reterive json can process

    //if api get only the xml format than we will have to use different converter


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createpost(@Field("userId") int userId,
                          @Field("title") String title,
                          @Field("body") String body);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createpost(@FieldMap Map<String,String> paramters);

    //it denotes the request body will use form URL encoding . Field should be declared as parameters and annotation with @Field.


    @Headers({"Static-Header1: 123","Static-Header2:456"})
    @PUT("posts/{id}")
    Call<Post> putPost(@Header("Dynamic-Header") String header,@Path("id") int id,@Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id,@Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}

//     https://jsonplaceholder.typicode.com/comments?postId=1

//some time we use in post type connection method /posts?userId=1&_sort=id&_order=desc  Hwew  ? indicate our Query start  and & indicate another second paramter added with it.