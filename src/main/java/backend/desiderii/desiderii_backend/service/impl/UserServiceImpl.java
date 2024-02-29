package backend.desiderii.desiderii_backend.service.impl;

import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.mapper.UserMapper;
import backend.desiderii.desiderii_backend.security.UserDetailsImpl;
import backend.desiderii.desiderii_backend.service.UserService;
import backend.desiderii.desiderii_backend.utils.EncryptPasswordUtils;
import backend.desiderii.desiderii_backend.utils.JWTUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-17 11:21:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String[] userLogin(User user) {
        String alias = user.getAlias();
        String password = user.getPassword();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(alias, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Get user from database
        // User userDB = userMapper.selectOne(new QueryWrapper<User>().eq("alias", alias));
        // Username is not founded or password mismatch
        if(null == authentication){
            logger.warn("用户名或密码错误");
            return null;
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gen tokens
        String[] tokens = new String[2];
        tokens[0] = JWTUtils.createAccessToken(userDetails.getUid());
        tokens[1] = JWTUtils.createRefreshToken(userDetails.getUid());

        return tokens;
    }

    @Override
    public boolean userRegister(User user){
        logger.info("注册中");
        User userDB = userMapper.selectOne(new QueryWrapper<User>()
                .eq("alias", user.getAlias())
                .or()
                .eq("email", user.getEmail()));

        if(null != userDB){
            logger.warn("用户名或邮箱重复!");
            return false;
        }

        User newUser = new User();
        newUser.setAlias(user.getAlias());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(EncryptPasswordUtils.encryptPassword(user.getPassword()));

        userMapper.insert(newUser);

        return true;
    }

    @Override
    public User getUserByName(String name){
        User userDB = userMapper.selectOne(new QueryWrapper<User>()
                .eq("alias", name));

        if(null == userDB){
            logger.warn("没有该用户！");
            return null;
        }

        return userDB;
    }
}
