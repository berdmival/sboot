package by.tms.sboot.controller;

import by.tms.sboot.service.UserService;
import by.tms.sboot.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(path = "/logout")
public class LogoutController {

    private final UserService userService;

    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> logout(WebRequest webRequest) {
        User user = (User) webRequest.getAttribute("user", webRequest.SCOPE_SESSION);
        userService.logout(user.getId());
        webRequest.removeAttribute("user", webRequest.SCOPE_SESSION);
        return ResponseEntity.ok(user);
    }
}
