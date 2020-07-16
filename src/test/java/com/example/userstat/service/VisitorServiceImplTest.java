package com.example.userstat.service;

import com.example.userstat.model.JsonResponse;
import com.example.userstat.model.Visitor;
import com.example.userstat.repository.VisitorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class VisitorServiceImplTest {


    @Mock
    private VisitorRepository repoMock;

    @InjectMocks
    private VisitorServiceImpl serviceMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_createVisit_True() {
        UUID sessionID = UUID.randomUUID();
        Visitor visitor = new Visitor(1L, 42L, Date.valueOf(LocalDate.now()), sessionID.toString());

        when(repoMock.save(isA(Visitor.class))).thenAnswer(new Answer<Visitor>() {
            @Override
            public Visitor answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] arguments = invocationOnMock.getArguments();
                return (Visitor) arguments[0];
            }
        });
        Visitor createdVisitor = serviceMock.createVisit(visitor);
        assertEquals(visitor.getSession(), createdVisitor.getSession());
    }

    @Test
    void dayAmountHits() {
        when(repoMock.countAllByDate(any(Date.class))).thenReturn(45L);
        JsonResponse resp = serviceMock.dayAmountHits();
        assertEquals(45L, resp.getCount());
        assertEquals("allDay", resp.getDescription());
    }

    @Test
    void dayAmountUniqueHits() {
        when(repoMock.countAllByDateUnique(any(Date.class))).thenReturn(4L);
        JsonResponse resp = serviceMock.dayAmountUniqueHits();
        assertEquals(4L, resp.getCount());
        assertEquals("uniqueDay", resp.getDescription());
    }

    @Test
    void amountFromToPeriodHits() {
        Date date = Date.valueOf(LocalDate.now());
        when(repoMock.countAllByDateBetween(any(Date.class), any(Date.class))).thenReturn(5L);
        JsonResponse resp = serviceMock.amountFromToPeriodHits(date, date);
        assertEquals(5L, resp.getCount());
        assertEquals("allInPeriod", resp.getDescription());
    }

    @Test
    void amountFromToPeriodUniqueHits() {
        Date date = Date.valueOf(LocalDate.now());
        when(repoMock.countAllByDateBetweenUnique(any(Date.class), any(Date.class))).thenReturn(1L);
        JsonResponse resp = serviceMock.amountFromToPeriodUniqueHits(date, date);
        assertEquals(1L, resp.getCount());
        assertEquals("uniqueInPeriod", resp.getDescription());
    }

    @Test
    void amountFromToRegularUsers() {
        Date date = Date.valueOf(LocalDate.now());
        when(repoMock.countRegularUsers(any(Date.class), any(Date.class))).thenReturn(8L);
        JsonResponse resp = serviceMock.amountFromToRegularUsers(date, date);
        assertEquals(8L, resp.getCount());
        assertEquals("regularInPeriod", resp.getDescription());
    }
}