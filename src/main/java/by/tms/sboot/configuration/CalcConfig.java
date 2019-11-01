package by.tms.sboot.configuration;

import by.tms.sboot.action.ExpressionRecord;
import by.tms.sboot.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CalcConfig {

    @Bean("users")
    public List<User> users() {
        return new ArrayList<>();
    }

    @Bean("history")
    public Map<Long, List<ExpressionRecord>> history() {
        return new HashMap<>();
    }
}
