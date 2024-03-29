package by.tms.sboot.controller;

import by.tms.sboot.service.UserService;
import by.tms.sboot.user.AuthData;
import by.tms.sboot.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> auth(@RequestBody AuthData authData, WebRequest webRequest) {
        User user = userService.login(authData);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        } else {
            webRequest.setAttribute("user", user, webRequest.SCOPE_SESSION);
            return ResponseEntity.ok(user);
        }
    }
}
