package com.example.userstat.repository;

import com.example.userstat.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Long countAllByDate(Date date);

    @Query(value = "select count(distinct v.session) from Visitor v where (v.date = ?1)")
    Long countAllByDateUnique(Date date);

    Long countAllByDateBetween(Date from, Date to);

    @Query(value = "select count(distinct v.session) from Visitor v where (v.date between ?1 and ?2)")
    Long countAllByDateBetweenUnique(Date from, Date to);

    @Query(value = "select count(distinct v.userId) from Visitor v where (v.date between ?1 and ?2) " +
            "group by v.userId having count(v.pageId) >= 10")
    Long countRegularUsers(Date from, Date to);
}
