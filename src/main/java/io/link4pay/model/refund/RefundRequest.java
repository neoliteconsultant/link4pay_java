package io.link4pay.model.refund;

import com.google.gson.Gson;
import io.link4pay.model.Request;
import static io.link4pay.model.refund.RefundHttpRequest.*;

public class RefundRequest extends Request {
    private Gson gson = new Gson();
    private String merchantID;
    private String txnReference;
    public String refundInvoiceNo;
    public double refundAmount;
    public String comments;
    private String name;
    private String email;
    private String mobile;

    public RefundRequest merchantID(String merchantID) {
        this.merchantID = merchantID;
        return this;
    }

    public RefundRequest txnReference(String txnReference) {
        this.txnReference = txnReference;
        return this;
    }

    public RefundRequest refundInvoiceNo(String refundInvoiceNo) {
        this.refundInvoiceNo = refundInvoiceNo;
        return this;
    }

    public RefundRequest refundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
        return this;
    }

    public RefundRequest comments(String comments) {
        this.comments = comments;
        return this;
    }

    public RefundRequest name(String name) {
        this.name = name;
        return this;
    }

    public RefundRequest email(String email) {
        this.email = email;
        return this;
    }

    public RefundRequest mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public String toJSON() {
        RefundHttpRequest refundHttpRequest = new RefundHttpRequest();
        refundHttpRequest.merchantID=merchantID;

        Refund refund = new Refund();
        refund.refundAmount= String.valueOf(refundAmount);
        refund.refundInvoiceNo = refundInvoiceNo;
        refund.txnReference = txnReference;
        refund.comments = comments;
        refundHttpRequest.refund=refund;

        DynamicDescriptor dynamicDescriptor = new DynamicDescriptor();
        dynamicDescriptor.name=name;
        dynamicDescriptor.email=email;
        dynamicDescriptor.mobile=mobile;

        refundHttpRequest.dynamicDescriptor = dynamicDescriptor;


        return gson.toJson(refundHttpRequest);

    }
}
