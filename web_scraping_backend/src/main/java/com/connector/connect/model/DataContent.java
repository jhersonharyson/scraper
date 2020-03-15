package com.connector.connect.model;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(appliesTo = "data_content")
public class DataContent {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer count;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Data> content;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScrapedContent> scrapedContent;


    public DataContent() {
    }


    public DataContent(Integer count, List<Data> content, List<ScrapedContent> scrapedContent) {
        this.count = count;
        this.content = content;
        this.scrapedContent = scrapedContent;
    }
}
