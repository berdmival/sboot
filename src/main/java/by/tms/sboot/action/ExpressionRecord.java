package by.tms.sboot.action;

import by.tms.sboot.service.CalcService;
import by.tms.sboot.util.Validator;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class ExpressionRecord {

    private CalcService calcService;
    private Validator validator;

    @Range(min = -9_223_372_036_854_775_808L, max = 9_223_372_036_854_775_807L)
    @NotNull(message = "Задайте первое число выражения")
    private Double num1;
    @Range(min = -9_223_372_036_854_775_808L, max = 9_223_372_036_854_775_807L)
    @NotNull(message = "Задайте второе число выражения")
    private Double num2;
    @NotNull
    private String actionType;
    private Double result;

    public void setCalcService(CalcService calcService) {
        this.calcService = calcService;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void calculate() {
        if (validator.validArgs(num1, num2, actionType)) {
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
        } else {
            result = null;
        }
    }

    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public Double getNum2() {
        return num2;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Double getResult() {
        return result;
    }
}
