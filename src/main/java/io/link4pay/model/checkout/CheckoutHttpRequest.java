package io.link4pay.model.checkout;

public class CheckoutHttpRequest {
    public String merchantID;
    public Capture capture;
    public DynamicDescriptor dynamicDescriptor;

    public static class Capture{
        public String txnReference;
        public String amount;
    }

    public static class DynamicDescriptor{
        public String name;
        public String email;
        public String mobile;
    }
}
