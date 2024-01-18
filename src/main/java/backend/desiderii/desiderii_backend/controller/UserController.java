package backend.desiderii.desiderii_backend.controller;

import backend.desiderii.desiderii_backend.config.Response;
import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-17 11:21:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserService userService;
    @PostMapping("/login")
    public Response<Map> postUserLogin(@RequestBody User user){
        logger.info("用户登录: {}", user.getAlias());
        logger.info("输入密码: {}", user.getPassword());
        try{
            String[] tokens = userService.userLogin(user);
            if(null == tokens){
                throw new Exception();
            }else{
                Map<String, String> map = new HashMap<>();
                map.put("accessToken", tokens[0]);
                map.put("refreshToken", tokens[1]);
                return Response.success(200, "Login success", map);
            }
        }catch (Exception e){
            logger.warn("登录失败！");
            return Response.error(400, "Login failed", null);
        }
    }

    @PostMapping("/register")
    public void postUserRegister(@RequestBody User user){

    }
}
