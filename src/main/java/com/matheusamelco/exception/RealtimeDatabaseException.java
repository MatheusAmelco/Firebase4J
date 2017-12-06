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

package com.matheusamelco.exception;

/**
 * Exception for RealtimeDatabase methods.
 *
 * @author Matheus Felipe Amelco
 * @since 05/12/2017
 */
public class RealtimeDatabaseException extends Throwable {

    /**
     * @param message
     */
    public RealtimeDatabaseException(String message) {
        super(message);
    }

    /**
     * @param message - Message about the exception
     * @param cause   -
     */
    public RealtimeDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
