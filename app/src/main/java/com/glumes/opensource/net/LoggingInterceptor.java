package com.glumes.opensource.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by zhaoying on 16/10/21.
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

//        Timber.tag("MainActivity");

        Request request = chain.request();

        long t1 = System.nanoTime();

        Timber.e("Sending request %s on %s%s", request.url(), chain.connection(), request.headers());

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();

        Timber.e("Received response for %s in %.1fms%n%s", response.request().url(), t2 - t1 / 1e6d,
                response.headers());

        return response;

    }


}
