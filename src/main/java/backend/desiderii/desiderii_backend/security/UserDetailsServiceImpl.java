package backend.desiderii.desiderii_backend.security;

import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDB = userMapper.selectOne(new QueryWrapper<User>()
                .eq("alias", username));

        if(null == userDB){
            logger.warn("没有该用户");
            throw new UsernameNotFoundException("没有该用户");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl();

        userDetails.setUid(userDetails.getUid());
        userDetails.setAlias(userDB.getAlias());
        userDetails.setPassword(userDB.getPassword());
        userDetails.setAvatar(userDB.getAvatar());
        userDetails.setEmail(userDB.getEmail());
        userDetails.setPhone(userDB.getPhone());
        userDetails.setCreateTime(userDB.getCreateTime());
        userDetails.setSignature(userDB.getSignature());

        return userDetails;
    }
}
