package com.connector.connect;

import com.connector.connect.model.File;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

import java.io.IOException;

public class FileUtils {

    public String toJSON(File file){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(file);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InternalError();
        }
    }

    public String write(String data, String path, String name) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(path + name);
            myWriter.write(data);
            myWriter.close();

            return path + name;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            throw new IOException();
        }

    }

}
