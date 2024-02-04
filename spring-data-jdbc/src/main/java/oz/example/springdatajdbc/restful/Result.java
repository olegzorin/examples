package oz.example.springdatajdbc.restful;

import java.util.concurrent.Callable;

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

    static Result of(Callable<?> action) {
        try {
            return ok(action.call());
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
    static Result of(Runnable action) {
        try {
            action.run();
            return ok();
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
