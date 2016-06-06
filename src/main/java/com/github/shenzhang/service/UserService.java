package com.github.shenzhang.service;

import com.github.shenzhang.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * User: Zhang Shen
 * Date: 5/23/16
 * Time: 11:14 PM.
 */
public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(long id, User user);

    Optional<User> getUser(long id);

    void deleteUser(long id);
}
