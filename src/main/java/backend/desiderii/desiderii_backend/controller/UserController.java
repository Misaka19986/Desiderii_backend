package backend.desiderii.desiderii_backend.controller;

import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-17 11:21:25
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserService userService;
    @PostMapping("/login")
    public void postUserLogin(@RequestBody User user){
        logger.info("用户登录: {}", user.getAlias());
        logger.info("输入密码: {}", user.getPassword());
        try{
            userService.userLogin(user);
        }catch (Exception e){
            logger.warn("登录失败！");
        }
    }

    @PostMapping("/register")
    public void postUserRegister(@RequestBody User user){

    }
}
