package com.connector.connect.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(appliesTo = "data_scraped")
public class DataScraped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Data> data;

    public DataScraped() {
    }

    public DataScraped(List<Data> data) {
        this.data = data;
    }
}
