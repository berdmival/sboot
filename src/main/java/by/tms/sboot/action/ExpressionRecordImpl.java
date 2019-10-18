package by.tms.sboot.action;

import by.tms.sboot.service.CalcService;
import by.tms.sboot.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("expressionRecord")
public class ExpressionRecordImpl implements ExpressionRecord {

    @Autowired
    CalcService calcService;
    @Autowired
    Validator validator;
    @Value("${num1}")
    private Double num1;
    @Value("${num2}")
    private Double num2;
    @Value("${action}")
    private String actionType;

    @Override
    public void calculate() {
        if (validator.validArgs(num1, num2, actionType)) {
            Double result;
            switch (actionType) {
                case ("sum"):
                    result = (Double) calcService.sum(num1, num2);
                    break;
                case ("diff"):
                    result = (Double) calcService.diff(num1, num2);
                    break;
                case ("mult"):
                    result = (Double) calcService.mult(num1, num2);
                    break;
                case ("div"):
                    result = (Double) calcService.div(num1, num2);
                    break;
                default:
                    result = null;
            }
            System.out.println(result);
        } else {
            System.out.println("Args aren't valid");
        }
    }
}
