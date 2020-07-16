package com.example.userstat.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class VisitorRepositoryTest {

    @Mock
    private VisitorRepository visitorRepoMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_CountAllByDate_Return_True() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countAllByDate(any(Date.class))).thenReturn(42L);
        Long amount = visitorRepoMock.countAllByDate(date);
        assertEquals(42L, amount);
    }

    @Test
    void test_CountAllByDate_Return_False() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countAllByDate(any(Date.class))).thenReturn(12L);
        Long amount = visitorRepoMock.countAllByDate(date);
        assertNotEquals(42L, amount);
    }

    @Test
    void test_CountAllByDateUnique_Return_True() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countAllByDateUnique(any(Date.class))).thenReturn(12L);
        Long amount = visitorRepoMock.countAllByDateUnique(date);
        assertEquals(12L, amount);
    }

    @Test
    void test_CountAllByDateUnique_Return_False() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countAllByDateUnique(any(Date.class))).thenReturn(12L);
        Long amount = visitorRepoMock.countAllByDateUnique(date);
        assertNotEquals(1L, amount);
    }

    @Test
    void test_CountAllByDateBetween_Return_True() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countAllByDateBetween(any(Date.class), any(Date.class))).thenReturn(2L);
        Long amount = visitorRepoMock.countAllByDateBetween(date, date);
        assertEquals(2L, amount);
    }

    @Test
    void test_CountAllByDateBetween_Return_False() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countAllByDateBetween(any(Date.class), any(Date.class))).thenReturn(12L);
        Long amount = visitorRepoMock.countAllByDateBetween(date, date);
        assertNotEquals(0L, amount);
    }

    @Test
    void test_CountAllByDateBetweenUnique_Return_True() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countAllByDateBetweenUnique(any(Date.class), any(Date.class))).thenReturn(1L);
        Long amount = visitorRepoMock.countAllByDateBetweenUnique(date, date);
        assertEquals(1L, amount);
    }

    @Test
    void test_CountAllByDateBetweenUnique_Return_False() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countAllByDateBetweenUnique(any(Date.class), any(Date.class))).thenReturn(11L);
        Long amount = visitorRepoMock.countAllByDateBetweenUnique(date, date);
        assertNotEquals(1L, amount);
    }

    @Test
    void test_CountRegularUsers_Return_True() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countRegularUsers(any(Date.class), any(Date.class))).thenReturn(11L);
        Long amount = visitorRepoMock.countRegularUsers(date, date);
        assertEquals(11L, amount);
    }

    @Test
    void test_CountRegularUsers_Return_False() {
        Date date = Date.valueOf(LocalDate.now());
        when(visitorRepoMock.countRegularUsers(any(Date.class), any(Date.class))).thenReturn(1L);
        Long amount = visitorRepoMock.countRegularUsers(date, date);
        assertNotEquals(11L, amount);
    }
}