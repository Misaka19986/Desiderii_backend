package backend.desiderii.desiderii_backend.service;

import backend.desiderii.desiderii_backend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-17 11:21:25
 */
public interface UserService extends IService<User> {

    String[] userLogin(User user);
    boolean userRegister(User user);
}
