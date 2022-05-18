package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Stage;
import com.example.models.StageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageMapper {

    StageDto entity2dto(Stage stage);
}
