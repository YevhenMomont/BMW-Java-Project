package com.knubisoft.persistence;

import com.knubisoft.model.User;

import java.util.List;

public interface UserRepository {

    void insert(User user);

    List<User> findAll();

    void truncate();

}
