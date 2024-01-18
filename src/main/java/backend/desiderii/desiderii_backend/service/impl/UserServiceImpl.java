package backend.desiderii.desiderii_backend.service.impl;

import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.mapper.UserMapper;
import backend.desiderii.desiderii_backend.service.UserService;
import backend.desiderii.desiderii_backend.utils.EncryptPasswordUtils;
import backend.desiderii.desiderii_backend.utils.JWTUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    private UserMapper userMapper;

    @Override
    public String[] userLogin(User user) {
        String alias = user.getAlias();
        String password = user.getPassword();

        // Get user from database
        User userDB = userMapper.selectOne(new QueryWrapper<User>().eq("alias", alias));

        // Username is not founded or password mismatch
        if(null == user ||
                EncryptPasswordUtils.matchesPassword(password, userDB.getPassword())){
            return null;
        }

        // Gen tokens
        String[] tokens = null;
        tokens[0] = JWTUtils.createAccessToken(userDB.getUid());
        tokens[1] = JWTUtils.createRefreshToken(userDB.getUid());

        return tokens;
    }

    @Override
    public User userRegister(User user){
        return null;
    }
}
