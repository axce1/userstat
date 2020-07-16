package com.example.userstat.service;

import com.example.userstat.model.JsonResponse;
import com.example.userstat.model.Visitor;

import java.sql.Date;

public interface VisitorService {

    Visitor createVisit(Visitor visitor);

    JsonResponse dayAmountHits();

    JsonResponse dayAmountUniqueHits();

    JsonResponse amountFromToPeriodHits(Date from, Date to);

    JsonResponse amountFromToPeriodUniqueHits(Date from, Date to);

    JsonResponse amountFromToRegularUsers(Date from, Date to);
}
