package com.example.userstat.service;

import com.example.userstat.model.JsonResponse;
import com.example.userstat.model.Visitor;
import com.example.userstat.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public void createVisit(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    @Override
    public JsonResponse dayAmountHits() {
        Long amount = visitorRepository.countAllByDate(Date.valueOf(LocalDate.now()));
        return new JsonResponse("all", amount);
    }

    @Override
    public JsonResponse dayAmountUniqueHits() {
        Long amount = visitorRepository.countAllByDateUnique(Date.valueOf(LocalDate.now()));
        return new JsonResponse("unique", amount);
    }

    @Override
    public JsonResponse amountFromToPeriodHits(Date from, Date to) {
        Long amount = visitorRepository.countAllByDateBetween(from, to);
        return new JsonResponse("all in period", amount);
    }

    @Override
    public JsonResponse amountFromToPeriodUniqueHits(Date from, Date to) {
        Long amount = visitorRepository.countAllByDateBetweenUnique(from, to);
        return new JsonResponse("unique in period", amount);
    }

    @Override
    public JsonResponse amountFromToRegularUsers(Date from, Date to) {
        Long amount = visitorRepository.countRegularUsers(from, to);

        return new JsonResponse(
                "regular",
                amount == null ? 0L : amount);
    }
}
