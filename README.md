# Link4Pay Java library

The Link4Pay Java library provides integration access to the Link4Pay Gateway.



## Dependencies

* none

Java version >= 8 is required. The Link4Pay Java SDK is tested against Java versions 8 and 11.





## Quick Start Example

````java
package io.link4pay;

import io.link4pay.model.transactionHttpRequest.HostedPayment;
import io.link4pay.model.transactionHttpRequest.PaymentRequest;
import io.link4pay.model.Result;


public class Application {
    public static void main(String[] args) {
        Link4PayGateway link4PayGateway = new Link4PayGateway("sandbox",
                "the_merchant_id",
                "the_public_key",
                "the_private_key");

        PaymentRequest transactionRequest = new PaymentRequest();
        transactionRequest.merchantID("MER09821725")
                .customerID("CUS77743201").firstName("Joe")
        .lastName("Test").emailId("test@test.test").addressLine1("NYC")
        .addressLine2("NYC").city("NYC").country("US").state("NYC").zip("20912")
                .currencyCode("USD").txnAmount(8.99).txnReference("REF0013tt22112");

        final Result<HostedPayment> hostedPaymentResult = link4PayGateway.paymentService().payWithHPP(transactionRequest);

        if (hostedPaymentResult.isSuccess()) {
            HostedPayment hostedPayment = hostedPaymentResult.getTarget();
            System.out.println("Success!: " + hostedPayment.getResponse());
        }
    }
}

````

## Gradle

  With Gradle installed, this package can be built simply by running this command:

    ./gradlew build

  The resulting jar file will be produced in build/libs/.

## Development

See our [development notes](DEVELOPMENT.md).



## License

See the LICENSE file.

