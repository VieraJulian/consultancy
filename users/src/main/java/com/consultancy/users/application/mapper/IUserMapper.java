package com.consultancy.users.application.mapper;

import com.consultancy.users.application.dto.UserRequestDTO;
import com.consultancy.users.application.dto.UserResponseDTO;
import com.consultancy.users.domain.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    UserResponseDTO userEntityToUserResponseDto(UserEntity userEntity);
    UserEntity userRequestDtoToUserEntity(UserRequestDTO userRequestDTO);
}
