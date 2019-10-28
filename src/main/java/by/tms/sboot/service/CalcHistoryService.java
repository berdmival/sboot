package by.tms.sboot.service;

import by.tms.sboot.action.ExpressionRecord;
import by.tms.sboot.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("historyService")
public class CalcHistoryService implements HistoryService {

    private final HistoryRepository historyRepository;

    public CalcHistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void createHistoryForUser(long userId) {
        historyRepository.createNewHistory(userId);
    }

    @Override
    public void addRecordForUsersHistory(long userId, ExpressionRecord record) {
        historyRepository.addHistoryRecord(userId, record);
    }

    @Override
    public List<ExpressionRecord> getUserHistory(long userId) {
        return historyRepository.getHistoryById(userId);
    }

    @Override
    public Map<Long, List<ExpressionRecord>> getHistoryForAllUsers() {
        return historyRepository.getAllHistory();
    }
}
