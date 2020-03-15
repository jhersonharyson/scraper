package com.connector.connect.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(appliesTo = "data")
public class Data {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4000)
    private String data;


    public Data() {
    }

    public Data(String data) {
        this.data = data;
    }
}
