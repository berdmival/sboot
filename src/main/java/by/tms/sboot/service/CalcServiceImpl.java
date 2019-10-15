package by.tms.sboot.service;

import org.springframework.stereotype.Service;

@Service("calcService")
public class CalcServiceImpl implements CalcService{

    @Override
    public Double sum(Number num1, Number num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    @Override
    public Double diff(Number num1, Number num2) {
        return num1.doubleValue() - num2.doubleValue();
    }

    @Override
    public Double mult(Number num1, Number num2) {
        return num1.doubleValue() * num2.doubleValue();
    }

    @Override
    public Double div(Number num1, Number num2) {
        return num1.doubleValue() / num2.doubleValue();
    }

}
