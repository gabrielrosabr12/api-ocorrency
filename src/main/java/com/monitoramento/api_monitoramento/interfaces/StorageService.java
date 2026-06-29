package com.monitoramento.api_monitoramento.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    Set<Path> store(Set<MultipartFile> file,String pathTitle);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
