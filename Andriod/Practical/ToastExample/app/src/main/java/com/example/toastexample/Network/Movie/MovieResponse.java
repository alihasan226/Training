package com.example.toastexample.Network.Movie;

import java.util.List;

public class MovieResponse {

    private String original_language;
    private String original_title;
    private String overview;
    private Double popularity;
    private String poster_path;
    private List<production_companies> production_companiesList;
    private List<production_countries> production_countriesList;
    private String release_date;
    private Long revenue;
    private Integer runtime;
    private List<spoken_languages> spoken_languageList;
    private String status;
    private String tagline;
    private String title;
    private String video;
    private Double vote_average;
    private Integer vote_count;
    private Boolean adult;
    private String backdrop_path;
    private String belogns_to_collection;
    private Long budget;
    private List<genres> genresList;
    private String homepage;
    private Integer id;
    private String imdb_id;

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setBelogns_to_collection(String belogns_to_collection) {
        this.belogns_to_collection = belogns_to_collection;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public void setGenresList(List<genres> genresList) {
        this.genresList = genresList;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setProduction_companiesList(List<production_companies> production_companiesList) {
        this.production_companiesList = production_companiesList;
    }

    public void setProduction_countriesList(List<production_countries> production_countriesList) {
        this.production_countriesList = production_countriesList;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public void setSpoken_languageList(List<spoken_languages> spoken_languageList) {
        this.spoken_languageList = spoken_languageList;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }


    public Boolean getAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getBelogns_to_collection() {
        return belogns_to_collection;
    }

    public Long getBudget() {
        return budget;
    }

    public List<genres> getGenresList() {
        return genresList;
    }

    public String getHomepage() {
        return homepage;
    }

    public Integer getId() {
        return id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public List<production_companies> getProduction_companiesList() {
        return production_companiesList;
    }

    public List<production_countries> getProduction_countriesList() {
        return production_countriesList;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Long getRevenue() {
        return revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public List<spoken_languages> getSpoken_languageList() {
        return spoken_languageList;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public String getVideo() {
        return video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }


}
