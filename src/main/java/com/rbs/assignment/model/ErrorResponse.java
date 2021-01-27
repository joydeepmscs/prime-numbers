package com.rbs.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
/**
 *  @author Joydeep Paul
 * */
public class ErrorResponse {
    @JsonProperty("code")
    private String code = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("root_cause")
    private String rootCause = null;

    public ErrorResponse code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Application specific standard error code
     *
     * @return code
     **/
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Error message stating the reason
     *
     * @return message
     **/
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse rootCause(String rootCause) {
        this.rootCause = rootCause;
        return this;
    }

    /**
     * Root cause of the Error
     *
     * @return rootCause
     **/
    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorResponse errorResponse = (ErrorResponse) o;
        return Objects.equals(this.code, errorResponse.code) &&
                Objects.equals(this.message, errorResponse.message) &&
                Objects.equals(this.rootCause, errorResponse.rootCause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, rootCause);
    }


}
