/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.identity.common.exceptions;

/**
 * Custom exception for resource registration endpoint.
 */
public class UMAException extends Exception {

    private String errorCode;
    private String errorDescription;
    private int statusCode;

    public UMAException() {

    }

    public UMAException(String message) {

        super(message);
    }

    public UMAException(Throwable throwable) {

        super(throwable);
    }

    public UMAException(String message, Throwable throwable) {

        super(message, throwable);
    }

    public UMAException(String errorcode, String message) {

        super(message);
        this.errorCode = errorcode;
    }

    public UMAException(String errorCode, String message, Throwable throwable) {

        super(message, throwable);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(String errorCode) {

        this.errorCode = errorCode;
    }

    public String getErrorDescription() {

        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {

        this.errorDescription = errorDescription;
    }

    public int getStatusCode() {

        return statusCode;
    }

    public void setStatusCode(int statusCode) {

        this.statusCode = statusCode;
    }
}
