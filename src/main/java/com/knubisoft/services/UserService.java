package com.knubisoft.services;

import com.knubisoft.dto.UserDTO;
import com.knubisoft.mapper.UserMapper;
import com.knubisoft.model.User;
import com.knubisoft.persistence.AddressRepository;
import com.knubisoft.persistence.CompanyRepository;
import com.knubisoft.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;


    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(mapper::userToDto)
                .collect(Collectors.toList());
    }

    public void removeUsersAndSave(List<UserDTO> userDTOs) {
        List<User> users = userDTOs.stream().map(mapper::dtoToUser)
                .collect(Collectors.toList());

        userRepository.truncate();
        addressRepository.truncate();
        companyRepository.truncate();

        for (User user : users) {
            user.getAddress().setId(addressRepository.insert(user.getAddress()));
            user.getCompany().setId(companyRepository.insert(user.getCompany()));
            userRepository.insert(user);
        }
    }

}
