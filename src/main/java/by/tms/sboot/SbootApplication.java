package by.tms.sboot;

import by.tms.sboot.action.ActionTypeEnum;
import by.tms.sboot.model.ExpressionRecord;
import by.tms.sboot.service.CalcService;
import by.tms.sboot.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.Scanner;

@SpringBootApplication
public class SbootApplication implements CommandLineRunner {

    @Autowired
    private CalcService calcService;

    @Value("${exit.message}")
    private String exitMessage;

    @Value("${result.message}")
    private String resultIsMessage;

    @Value("${input.num1.message}")
    private String inputNum1Message;

    @Value("${input.num2.message}")
    private String inputNum2Message;

    @Value("${input.action.message}")
    private String inputActionMessage;

    public static void main(String[] args) {
        SpringApplication.run(SbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (Scanner in = new Scanner(System.in)) {
            while (!exitCalc(in)) {

                ExpressionRecord expressionRecord = new ExpressionRecord();

                getExpressionAttributesFromConsole(expressionRecord, in);

                calcService.calculate(expressionRecord);

                System.out.println(resultIsMessage + expressionRecord.getResult());
            }
        }
    }

    private boolean exitCalc(Scanner in) {
        String inputData;
        System.out.print(exitMessage);
        inputData = in.nextLine();
        return inputData.equals("q");
    }

    private void getExpressionAttributesFromConsole(ExpressionRecord expressionRecord, Scanner in) {
        String inputData;
        inputData = getNumberFromConsole(in, inputNum1Message);
        expressionRecord.setNum1(Double.parseDouble(inputData));

        inputData = getNumberFromConsole(in, inputNum2Message);
        expressionRecord.setNum2(Double.parseDouble(inputData));

        inputData = getActionFromConsole(in);
        expressionRecord.setActionType(ActionTypeEnum.valueOf(inputData));
    }

    private String getActionFromConsole(Scanner in) {
        String inputData;
        do {
            System.out.print(inputActionMessage);
            inputData = in.nextLine().toUpperCase();
        }
        while (!Validator.isValidAction(inputData));
        return inputData;
    }

    private String getNumberFromConsole(Scanner in, String s) {
        String inputData;
        do {
            System.out.print(s);
            inputData = in.nextLine();
        }
        while (!Validator.isNumeric(inputData));
        inputData = inputData.replaceAll(",", ".");
        return inputData;
    }
}
