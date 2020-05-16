package com.connector.connect.controller;


import com.connector.connect.model.File;
import com.connector.connect.service.FileService;
import com.connector.connect.service.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
@RequestMapping("/scraper")
public class ScraperInterfaceController {


    @Autowired
    FileService fileService;

    @GetMapping
    ResponseEntity<Response<List<File>>> getAllFiles() /*@RequestParam("number_of_document") Optional<String> numberOfDocument) */ {
        Response<List<File>> response = new Response<>();
        try {
            System.out.println("Recebendo arquivo get");
            List<File> files = fileService.findAllData();
            response.setData(files);
            return ResponseEntity.ok(response);
        }catch (InternalError e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    ResponseEntity<Response<File>> receiveScrapedData(@RequestBody File file) {
        System.out.println("Recebendo arquivo post");
        Response<File> response = new Response<>();
        try {
            response.setData(fileService.saveAndWrite(file));
            return ResponseEntity.ok(response);
        } catch (InternalError e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/{year}/{month}/{day}")
    ResponseEntity<Response<List<File>>> getFilesByDate(@PathVariable() String year, @PathVariable() String month, @PathVariable() String day) /*@RequestParam("number_of_document") Optional<String> numberOfDocument) */ {
        Response<List<File>> response = new Response<>();
        try {
            List<File> files = fileService.findDataByDate(year, month, day);
            response.setData(files);
            return ResponseEntity.ok(response);
        }catch (InternalError e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/document/{numberOfDocument}")
    ResponseEntity<Response< List<File>>> getFilesByNumberOfDocument(@PathVariable() String numberOfDocument ) /*@RequestParam("number_of_document") Optional<String> numberOfDocument) */ {
        Response< List<File>> response = new Response<>();
        try {
            List<File> files = fileService.findByNumberOfDocument(numberOfDocument);
            response.setData(files);
            return ResponseEntity.ok(response);
        }catch (InternalError e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}


//    SECRETARIA DE ESTADO DE DESENVOLVIMENTO URBANO E OBRAS PÚBLICAS:
//        TERMO ADITIVO A CONTRATO: {count: 3, content: Array(3), scrapedContent: Array(3)}
