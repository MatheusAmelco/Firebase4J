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

package com.matheusamelco;

import com.matheusamelco.exception.RealtimeDatabaseException;
import com.matheusamelco.model.RealtimeDatabaseResponse;
import com.matheusamelco.util.HttpUtils;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/**
 * RealtimeDatabase
 *
 * @author Matheus Felipe Amelco
 * @since 05/12/2017
 */
public final class RealtimeDatabase {

    // Constants

    private static final int GET = 1;
    private static final int PUT = 2;
    private static final int PATCH = 3;
    private static final int POST = 4;
    private static final int DELETE = 5;

    private final String url;
    private int method;
    private JSONObject data;

    /**
     * Constructor
     *
     * @param builder - Requisition builder.
     */
    public RealtimeDatabase(Builder builder) {
        this.url = builder.baseUrl;
        this.method = builder.method;
        this.data = builder.data;
    }

    /**
     * @return
     * @throws RealtimeDatabaseException
     */
    public RealtimeDatabaseResponse makeRequest() throws RealtimeDatabaseException {
        Response reponse = null;

        switch (method) {
            case GET:
                reponse = HttpUtils.get(url);
                break;
            case PUT:
                reponse = HttpUtils.put(url, data);
                break;
            case PATCH:
                reponse = HttpUtils.patch(url, data);
                break;
            case POST:
                reponse = HttpUtils.post(url, data);
                break;
            case DELETE:
                reponse = HttpUtils.delete(url);
                break;
            default:
                break;
        }

        return this.processResponse(reponse);
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String baseUrl;
        //private String secureToken;
       // private boolean isSilent;
        private int method;
        private JSONObject data;

        public Builder() {
            this.baseUrl = "";
            //this.secureToken = "";
            //this.isSilent = false;
        }

        public Builder url(String url) {
            this.baseUrl = url;
            return this;
        }

       /* public Builder token(String token) {
            this.secureToken = token;
            return this;
        }

        public Builder silent(boolean silent) {
            this.isSilent = silent;
            return this;
        }*/

        public Builder get() {
            return method(GET, null);
        }

        public Builder delete() {
            return method(DELETE, null);
        }

        public Builder post(JSONObject data) {
            return method(POST, data);
        }

        public Builder put(JSONObject data) {
            return method(PUT, data);
        }

        public Builder patch(JSONObject data) {
            return method(PATCH, data);
        }

        Builder method(int method, JSONObject body) {
            if (method == 0) {
                throw new NullPointerException("method == 0");
            }

            if (body == null && (method == PATCH || method == PUT || method == POST)) {
                throw new IllegalArgumentException("method " + method + " must have a request body.");
            }

            this.method = method;
            this.data = body;
            return this;
        }

        public RealtimeDatabase build() {
            if (baseUrl == null) {
                throw new IllegalStateException("url == null");
            }

            baseUrl = buildFullUri();

            return new RealtimeDatabase(this);
        }

        /**
         * Assemble URL with baseUrl + secureToken + isSilent.
         *
         * @return
         */
        private String buildFullUri() {
            String url = this.baseUrl + ".json";

           /* if (secureToken != null) {
                url += "?auth=" + secureToken;
            }

            if (isSilent) {
                url += "?print=silent";
            }*/

            return url;
        }
    }


    /**
     * Interprets the response from the HTTP/s request made to Firebase.
     *
     * @param httpResponse
     * @return
     * @throws RealtimeDatabaseException
     */
    private RealtimeDatabaseResponse processResponse(Response httpResponse) throws RealtimeDatabaseException {
        if (httpResponse == null) {
            throw new RealtimeDatabaseException("HttpResponse can not be null!");
        }

        ResponseBody body = httpResponse.body();
        int code = httpResponse.code();
        boolean success = false;

        switch (code) {
            case 200:
                success = true;
                break;
            case 204:
                success = true;
                break;
            default:
                break;
        }

        return new RealtimeDatabaseResponse(
                httpResponse.isSuccessful() && success,
                code,
                (body != null ? body.toString() : ""));
    }
}