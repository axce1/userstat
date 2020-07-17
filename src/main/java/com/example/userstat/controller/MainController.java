package com.example.userstat.controller;

import com.example.userstat.dto.JsonResponse;
import com.example.userstat.model.Visitor;
import com.example.userstat.service.VisitorServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    private final VisitorServiceImpl visitorService;

    public MainController(VisitorServiceImpl visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/visitor")
    public List<JsonResponse> addVisitor(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "page_id") Long page_id,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        Visitor visitor = new Visitor(id, page_id, Date.valueOf(LocalDate.now()), session.getId());
        visitorService.createVisit(visitor);

        List<JsonResponse> entities = new ArrayList<>();
        entities.add(visitorService.dayAmountHits());
        entities.add(visitorService.dayAmountUniqueHits());
        return entities;
    }

    @GetMapping("/stat")
    public List<JsonResponse> statPeriod(@RequestParam(name = "from") @Past Date from,
                                        @RequestParam(name = "to") @Past Date to) {
        List<JsonResponse> responses = new ArrayList<>();
        responses.add(visitorService.amountFromToPeriodHits(from, to));
        responses.add(visitorService.amountFromToPeriodUniqueHits(from, to));
        responses.add(visitorService.amountFromToRegularUsers(from, to));

        return responses;
    }
}
