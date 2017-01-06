package com.glumes.opensource.net.entity;

/**
 * Created by zhaoying on 16/11/8.
 */

public class HttpResult<T> {

    private String error ;

    private T results ;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }


}
