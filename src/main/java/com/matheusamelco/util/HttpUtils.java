/*
 * Copyright 2017 Matheus Felipe Amelco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.matheusamelco.util;

import com.matheusamelco.exception.RealtimeDatabaseException;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

/**
 * RealtimeDatabase
 *
 * @author Matheus Felipe Amelco
 * @since 05/12/2017
 */
public abstract class HttpUtils {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * @param url
     * @return
     * @throws IOException
     */
    public static Response get(String url) throws RealtimeDatabaseException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return makeRequest(request);
    }

    /**
     * @param url
     * @return
     * @throws IOException
     */
    public static Response put(String url, JSONObject data) throws RealtimeDatabaseException {
        RequestBody body = RequestBody.create(JSON, data.toString());

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        return makeRequest(request);
    }

    /**
     * @param url
     * @return
     * @throws IOException
     */
    public static Response patch(String url, JSONObject data) throws RealtimeDatabaseException {
        RequestBody body = RequestBody.create(JSON, data.toString());

        Request request = new Request.Builder()
                .url(url)
                .patch(body)
                .build();

        return makeRequest(request);
    }

    /**
     * @param url
     * @param data
     * @return
     * @throws IOException
     */
    public static Response post(String url, JSONObject data) throws RealtimeDatabaseException {
        RequestBody body = RequestBody.create(JSON, data.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return makeRequest(request);
    }


    /**
     * @param url
     * @return
     * @throws IOException
     */
    public static Response delete(String url) throws RealtimeDatabaseException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        return makeRequest(request);
    }

    /**
     * @param request
     * @return
     * @throws RealtimeDatabaseException
     */
    private static Response makeRequest(Request request) throws RealtimeDatabaseException {
        Response response;

        try {
            OkHttpClient client = new OkHttpClient();
            response = client.newCall(request).execute();

        } catch (IOException e) {
            throw new RealtimeDatabaseException("Could not receive response from request (" + request.method() + ") @ " + request.url(), e);
        }

        return response;
    }
}