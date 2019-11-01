package by.tms.sboot.controller;

import by.tms.sboot.action.ExpressionRecord;
import by.tms.sboot.service.CalcService;
import by.tms.sboot.service.HistoryService;
import by.tms.sboot.user.User;
import by.tms.sboot.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
public class CalcController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private CalcService calcService;
    @Autowired
    private Validator validator;

    @PostMapping(path = "/calc")
    public ResponseEntity<ExpressionRecord> calculateExpression(@RequestBody ExpressionRecord expressionRecord,
                                                                WebRequest webRequest) {

        User user = (User) webRequest.getAttribute("user", webRequest.SCOPE_SESSION);
        if (user != null) {
            long userId = user.getId();

            expressionRecord.setCalcService(calcService);
            expressionRecord.setValidator(validator);
            expressionRecord.calculate();

            if (historyService.getUserHistory(userId) == null) {
                historyService.createHistoryForUser(userId);
            }
            historyService.addRecordForUsersHistory(userId, expressionRecord);
            return ResponseEntity.ok(expressionRecord);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/history")
    public ResponseEntity<List<ExpressionRecord>> historyOfCalculating(WebRequest webRequest) {
        User user = (User) webRequest.getAttribute("user", webRequest.SCOPE_SESSION);
        if (user != null) {
            long userId = user.getId();
            return ResponseEntity.ok(historyService.getUserHistory(userId));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
