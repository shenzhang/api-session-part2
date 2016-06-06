package com.github.shenzhang.service;

import com.github.shenzhang.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * User: Zhang Shen
 * Date: 5/23/16
 * Time: 11:16 PM.
 */
public class MemUserService implements UserService {
    private Map<Long, User> users = newHashMap();

    @Override
    public List<User> getAllUsers() {
        return newArrayList(users.values());
    }

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void updateUser(long id, User user) {
        users.putIfAbsent(id, user);
    }

    @Override
    public Optional<User> getUser(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void deleteUser(long id) {
        users.remove(id);
    }
}
