package io.link4pay;

import io.link4pay.model.HostedPayment;
import io.link4pay.model.PaymentRequest;
import io.link4pay.model.Result;


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
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112");

        final Result<HostedPayment> hostedPaymentResult = link4PayGateway.paymentService().payWithHPP(paymentRequest);

        if (hostedPaymentResult.isSuccess()) {
            HostedPayment hostedPayment = hostedPaymentResult.getTarget();
            System.out.println("Success!: " + hostedPayment.getResponse());
        }
    }
}
