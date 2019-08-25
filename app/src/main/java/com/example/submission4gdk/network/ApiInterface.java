package com.example.submission4gdk.network;

import com.example.submission4gdk.models.Movie;
import com.example.submission4gdk.models.MovieDetail;
import com.example.submission4gdk.models.Trailer;
import com.example.submission4gdk.models.Tv;
import com.example.submission4gdk.models.TvDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(Constant.MOVIE_PATH)
    Call<Movie> popularMovies(
            @Query("page") int page);

    @GET(Constant.TV_PATH)
    Call<Tv> popularTvShow(
            @Query("page") int page);

    @GET(Constant.DETAIL_MOVIE + "/{movie_id}")
    Call<MovieDetail> movieDetail(
            @Path("movie_id") int movieId);

    @GET(Constant.DETAIL_TV + "/{tv_id}")
    Call<TvDetail> tvDetail(
            @Path("tv_id") int tvId);

    @GET(Constant.DETAIL_MOVIE + "/{movie_id}/" + Constant.VIDEOS)
    Call<Trailer> trailers(
            @Path("movie_id") int movieId);

    @GET(Constant.DETAIL_TV + "/{tv_id}/" + Constant.VIDEOS)
    Call<Trailer> trailers_tv(
            @Path("tv_id") int movieId);
}
