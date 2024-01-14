package backend.desiderii.desiderii_backend.service.impl;

import backend.desiderii.desiderii_backend.entity.Message;
import backend.desiderii.desiderii_backend.mapper.MessageMapper;
import backend.desiderii.desiderii_backend.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-14 09:14:05
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}