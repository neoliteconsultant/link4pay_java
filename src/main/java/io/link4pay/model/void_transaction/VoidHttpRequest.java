package io.link4pay.model.void_transaction;

import com.google.gson.annotations.SerializedName;

public class VoidHttpRequest {
    public String merchantID;
    @SerializedName("void")
    public Void voidResponse;
    public DynamicDescriptor dynamicDescriptor;

    public static class Void{
        public String txnReference;
        public String amount;
    }

    public static class DynamicDescriptor{
        public String name;
        public String email;
        public String mobile;
    }
}
