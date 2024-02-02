package org.example.springjdbc.restful;

public record Result (int status, String error, Object data) {
    public static final int STATUS_OK = 0;
    public static final int STATUS_ERROR = 1;

    static Result ok(Object data) {
        return new Result(STATUS_OK, null, data);
    }

    static Result ok() {
        return ok(null);
    }

    static Result error(String error) {
        return new Result(STATUS_ERROR, error, null);
    }
}
