package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Defence;
import com.example.models.DefenceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DefenceMapper {

    DefenceDto entity2dto(Defence defence);

}
