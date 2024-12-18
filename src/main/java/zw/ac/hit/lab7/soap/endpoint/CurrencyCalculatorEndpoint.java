package zw.ac.hit.lab7.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import zw.ac.hit.ise7106.lab7.ConvertRequest;
import zw.ac.hit.ise7106.lab7.ConvertResponse;
import zw.ac.hit.lab7.soap.service.CurrencyConverterService;

@Endpoint
public class CurrencyCalculatorEndpoint {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    @PayloadRoot(
            namespace = "http://www.hit.ac.zw/ise7106/lab7",
            localPart = "convertRequest"
    )
    @ResponsePayload
    public ConvertResponse getUserRequest(@RequestPayload ConvertRequest request) {
        return currencyConverterService.convert(request);
    }
}
