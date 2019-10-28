package by.tms.sboot.controller;

import by.tms.sboot.action.ExpressionRecord;
import by.tms.sboot.service.CalcService;
import by.tms.sboot.service.HistoryService;
import by.tms.sboot.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/calc")
public class CalcController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private CalcService calcService;
    @Autowired
    private Validator validator;

    @Value("${actions}")
    private String[] actions;

    @GetMapping
    public ModelAndView calcShow(ModelAndView modelAndView) {

        modelAndView.setViewName("calc.html");

        if (historyService.getUserHistory(0) == null) {
            historyService.createHistoryForUser(0);
        }

        modelAndView.addObject("history", historyService.getUserHistory(0));
        modelAndView.addObject("expression", new ExpressionRecord());
        modelAndView.addObject("actionType", actions);


        return modelAndView;
    }

    @PostMapping
    public ModelAndView calcExpr(@Valid @ModelAttribute("expression") ExpressionRecord expression,
                                 BindingResult bindingResult,
                                 ModelAndView modelAndView
    ) {

        if (!bindingResult.hasErrors()) {
            expression.setCalcService(calcService);
            expression.setValidator(validator);
            expression.calculate();
            if (historyService.getUserHistory(0) == null) {
                historyService.createHistoryForUser(0);
            }
            historyService.addRecordForUsersHistory(0, expression);
            modelAndView.addObject("message", expression.getResult());
            modelAndView.addObject("expression", new ExpressionRecord());
        }

        modelAndView.addObject("actionType", actions);
        modelAndView.addObject("history", historyService.getUserHistory(0));

        modelAndView.setViewName("calc.html");
        return modelAndView;
    }
}
