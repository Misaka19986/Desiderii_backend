package backend.desiderii.desiderii_backend.service.impl;

import backend.desiderii.desiderii_backend.entity.Post;
import backend.desiderii.desiderii_backend.mapper.PostMapper;
import backend.desiderii.desiderii_backend.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}
