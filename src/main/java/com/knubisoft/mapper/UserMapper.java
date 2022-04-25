package com.knubisoft.mapper;

import com.knubisoft.dto.UserDTO;
import com.knubisoft.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User dtoToUser(UserDTO userDTO);

    UserDTO userToDto(User user);

}
