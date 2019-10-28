package by.tms.sboot.configuration;

import by.tms.sboot.action.ExpressionRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CalcConfig {

    @Bean("history")
    public Map<Long, List<ExpressionRecord>> history() {
        return new HashMap<>();
    }
}
