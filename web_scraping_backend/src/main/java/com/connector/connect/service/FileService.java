package com.connector.connect.service;

import com.connector.connect.FileUtils;
import com.connector.connect.model.File;
import com.connector.connect.repository.FileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class FileService {

    @Value("${json.save.path}")
    private String saveJsonFilePath;

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

    public List<File> findByNumberOfDocument(String numberOfDocument){
        return repository.findAllByNumberOfDocument(numberOfDocument);
    }

    public File save(File file){
        repository.save(file);
        return file;
    }


    public String write(File file) throws IOException {
        String json = new FileUtils().toJSON(file);
        return new FileUtils().write(json, saveJsonFilePath, file.getFileName());
    }

    public String buildFilename(File file){
        return file.getDateOfDocument().replaceAll(" ", "_") + '_' +new Date().getTime() + ".json";
    }


    public File saveAndWrite(File file){
        try{

            file.setFileName(this.buildFilename(file));
            file.setSourceUrl(saveJsonFilePath + file.getFileName());

            this.repository.save(file);
            this.write(file);
        }catch (HibernateException | IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
