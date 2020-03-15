package com.connector.connect.repository;


import com.connector.connect.model.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {

    @Query("SELECT f FROM File f ")
    public List<File> findAllData();


    @Query("SELECT f FROM File f WHERE f.date = :date")
    public List<File> findDataByDate(@Param("date") Date date);


    public File findByNumberOfDocument(String numberOfDocument);
}
