package io.link4pay;

import io.link4pay.model.Result;
import io.link4pay.model.checkout.CheckoutRequest;
import io.link4pay.model.management.ManagementRequest;
import io.link4pay.model.payment.HostedPayment;
import io.link4pay.model.refund.RefundRequest;
import io.link4pay.model.tokenization.TokenizationRequest;
import io.link4pay.model.transaction.*;
import io.link4pay.model.void_transaction.VoidRequest;

import java.util.ArrayList;
import java.util.List;

import static io.link4pay.model.transaction._3DSecure.*;


public class Application {
    private final static String publicKey="/Users/tonym/Desktop/Freelancing/Projects/Link4Pay/certs/DEV20210811001-crt.pem";
    private final static String privateKey ="/Users/tonym/Desktop/Freelancing/Projects/Link4Pay/certs/DEV20210811001-key.pem";
    public static void main(String[] args) {
        //saveCard();
        payWithHPP();
    }



    public static void payWithHPP() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox", "MER00000001",
                publicKey,
                privateKey);

        List<Item> items = new ArrayList<>();
        items.add(new Item("RBK fitness shoes", "ITM001", "2.49", "2"));
        items.add(new Item("Nike DriFit T-shirt", "ITM002", "1.99", "1"));

        _3DSecure _3dSecure = new _3DSecure();
        _3dSecure.challengeIndicator = "01";
        _3dSecure.challengeWindowSize = "05";
        _3dSecure.exemptions = new Exemptions(true, true,
                true, true,
                true, true,
                true, "12345");


        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").emailId("test@test.test").addressLine1("NYC")
                .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .shippingFirstName("Joe")
                .shippingLastName("Test").shippingEmailId("test@test.test").shippingAddressLine1("NYC")
                .shippingAddressLine2("NYC").shippingCity("NYC").shippingCountry("US").shippingState("NYC").
                shippingZip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                .isApp(true).payout("true/false")
                ._3DSecure(_3dSecure)
                .items(items).totalValue("19.07").subtotal("19.41").tax("0.03").shippingCharges("0.55")
                .discountValue("0.40").couponCode("FIRST40").couponCodeDetails("Get $0.4 off on every transaction. *T&C apply")
                .dynamicDescriptor(new DynamicDescriptor("COMPANY NAME LTD", "joedoe@example.com", "123456798"))
                .successURL("http://www.domain.com/SuccessResponse.html").
                productURL("http://www.domain.com/Product.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html")
                .cartURL("http://www.domain.com/Cart.html").iFrame("true/false")
                .showConfirmationPage("true").customData1("item").
                customData2("item").customData3("item").
                customData4("item").customData5("item")
                .site("https://link4pay.com");


        final Result<HostedPayment> hostedPaymentResult = link4PayGateway.paymentService().payWithHPP(transactionRequest);
        if (hostedPaymentResult.isSuccess()) {
            HostedPayment hostedPayment = hostedPaymentResult.getTarget();
            System.out.println("Success!: " + hostedPayment.getResponse());
        } else {
            System.out.println("Status Code !: " + hostedPaymentResult.getStatusCode());
            System.out.println("Error!: " + hostedPaymentResult.getMessage());
        }
    }

    public static void payWithoutHPP() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        _3DSecure _3dSecure = new _3DSecure();
        _3dSecure.challengeIndicator = "01";
        _3dSecure.challengeWindowSize = "05";

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").emailId("test@test.test").addressLine1("NYC")
                .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                ._3DSecure(_3dSecure)
                .isApp(true)
                .paymentType("Sale").paymentMode("IDEAL").bankCode("ING")
                .paymentDetail(new PaymentDetail("4111111111111111", "VisaCard",
                        "2025", "12"
                        , "John", "true", "987"))
                .holder("John Doe").bic("GIBAATWWXXX").iban("AT15201112816")
                .successURL("http://www.domain.com/SuccessResponse.html").
                productURL("http://www.domain.com/Product.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html")
                .cartURL("http://www.domain.com/Cart.html")
                .showConfirmationPage("true").
                customData2("item").customData3("item").
                customData4("item").customData5("item")
                .site("https://link4pay.com");


        final Result<HostedPayment> hostedPaymentResult = link4PayGateway.paymentService().makePayment(transactionRequest);
        if (hostedPaymentResult.isSuccess()) {
            HostedPayment hostedPayment = hostedPaymentResult.getTarget();
            System.out.println("Success!: " + hostedPayment.getResponse());
        } else {
            System.out.println("Status Code !: " + hostedPaymentResult.getStatusCode());
            System.out.println("Error!: " + hostedPaymentResult.getMessage());
        }
    }

    public static void capture() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);
        CheckoutRequest checkoutRequest = new CheckoutRequest();
        checkoutRequest.merchantID("MER09821725").txnReference("REF0013tt22112")
                .name("COMPANY NAME LTD").email("joedoe@example.com")
                .mobile("123456798");
        System.out.println(checkoutRequest.toJSON());

        final Result<TransactionResponse> checkoutResponseResult =
                link4PayGateway.paymentService().capture(checkoutRequest);

        if (checkoutResponseResult.isSuccess()) {
            TransactionResponse hostedPayment = checkoutResponseResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        } else {
            System.out.println("Status Code !: " + checkoutResponseResult.getStatusCode());
            System.out.println("Error!: " + checkoutResponseResult.getMessage());
        }
    }

    public static void voidTransaction() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);
        VoidRequest voidRequest = new VoidRequest();
        voidRequest.merchantID("MER09821725").txnReference("REF0013tt22112")
                .name("COMPANY NAME LTD").email("joedoe@example.com")
                .mobile("123456798");

        System.out.println(voidRequest.toJSON());

        final Result<TransactionResponse> voidResponseResult =
                link4PayGateway.paymentService().voidTransaction(voidRequest);

        if (voidResponseResult.isSuccess()) {
            TransactionResponse voidResponse = voidResponseResult.getTarget();
            System.out.println("Success!: " + voidResponse.response.description);
        } else {
            System.out.println("Status Code !: " + voidResponseResult.getStatusCode());
            System.out.println("Error!: " + voidResponseResult.getMessage());
        }
    }

    public static void refundTransaction() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.merchantID("MER09821725").txnReference("REF0013tt22112")
                .refundInvoiceNo("AC0987654321").refundAmount(10.23)
                .comments("First refund")
                .name("COMPANY NAME LTD").email("joedoe@example.com")
                .mobile("123456798");
        System.out.println(refundRequest.toJSON());

        final Result<TransactionResponse> refundResponseResult =
                link4PayGateway.paymentService().refundTransaction(refundRequest);

        if (refundResponseResult.isSuccess()) {
            TransactionResponse refund = refundResponseResult.getTarget();
            System.out.println("Success!: " + refund.response.description);
        } else {
            System.out.println("Status Code !: " + refundResponseResult.getStatusCode());
            System.out.println("Error!: " + refundResponseResult.getMessage());
        }
    }

    public static void tokenizationPayout() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").emailId("test@test.test").addressLine1("NYC")
                .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .shippingFirstName("Joe")
                .shippingLastName("Test").shippingEmailId("test@test.test").shippingAddressLine1("NYC")
                .shippingAddressLine2("NYC").shippingCity("NYC").shippingCountry("US").shippingState("NYC").
                shippingZip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                .hostedPage(true).paymentDetail(new PaymentDetail("476173-994987418383314681-0200"))
                .async(false).payout("true")
                .successURL("http://www.domain.com/SuccessResponse.html").
                productURL("http://www.domain.com/Product.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html")
                .cartURL("http://www.domain.com/Cart.html").customData1("item").
                customData2("item").customData3("item").
                customData4("item").customData5("item");


        final Result<TransactionResponse> tokenizationResult = link4PayGateway.payout().tokenizationPayout(transactionRequest);
        if (tokenizationResult.isSuccess()) {
            TransactionResponse payoutResult = tokenizationResult.getTarget();
            System.out.println("Success!: " + payoutResult.response.description);
        } else {
            System.out.println("Status Code !: " + tokenizationResult.getStatusCode());
            System.out.println("Error!: " + tokenizationResult.getMessage());
        }
    }

    public static void cardPayout() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        _3DSecure _3dSecure = new _3DSecure();
        _3dSecure.deviceFingerprint = new DeviceFingerprint("330",
                "24",
                "en-GB",
                "1080",
                "1920",
                "windows",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36",
                "false",
                "true",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
                "192.1.1.1");
        _3dSecure.exemptions = new Exemptions(true, true,
                true, true,
                true, true,
                true, "12345");
        _3dSecure.externalThreeds = new ExternalThreeds("06",
                "Y",
                "30e789c1-8293-4b2b-8b56-0776c7fff473",
                "6388a97c-6177-4fe1-777a-7bcfda1a7f7f",
                "a7776e64-caa7-7ca1-ad44-aa3ee2f97eda",
                "2.1.0",
                "mK225wGt2bLnnLB0Ul77777HLnU=",
                "MDAwMDAw12345DEyMzQ2Njc4OTA=");
        _3dSecure.challengeIndicator = "01";
        _3dSecure.challengeWindowSize = "05";

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").emailId("test@test.test").addressLine1("NYC")
                .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                .hostedPage(true).paymentDetail(new PaymentDetail("476173-994987418383314681-0200"))
                .async(false).payout("true")
                ._3DSecure(_3dSecure)
                .dynamicDescriptor(new DynamicDescriptor("COMPANY NAME LTD", "joedoe@example.com", "123456798"))
                .successURL("http://www.domain.com/SuccessResponse.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html");


        final Result<TransactionResponse> cardPayoutResult = link4PayGateway.payout().cardPayout(transactionRequest);
        if (cardPayoutResult.isSuccess()) {
            TransactionResponse hostedPayment = cardPayoutResult.getTarget();
            System.out.println("Success!: " + hostedPayment.response.description);
        } else {
            System.out.println("Status Code !: " + cardPayoutResult.getStatusCode());
            System.out.println("Error!: " + cardPayoutResult.getMessage());
        }
    }


    public static void saveCard() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "DEV20210811001",
                publicKey,
                privateKey);

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").mobileNo("3123456789").emailId("test@test.test")
                .cardNumber("4012001037167778").expMonth("05").expYear("2025")
                .nameOnCard("John Smith").encCardNumber("")
                .encAlgorithm("").keySequenceNumber("").acquirer("");


        final Result<TransactionResponse> saveCardResult = link4PayGateway.tokenization().saveCard(tokenizationRequest);
        if (saveCardResult.isSuccess()) {
            TransactionResponse saveCardResponse = saveCardResult.getTarget();
            System.out.println("Success!: " + saveCardResponse.response.description);
        } else {
            System.out.println("Status Code !: " + saveCardResult.getStatusCode());
            System.out.println("Error!: " + saveCardResult.getMessage());
        }
    }

    public static void verifyCard() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.merchantID("MER09821725")
                .tokenID("476173-994987418383314681-0200").showAllCards("true");

        final Result<TransactionResponse> verifyCardResult = link4PayGateway.tokenization().verifyCard(tokenizationRequest);
        if (verifyCardResult.isSuccess()) {
            TransactionResponse verifyCard = verifyCardResult.getTarget();
            System.out.println("Success!: " + verifyCard.response.description);
        } else {
            System.out.println("Status Code !: " + verifyCardResult.getStatusCode());
            System.out.println("Error!: " + verifyCardResult.getMessage());
        }
    }

    public static void verifyToken() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.merchantID("MER09821725")
                .tokenID("476173-994987418383314681-0200");


        final Result<TransactionResponse> verifyTokenResult = link4PayGateway.tokenization().verifyToken(tokenizationRequest);
        if (verifyTokenResult.isSuccess()) {
            TransactionResponse verifyToken = verifyTokenResult.getTarget();
            System.out.println("Success!: " + verifyToken.response.description);
        } else {
            System.out.println("Status Code !: " + verifyTokenResult.getStatusCode());
            System.out.println("Error!: " + verifyTokenResult.getMessage());
        }
    }

    public static void deleteToken() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.customerID("CUS77743201").merchantID("MER09821725")
                .tokenID("476173-994987418383314681-0200");


        final Result<TransactionResponse> deleteTokenResult = link4PayGateway.tokenization().deleteToken(tokenizationRequest);
        if (deleteTokenResult.isSuccess()) {
            TransactionResponse deleteToken = deleteTokenResult.getTarget();
            System.out.println("Success!: " + deleteToken.response.description);
        } else {
            System.out.println("Status Code !: " + deleteTokenResult.getStatusCode());
            System.out.println("Error!: " + deleteTokenResult.getMessage());
        }
    }

    public static void getCustomerToken() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        TokenizationRequest tokenizationRequest = new TokenizationRequest();
        tokenizationRequest.merchantID("MER09821725").customerID("CUS77743201")
                .showAllCards("true");


        final Result<TransactionResponse> verifyCardResult = link4PayGateway.tokenization().verifyCard(tokenizationRequest);
        if (verifyCardResult.isSuccess()) {
            TransactionResponse verifyCard = verifyCardResult.getTarget();
            System.out.println("Success!: " + verifyCard.response.description);
        } else {
            System.out.println("Status Code !: " + verifyCardResult.getStatusCode());
            System.out.println("Error!: " + verifyCardResult.getMessage());
        }
    }

    public static void generatePaymentLink() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
                .lastName("Test").emailId("test@test.test").addressLine1("NYC")
                .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112")
                .hostedPage(true)
                .async(false).payout("true")
                .successURL("http://www.domain.com/SuccessResponse.html").
                productURL("http://www.domain.com/Product.html")
                .failURL("http://www.domain.com/FailResponse.htm").
                cancelURL("http://www.domain.com/CancelResponse.html")
                .cartURL("http://www.domain.com/Cart.html")

                .showConfirmationPage("true");


        final Result<TransactionResponse> generateLinkResult = link4PayGateway.paymentLink().generateLink(transactionRequest);
        if (generateLinkResult.isSuccess()) {
            TransactionResponse generateLinkPayment = generateLinkResult.getTarget();
            System.out.println("Success!: " + generateLinkPayment.response.description);
        } else {
            System.out.println("Status Code !: " + generateLinkResult.getStatusCode());
            System.out.println("Error!: " + generateLinkResult.getMessage());
        }
    }

    /**
     *
     */
    public static void validateSetup() {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "MER00000001",
                publicKey,
                privateKey);

        ManagementRequest managementRequest = new ManagementRequest();
        managementRequest.merchantID("MER09821725");
        managementRequest.payoutEndpoint("https://merchant.test.link4pay.com/api/cardpayments");
        managementRequest.tokenEndpoint("https://merchant.test.link4pay.com/api/tokenization");
        managementRequest.certificate("4A:91:EB:11:73:4A:91:EB:11:CF:12:24:FC:2D:74:4A:91:EB:11");
        managementRequest.apiToken("c23123ase8cc2324d532424234234234b");

        final Result<TransactionResponse> validateSetupResult = link4PayGateway.managementService().validateSetup(managementRequest);


        if (validateSetupResult.isSuccess()) {
            TransactionResponse validateSetup = validateSetupResult.getTarget();
            System.out.println("Success!: " + validateSetup.response.description);
            System.out.println("Status Code !: " + validateSetupResult.getStatusCode());
        } else {
            System.out.println("Error!: " + validateSetupResult.getMessage());
        }
    }
}
