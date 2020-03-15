package com.connector.connect.service;

import com.connector.connect.model.File;
import com.connector.connect.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    public List<File> findAllData(){
        return repository.findAllData();
    }

    public List<File> findDataByDate(String year, String month, String day) throws InternalError{
        SimpleDateFormat fomatter = new SimpleDateFormat("dd/MM/yyyy");
        if(Integer.parseInt(month)> 11 || Integer.parseInt(day) > 31) {
            throw new InternalError();
        }
        try {
            String date = day+"/"+(Integer.parseInt(month)-1)+"/"+year;
            return repository.findDataByDate(fomatter.parse(date));
        } catch (ParseException e) {
            throw new InternalError();
        }
    }

    public File findByNumberOfDocument(String numberOfDocument){
        return repository.findByNumberOfDocument(numberOfDocument);
    }

    public File save(File file){
        repository.save(file);
        return file;
    }
}
