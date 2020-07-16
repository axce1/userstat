package com.example.userstat.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Visitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "page_id")
    private Long pageId;

    @Column(name = "date")
    private Date date;

    @Column(name = "session")
    private String session;

    public Visitor(Long userId, Long pageId, Date date, String session) {
        this.userId = userId;
        this.pageId = pageId;
        this.date = date;
        this.session = session;
    }
}
