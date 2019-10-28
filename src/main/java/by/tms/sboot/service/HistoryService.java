package by.tms.sboot.service;

import by.tms.sboot.action.ExpressionRecord;

import java.util.List;
import java.util.Map;

public interface HistoryService {

    void createHistoryForUser(long userId);

    void addRecordForUsersHistory(long userId, ExpressionRecord record);

    List<ExpressionRecord> getUserHistory(long userId);

    Map<Long, List<ExpressionRecord>> getHistoryForAllUsers();

}
