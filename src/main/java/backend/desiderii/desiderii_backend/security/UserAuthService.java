package backend.desiderii.desiderii_backend.security;

import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthService implements UserDetailsService {

    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("alias", username));
        if(null == user){
            return null;
        }


        return null;
    }

}
