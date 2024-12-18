package zw.ac.hit.lab7.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapWebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }


    @Bean
    public XsdSchema currencyCalculatorSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/currencyCalculator.xsd"));
    }

    @Bean(name = "currencyCalculator")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema currencyCalculatorSchema) {

        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(currencyCalculatorSchema);
        definition.setLocationUri("/ws");
        definition.setPortTypeName("CurrencyCalculatorServicePort");
        definition.setTargetNamespace("http://www.hit.ac.zw/ise7106/lab7");
        return definition;
    }


}
