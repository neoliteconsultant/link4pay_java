package io.link4pay;

import io.link4pay.model.HostedPayment;
import io.link4pay.model.PaymentRequest;
import io.link4pay.model.Result;
import io.link4pay.security.AESEncryption;


public class Application {
    public static void main(String[] args) {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_merchant_id",
                "the_public_key",
                "the_private_key");

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
        .lastName("Test").emailId("test@test.test").addressLine1("NYC")
        .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                .successURL("http://www.domain.com/SuccessResponse.html").
                productURL("http://www.domain.com/Product.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html")
                .cartURL("http://www.domain.com/Cart.html")
                .showConfirmationPage("true");

        AESEncryption aesEncryption = new AESEncryption();
        String key="DEV20210811001";
        String initializationIV="DEV20210811001DEV20210811001DEV20210811001";
        System.out.println(aesEncryption.encrypt(
                paymentRequest.toJSON(),key.getBytes(),initializationIV.getBytes()));

        /*
        final Result<HostedPayment> hostedPaymentResult = link4PayGateway.paymentService().payWithHPP(paymentRequest);

        if (hostedPaymentResult.isSuccess()) {
            HostedPayment hostedPayment = hostedPaymentResult.getTarget();
            System.out.println("Success!: " + hostedPayment.getResponse());
        }*/
    }
}
