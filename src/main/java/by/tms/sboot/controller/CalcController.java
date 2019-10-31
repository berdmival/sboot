package by.tms.sboot.controller;

import by.tms.sboot.action.ExpressionRecord;
import by.tms.sboot.service.CalcService;
import by.tms.sboot.service.HistoryService;
import by.tms.sboot.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ExpressionRecord> calculateExpression(@RequestBody ExpressionRecord expressionRecord) {

        expressionRecord.setCalcService(calcService);
        expressionRecord.setValidator(validator);
        expressionRecord.calculate();

        if (historyService.getUserHistory(0) == null) {
            historyService.createHistoryForUser(0);
        }
        historyService.addRecordForUsersHistory(0, expressionRecord);
        return ResponseEntity.ok(expressionRecord);
    }

    @GetMapping(path = "/history")
    public ResponseEntity<List<ExpressionRecord>> historyOfCalculating() {
        return ResponseEntity.ok(historyService.getUserHistory(0));
    }
}
