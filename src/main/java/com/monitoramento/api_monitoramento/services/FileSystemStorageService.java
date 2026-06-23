package com.monitoramento.api_monitoramento.services;

import com.monitoramento.api_monitoramento.interfaces.StorageService;
import com.monitoramento.api_monitoramento.properties.StorageProperties;
import com.monitoramento.api_monitoramento.repository.notes.AttachmentsRepository;
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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);
    private final AttachmentsRepository attachmentsRepository;
    private final NoteServices noteServices;

    @Autowired
    public FileSystemStorageService(StorageProperties properties,
                                    AttachmentsRepository attachmentsRepository,
                                    NoteServices noteServices){
        this.noteServices = noteServices;
        this.attachmentsRepository = attachmentsRepository;
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
    public Set<Path> store(Set<MultipartFile> files) {
        if (files == null || files.isEmpty()){
            throw new RuntimeException("Falha não possuia nenhum anexo");
        }

        return files.stream().map(file -> {
           if (file.isEmpty()){
               throw new RuntimeException("Falha ao armazenar arquivo vazio: "+file.getOriginalFilename());
           }

           try{
               Path destinationFile = this.rootLocation.resolve(file.getOriginalFilename());

               Files.copy(file.getInputStream(),destinationFile);

               return  destinationFile;
           } catch (IOException e ){
               throw new RuntimeException("Erro ao armazenar o arquivo: "+file.getOriginalFilename());
           }
        }).collect(Collectors.toSet());
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
