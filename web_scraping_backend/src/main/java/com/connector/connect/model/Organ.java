package com.connector.connect.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(appliesTo = "organ")
public class Organ {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    // bidimensional
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "file_id")
//    private File file;

    @Column
    private String organName;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organ_id")
    private List<Term> terms;

    public Organ() {
    }

    public Organ(Long id, String organName) {
        this.id = id;
        this.organName = organName;
    }
}
