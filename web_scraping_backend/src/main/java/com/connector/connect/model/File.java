package com.connector.connect.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter


@Entity
@Table(appliesTo = "file")
public class File {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileName;

    @Column
    private String dateOfDocument;

    @Column
    private String sourceUrl;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    private String numberOfDocument;
//    Unidimencional com tabela auxiliar
//    @Column
//    @OneToMany(cascade = CascadeType.ALL, targetEntity = Organ.class, fetch=FetchType.EAGER)

//    Unidimensional sem tabela auxiliar - com chave estrangeira
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private List<Organ> organ;


    public File() {
    }

    public File(Long id, String fileName, String sourceUrl, String dateOfDocument, Date date, String numberOfDocument, List<Organ> organ) {
        this.id = id;
        this.fileName = fileName;
        this.sourceUrl = sourceUrl;
        this.dateOfDocument = dateOfDocument;
        this.date = date;
        this.numberOfDocument = numberOfDocument;
        this.organ = organ;
    }

    public File(Long id, String fileName, String sourceUrl, String dateOfDocument, String numberOfDocument, List<Organ> organ) {
        this.id = id;
        this.fileName = fileName;
        this.sourceUrl = sourceUrl;
        this.dateOfDocument = dateOfDocument;
        this.numberOfDocument = numberOfDocument;
        this.organ = organ;
        this.date = new Date();
    }


}
