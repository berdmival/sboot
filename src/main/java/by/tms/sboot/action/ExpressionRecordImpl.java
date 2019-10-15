package by.tms.sboot.action;

import by.tms.sboot.service.CalcService;
import by.tms.sboot.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("expressionRecord")
public class ExpressionRecordImpl implements ExpressionRecord {

    @Value("${num1}")
    private String num1;

    @Value("${num2}")
    private String num2;

    @Value("${action}")
    private String actionType;

    @Autowired
    CalcService calcService;

    @Autowired
    Validator validator;

    @Override
    public void calculate() {
        if (validator.validArgs(num1, num2, actionType)) {
            Double num1d = Double.parseDouble(num1.replaceAll(",", "."));
            Double num2d = Double.parseDouble(num2.replaceAll(",", "."));

            Double result;
            switch (actionType) {
                case ("sum"):
                    result = (Double) calcService.sum(num1d, num2d);
                    break;
                case ("diff"):
                    result = (Double) calcService.diff(num1d, num2d);
                    break;
                case ("mult"):
                    result = (Double) calcService.mult(num1d, num2d);
                    break;
                case ("div"):
                    result = (Double) calcService.div(num1d, num2d);
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
