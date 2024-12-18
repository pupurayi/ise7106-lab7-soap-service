## TUTORIAL
[https://www.baeldung.com/spring-boot-soap-web-service](https://www.baeldung.com/spring-boot-soap-web-service)

## WSDL
[http://localhost:8080/ws/currencyCalculator.wsdl](http://localhost:8080/ws/currencyCalculator.wsdl)

## EXAMPLE

```curl
curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws
```