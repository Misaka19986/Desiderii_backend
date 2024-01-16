package backend.desiderii.desiderii_backend.controller;

import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-15 03:44:04
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private UserService userService;
    @PostMapping("/login")
    public void postUserLogin(@RequestBody User user){
        log.info("正在登录的用户名: {}\n", user.getAlias());
        log.info("使用密码: {}\n", user.getPassword());
        try{
            userService.userLogin(user);
        }catch (Exception e){

        }
    }
}
