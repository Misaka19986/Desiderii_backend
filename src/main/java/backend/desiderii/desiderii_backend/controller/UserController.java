package backend.desiderii.desiderii_backend.controller;

import backend.desiderii.desiderii_backend.config.Response;
import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.service.UserService;
import jakarta.annotation.Resource;
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
    @Resource
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
                logger.info("登录成功！");
                Map<String, String> map = new HashMap<>();
                map.put("accessToken", tokens[0]);
                map.put("refreshToken", tokens[1]);
                return Response.success(100, "Login success", map);
            }
        }catch (Exception e){
            logger.warn("登录失败！");
            return Response.error(101, "Login failed", null);
        }
    }

    @PostMapping("/register")
    public Response<String> postUserRegister(@RequestBody User user){
        try{
            if(!userService.userRegister(user)){
                throw new Exception();
            }else{
                logger.info("注册成功！");
                return Response.success(200, "Register success", null);
            }
        }catch (Exception e){
            logger.warn("注册失败！");
            return Response.error(201, "Register failed", null);
        }
    }

    @PostMapping("/getUserInfo")
    public Response<User> getUserInfo(@RequestBody String name){
        try{
            logger.info("给定名字: {}", name);
            User userDB = userService.getUserByName(name);
            if(null == userDB){
                throw new Exception();
            }else{
                logger.info("查询成功！");
                userDB.setPassword("");
                return Response.success(100, "Get user info success", userDB);
            }
        }catch (Exception e){
            logger.warn("查询失败");
            return Response.error(101, "Get user info failed", null);
        }
    }
}
