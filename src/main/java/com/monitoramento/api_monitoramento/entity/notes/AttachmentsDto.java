package com.monitoramento.api_monitoramento.entity.notes;


import java.util.HashSet;


public record AttachmentsDto (HashSet<String> file_paths){
}
