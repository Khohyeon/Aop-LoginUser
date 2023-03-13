package shop.mtcoding.aopstudy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.aopstudy.model.User;

@RequiredArgsConstructor
@RestController
public class UserController {
    // final이 붙은 전역 변수는 무조건 new가 되어야 한다.
    private final HttpSession session;

    @GetMapping("/login")
    public String login(HttpSession session) {
        User user = new User(1,"ssar","1234","01022223333");
        session.setAttribute("principal", user);
        return "login ok";
    }

    @GetMapping("/user/1")  // 인증 필요없음
    public String userInfo() {
        return "user ok";
    }

    @GetMapping("/auth/1") // 인증 필요함
    public String authInfo() {
        return "auth ok";
    }
}
