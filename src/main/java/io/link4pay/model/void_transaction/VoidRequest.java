package io.link4pay.model.void_transaction;

import com.google.gson.Gson;
import io.link4pay.model.Request;


public class VoidRequest extends Request {
    private Gson gson = new Gson();
    private String merchantID;
    private String txnReference;
    private double amount;
    private String name;
    private String email;
    private String mobile;

    public VoidRequest merchantID(String merchantID) {
        this.merchantID = merchantID;
        return this;
    }

    public VoidRequest txnReference(String txnReference) {
        this.txnReference = txnReference;
        return this;
    }

    public VoidRequest amount(double amount) {
        this.amount = amount;
        return this;
    }

    public VoidRequest name(String name) {
        this.name = name;
        return this;
    }

    public VoidRequest email(String email) {
        this.email = email;
        return this;
    }

    public VoidRequest mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public String toJSON() {
        VoidHttpRequest voidHttpRequest = new VoidHttpRequest();
        voidHttpRequest.merchantID="MER09821725";

        VoidHttpRequest.Void voidObject= new VoidHttpRequest.Void();
        voidObject.amount="10.00";
        voidObject.txnReference = "REF0013tt22112";
        voidHttpRequest.voidResponse=voidObject;

        VoidHttpRequest.DynamicDescriptor dynamicDescriptor = new VoidHttpRequest.DynamicDescriptor();
        dynamicDescriptor.name="COMPANY NAME LTD";
        dynamicDescriptor.email="joedoe@example.com";
        dynamicDescriptor.mobile="123456798";

        voidHttpRequest.dynamicDescriptor = dynamicDescriptor;


        return gson.toJson(voidHttpRequest);

    }
}
