package backend.desiderii.desiderii_backend.service.impl;

import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.mapper.UserMapper;
import backend.desiderii.desiderii_backend.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-15 03:44:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private UserMapper userMapper;

    @Override
    public User userLogin(User user) {
        // TODO: login
        return null;
    }

    @Override
    public User userRegister(User user){
        return null;
    }
}
