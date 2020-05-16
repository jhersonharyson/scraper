package com.connector.connect.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;


import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(appliesTo = "term")
public class Term {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Integer count;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DataContent dataContent;


    public Term() {

    }

//    public Term(String description, List<DataContent> dataContent) {
//        this.description = description;
//        this.dataContent = dataContent;
//    }
}
