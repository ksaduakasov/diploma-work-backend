package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Role;
import com.example.models.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto entity2dto(Role role);
}
