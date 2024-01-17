package backend.desiderii.desiderii_backend.service;

import backend.desiderii.desiderii_backend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-15 03:44:04
 */
public interface UserService extends IService<User> {
    User userLogin(User user);
    User userRegister(User user);
}
