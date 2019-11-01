package by.tms.sboot.service;

import by.tms.sboot.user.AuthData;
import by.tms.sboot.user.User;

import java.util.List;

public interface UserService {

    boolean register(User user);

    User login(String email, String password);

    User logout(long id);

    List<User> findOnlineUsers();

    User login(AuthData authData);
}
