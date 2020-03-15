package com.connector.connect.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;


import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(appliesTo = "scraped_content")
public class ScrapedContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DataScraped> dataScraped;

    public ScrapedContent() {
    }

    public ScrapedContent(List<DataScraped> dataScraped) {
        this.dataScraped = dataScraped;
    }
}
