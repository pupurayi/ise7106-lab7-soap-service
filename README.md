## TUTORIAL
[https://www.baeldung.com/spring-boot-soap-web-service](https://www.baeldung.com/spring-boot-soap-web-service)

## WSDL
[http://localhost:8080/ws/currencyCalculator.wsdl](http://localhost:8080/ws/currencyCalculator.wsdl)

## EXAMPLE

```curl
curl --location 'http://localhost:8080/ws' \
--header 'content-type: text/xml' \
--data '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://www.hit.ac.zw/ise7106/lab7">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:convertRequest>
            <gs:from>ZWG</gs:from>
            <gs:to>USD</gs:to>
            <gs:amount>20000</gs:amount>
        </gs:convertRequest>
    </soapenv:Body>
</soapenv:Envelope>
'
```

## RESPONSE
```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:convertResponse xmlns:ns2="http://www.hit.ac.zw/ise7106/lab7">
            <ns2:summary>ZWG1.00 : USD0.04</ns2:summary>
            <ns2:oneTo>0.04</ns2:oneTo>
            <ns2:convertedAmount>758.27</ns2:convertedAmount>
        </ns2:convertResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```