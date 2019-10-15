package by.tms.sboot;

import by.tms.sboot.action.ExpressionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbootApplication implements CommandLineRunner {

    @Autowired
    private ExpressionRecord expressionRecord;

    public static void main(String[] args) {
        SpringApplication.run(SbootApplication.class, args);
    }

    @Override
    public void run(String... args) {
        expressionRecord.calculate();
    }
}
