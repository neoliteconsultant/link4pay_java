package io.link4pay.model.refund;

public class RefundHttpRequest {
    public String merchantID;
    public Refund refund;
    public DynamicDescriptor dynamicDescriptor;

    public static class Refund{
        public String txnReference;
        public String refundInvoiceNo;
        public String refundAmount;
        public String comments;
    }

    public static class DynamicDescriptor{
        public String name;
        public String email;
        public String mobile;
    }
}
