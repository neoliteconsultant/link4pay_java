# Link4Pay Java library

The Link4Pay Java library provides integration access to the [Link4Pay Gateway](https://developer.link4.tech/#/).



## Dependencies

* none

Java version >= 8 is required. The Link4Pay Java SDK is tested against Java versions 8 and 11.





## Quick Start Example

````java
import io.link4pay.model.Result;
import io.link4pay.model.transaction.Item;
import io.link4pay.model.transaction.TransactionRequest;
import io.link4pay.model.transaction.TransactionResponse;
import io.link4pay.model.checkout.CheckoutRequest;
import io.link4pay.model.management.ManagementRequest;
import io.link4pay.model.payment.HostedPayment;
import io.link4pay.model.refund.RefundRequest;
import io.link4pay.model.tokenization.TokenizationRequest;
import io.link4pay.model.void_transaction.VoidRequest;

import java.util.ArrayList;
import java.util.List;



public class Application {
    public static void main(String[] args) {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox", "the_api_key",
                        "public_key_absolute_path",
                        "private_key_absolute_path");
        
                List<Item> items = new ArrayList<>();
                items.add(new Item("RBK fitness shoes", "ITM001", "2.49", "2"));
                items.add(new Item(" Nike DriFit T-shirt", "ITM002", "1.99", "1"));
        
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
                        .isApp(true)
                        .items(items).totalValue("19.07").subtotal("19.41").tax("0.03").shippingCharges("0.55")
                        .discountValue("0.40").couponCode("FIRST40").couponCodeDetails("Get $0.4 off on every transaction. *T&C apply")
                        .successURL("http://www.domain.com/SuccessResponse.html").
                        productURL("http://www.domain.com/Product.html")
                        .failURL("http://www.domain.com/FailResponse.htm").
                        cancelURL("http://www.domain.com/CancelResponse.html")
                        .cartURL("http://www.domain.com/Cart.html")
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
}

````

Check io.link4pay.Application for more examples

## Gradle

  With Gradle installed, this package can be built simply by running this command:

    ./gradlew build

  The resulting jar file will be produced in build/libs/.

## Development

See our [development notes](DEVELOPMENT.md).



## License

See the LICENSE file.

