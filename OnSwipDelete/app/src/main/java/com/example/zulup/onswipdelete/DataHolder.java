package com.example.zulup.onswipdelete;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by zulup on 1/27/2018.
 */

public class DataHolder {

    private  ArrayList<Movies> movieData;
    public static DataHolder mDataHolder;

    public static DataHolder get() {
        if (mDataHolder == null) {
            return mDataHolder = new DataHolder();
        }
        return mDataHolder;
    }

    private DataHolder() {
        this.movieData = new ArrayList<>();
        for(int i=1;i<5;i++){
            movieData.add(new Movies("movie "+i));
        }
    }



    public  ArrayList<Movies> getMovieData() {
        return this.movieData;
    }


}
