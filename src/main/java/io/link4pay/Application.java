package io.link4pay;

import io.link4pay.model.checkout.CheckoutRequest;
import io.link4pay.model.TransactionResponse;
import io.link4pay.model.management.ManagementHttpRequest;
import io.link4pay.model.management.ManagementRequest;
import io.link4pay.model.payment.HostedPayment;
import io.link4pay.model.TransactionRequest;
import io.link4pay.model.Result;
import io.link4pay.model.refund.RefundRequest;
import io.link4pay.model.tokenization.TokenizationRequest;
import io.link4pay.model.void_transaction.VoidRequest;


public class Application {
    public static void main(String[] args) {
        payWithHPP();
    }

    public static void payWithHPP() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
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


        final Result<HostedPayment> hostedPaymentResult = link4PayGateway.paymentService().payWithHPP(transactionRequest);

        if (hostedPaymentResult.isSuccess()) {
            HostedPayment hostedPayment = hostedPaymentResult.getTarget();
            System.out.println("Success!: " + hostedPayment.getResponse());
        }
    }

    public static void payWithoutHPP() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").emailId("test@test.test").addressLine1("NYC")
                .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                .paymentType("Sale").paymentMode("IDEAL").bankCode("ING")
                .holder("John Doe").bic("GIBAATWWXXX").iban("AT15201112816")
                .successURL("http://www.domain.com/SuccessResponse.html").
                productURL("http://www.domain.com/Product.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html")
                .cartURL("http://www.domain.com/Cart.html")
                .showConfirmationPage("true");


        final Result<HostedPayment> hostedPaymentResult = link4PayGateway.paymentService().makePayment(transactionRequest);

        if (hostedPaymentResult.isSuccess()) {
            HostedPayment hostedPayment = hostedPaymentResult.getTarget();
            System.out.println("Success!: " + hostedPayment.getResponse());
        }
    }

    public static void capture() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");
        CheckoutRequest checkoutRequest = new CheckoutRequest();
        checkoutRequest.merchantID("MER09821725").txnReference("REF0013tt22112")
                .name("COMPANY NAME LTD").email("joedoe@example.com")
                .mobile("123456798");
        final Result<TransactionResponse> checkoutResponseResult =
                link4PayGateway.paymentService().capture(checkoutRequest);

        if (checkoutResponseResult.isSuccess()) {
            TransactionResponse hostedPayment = checkoutResponseResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void voidTransaction() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");
        VoidRequest voidRequest = new VoidRequest();
        voidRequest.merchantID("MER09821725").txnReference("REF0013tt22112")
                .name("COMPANY NAME LTD").email("joedoe@example.com")
                .mobile("123456798");
        final Result<TransactionResponse> voidResponseResult =
                link4PayGateway.paymentService().voidTransaction(voidRequest);

        if (voidResponseResult.isSuccess()) {
            TransactionResponse hostedPayment = voidResponseResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void refundTransaction() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.merchantID("MER09821725").txnReference("REF0013tt22112")
                .refundInvoiceNo("AC0987654321").refundAmount(10.23)
                .comments("First refund")
                .name("COMPANY NAME LTD").email("joedoe@example.com")
                .mobile("123456798");
        final Result<TransactionResponse> voidResponseResult =
                link4PayGateway.paymentService().refundTransaction(refundRequest);

        if (voidResponseResult.isSuccess()) {
            TransactionResponse hostedPayment = voidResponseResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void tokenizationPayout() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").emailId("test@test.test").addressLine1("NYC")
                .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                .hostedPage(true).tokenId("476173-994987418383314681-0200")
                .async(false).payout(true)
                .successURL("http://www.domain.com/SuccessResponse.html").
                productURL("http://www.domain.com/Product.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html")
                .cartURL("http://www.domain.com/Cart.html")
                .showConfirmationPage("true");


        final Result<TransactionResponse> payoutResult = link4PayGateway.payout().payout(transactionRequest);


        if (payoutResult.isSuccess()) {
            TransactionResponse hostedPayment = payoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }


    public static void saveCard() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").mobileNo("3123456789").emailId("test@test.test")
                .cardNumber("4012001037167778").expMonth("05").expYear("2025")
                .nameOnCard("John Smith").encCardNumber("")
                .encAlgorithm("").keySequenceNumber(0.0).acquirer("");


        final Result<TransactionResponse> payoutResult = link4PayGateway.tokenization().saveCard(tokenizationRequest);


        if (payoutResult.isSuccess()) {
            TransactionResponse hostedPayment = payoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void verifyCard() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.merchantID("MER09821725")
                .tokenID("476173-994987418383314681-0200").showAllCards(true);


        final Result<TransactionResponse> payoutResult = link4PayGateway.tokenization().verifyCard(tokenizationRequest);


        if (payoutResult.isSuccess()) {
            TransactionResponse hostedPayment = payoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void verifyToken() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.merchantID("MER09821725")
                .tokenID("476173-994987418383314681-0200");


        final Result<TransactionResponse> payoutResult = link4PayGateway.tokenization().verifyToken(tokenizationRequest);


        if (payoutResult.isSuccess()) {
            TransactionResponse hostedPayment = payoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void deleteToken() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.customerID("CUS77743201").merchantID("MER09821725")
                .tokenID("476173-994987418383314681-0200");


        final Result<TransactionResponse> payoutResult = link4PayGateway.tokenization().deleteToken(tokenizationRequest);


        if (payoutResult.isSuccess()) {
            TransactionResponse hostedPayment = payoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void getCustomerToken() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.merchantID("MER09821725")
                .tokenID("476173-994987418383314681-0200").showAllCards(true);


        final Result<TransactionResponse> payoutResult = link4PayGateway.tokenization().verifyCard(tokenizationRequest);


        if (payoutResult.isSuccess()) {
            TransactionResponse hostedPayment = payoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void generatePaymentLink() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").emailId("test@test.test").addressLine1("NYC")
                .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                .hostedPage(true).tokenId("476173-994987418383314681-0200")
                .async(false).payout(true)
                .successURL("http://www.domain.com/SuccessResponse.html").
                productURL("http://www.domain.com/Product.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html")
                .cartURL("http://www.domain.com/Cart.html")

                .showConfirmationPage("true");


        final Result<TransactionResponse> payoutResult = link4PayGateway.paymentLink().generateLink(transactionRequest);


        if (payoutResult.isSuccess()) {
            TransactionResponse hostedPayment = payoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }

    public static void validateSetup() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_public_key",
                "the_private_key");

        ManagementRequest managementRequest = new ManagementRequest();
        managementRequest.merchantID("MER09821725");
        managementRequest.payoutEndpoint("https://merchant.test.link4pay.com/api/cardpayments");
        managementRequest.tokenEndpoint("https://merchant.test.link4pay.com/api/tokenization");
        managementRequest.certificate("4A:91:EB:11:73:4A:91:EB:11:CF:12:24:FC:2D:74:4A:91:EB:11");
        managementRequest.apiToken("c23123ase8cc2324d532424234234234b");

        final Result<TransactionResponse> payoutResult = link4PayGateway.managementService().validateSetup(managementRequest);


        if (payoutResult.isSuccess()) {
            TransactionResponse hostedPayment = payoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        }
    }
}
