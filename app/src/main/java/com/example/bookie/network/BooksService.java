package com.example.bookie.network;

import com.example.bookie.models.BookResponse;
import com.example.bookie.models.Volume;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Neelabh Anand on 08/01/21.
 */
public interface BooksService {

    @GET("v1/volumes")
    Call<BookResponse> getBooks(@Query("q") String searchQuery,
                                @Query("maxResults") int maxResults,
                                @Query("orderBy") String relevance,
                                @Query("filter") String filter);

}