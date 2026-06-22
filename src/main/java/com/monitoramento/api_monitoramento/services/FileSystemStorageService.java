package com.monitoramento.api_monitoramento.services;

import com.monitoramento.api_monitoramento.interfaces.StorageService;
import com.monitoramento.api_monitoramento.properties.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);

    @Autowired
    public FileSystemStorageService(StorageProperties properties){
        ///(1) if path length default is equal to zero, throw new exception
        if (properties.getLocation().trim().isEmpty()){
            throw new RuntimeException("File upload location can not be Empty");
        }
        /// else, the rootlocation attribute receives the path fromproperties.getlocation
        this.rootLocation = Paths.get(properties.getLocation());
    }


    @Override
    public void init() {
        try{
            //try create default directory in initializate project
            Files.createDirectories(rootLocation);
            logger.info("Create class is sucessfully in "+ rootLocation);
        } catch (IOException e) {
            logger.error("An Exception occurred during a creation path default rootLocation");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void store(MultipartFile file) {

    }

    @Override
    public Stream<Path> loadAll() {
        return Stream.empty();
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
