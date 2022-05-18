package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Initial;
import com.example.models.InitialDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InitialMapper {

    InitialDto entity2dto(Initial initial);
}
