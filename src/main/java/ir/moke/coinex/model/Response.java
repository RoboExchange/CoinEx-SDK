package ir.moke.coinex.model;

public record Response<T>(Integer code,
                          T data,
                          String message,
                          Pagination pagination) {
}
