package backend.desiderii.desiderii_backend.service.impl;

import backend.desiderii.desiderii_backend.entity.Comment;
import backend.desiderii.desiderii_backend.mapper.CommentMapper;
import backend.desiderii.desiderii_backend.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
