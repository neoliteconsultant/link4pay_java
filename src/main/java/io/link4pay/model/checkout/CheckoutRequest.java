package io.link4pay.model.checkout;

import com.google.gson.Gson;
import io.link4pay.model.Request;
import static io.link4pay.model.checkout.CheckoutHttpRequest.*;

public class CheckoutRequest extends Request {
    private Gson gson = new Gson();
    private String merchantID;
    private String txnReference;
    private double amount;
    private String name;
    private String email;
    private String mobile;

    public CheckoutRequest merchantID(String merchantID) {
        this.merchantID = merchantID;
        return this;
    }

    public CheckoutRequest txnReference(String txnReference) {
        this.txnReference = txnReference;
        return this;
    }

    public CheckoutRequest amount(double amount) {
        this.amount = amount;
        return this;
    }

    public CheckoutRequest name(String name) {
        this.name = name;
        return this;
    }

    public CheckoutRequest email(String email) {
        this.email = email;
        return this;
    }

    public CheckoutRequest mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public String toJSON() {
        CheckoutHttpRequest checkoutHttpRequest = new CheckoutHttpRequest();
        checkoutHttpRequest.merchantID=merchantID;

        Capture capture = new Capture();
        capture.amount=String.valueOf(amount);
        capture.txnReference = txnReference;
        checkoutHttpRequest.capture=capture;

        DynamicDescriptor dynamicDescriptor = new DynamicDescriptor();
        dynamicDescriptor.name=name;
        dynamicDescriptor.email=email;
        dynamicDescriptor.mobile=mobile;

        checkoutHttpRequest.dynamicDescriptor = dynamicDescriptor;


        return gson.toJson(checkoutHttpRequest);

    }
}
