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

package com.matheusamelco.model;

/**
 * RealtimeDatabase
 *
 * @author Matheus Felipe Amelco
 * @since 05/12/2017
 */
public class RealtimeDatabaseResponse {

    private final boolean success;
    private final int code;
    private final String message;

    public RealtimeDatabaseResponse(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message.trim();
    }

    /**
     * Returns whether the request to Firebase was successful.
     *
     * @return boolean - If true, the request to Firebase was successful.
     */
    public boolean getSuccess() {
        return this.success;
    }

    /**
     * Returns the HTTP/s status code of the Firebase response.
     *
     * @return Integer - Represents the HTTP/s status code.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Returns a JSONObject with the response message from Firebase.
     *
     * @return JSONObject - Json containing the response message from the
     * firebase.
     */
    public String getMsg() {
        return this.message;
    }

    /**
     * Returns all data coming from the Firebase response.
     *
     * @return String - All data coming from the Firebase response.
     */
    public String getResponse() {

        return RealtimeDatabaseResponse.class.getSimpleName() + " [" +
                "(Success:" + this.success + ") " +
                "(Code:" + this.code + ") " +
                "(Msg:" + this.message + ") " +
                "]";
    }

    @Override
    public String toString() {
        return this.getResponse();
    }
}