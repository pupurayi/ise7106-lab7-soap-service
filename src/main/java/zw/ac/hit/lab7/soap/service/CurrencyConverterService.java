package zw.ac.hit.lab7.soap.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import zw.ac.hit.ise7106.lab7.ConvertRequest;
import zw.ac.hit.ise7106.lab7.ConvertResponse;
import zw.ac.hit.ise7106.lab7.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyConverterService {

    private static final Map<String, Rate> rates = new HashMap<>();

    @PostConstruct
    public void initData() {
        // always put in alphabetical order i.e. FROM must come before to on the alphabet i.e. USD:ZWG
        rates.put("USD:ZWG", new Rate("USD", "ZWG", BigDecimal.valueOf(26.3757)));
        rates.put("USD:ZAR", new Rate("USD", "ZAR", BigDecimal.valueOf(18.07)));
    }

    public ConvertResponse convert(ConvertRequest request) {
        Currency from = request.getFrom();
        Currency to = request.getTo();
        BigDecimal amount = request.getAmount();

        List<String> currencyCombinationList = new ArrayList<>();
        currencyCombinationList.add(from.value());
        currencyCombinationList.add(to.value());
        currencyCombinationList.sort(String::compareTo);

        String combination = String.join(":", currencyCombinationList);
        if (rates.containsKey(combination)) {
            Rate rate = rates.get(combination);
            ConvertResponse response = new ConvertResponse();
            if (rate.getFrom().equals(from.value()) && rate.getTo().equals(to.value())) {
                response.setSummary(String.format("%S1.00 : %S%s", from.value(), to.value(), rate.getRate().toPlainString()));
                response.setOneTo(rate.getRate());
                response.setConvertedAmount(amount.multiply(rate.getRate()));
            } else {
                BigDecimal invertedRate = BigDecimal.ONE.divide(rate.getRate(), 2, RoundingMode.HALF_UP);
                response.setSummary(String.format("%S1.00 : %S%s", from.value(), to.value(), invertedRate.toPlainString()));
                response.setOneTo(BigDecimal.ONE.divide(rate.getRate(), 2, RoundingMode.HALF_UP));
                response.setConvertedAmount(amount.divide(rate.getRate(), 2, RoundingMode.HALF_UP));
            }
            return response;
        }
        return null;
    }
}
