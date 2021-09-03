package io.link4pay.model.transaction;

public class TransactionResponse {
    public Response response;

    public static class Response{
        public String description;
        public int responseCode;
    }
}
