package by.tms.sboot.controller;

import by.tms.sboot.action.ExpressionRecord;
import by.tms.sboot.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private HistoryService historyService;

    @GetMapping(path = "/")
    public ResponseEntity<Map<Long, List<ExpressionRecord>>> index() {
        return ResponseEntity.ok(historyService.getHistoryForAllUsers());
    }
}
