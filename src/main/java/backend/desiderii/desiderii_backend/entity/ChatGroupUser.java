package backend.desiderii.desiderii_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-15 03:44:04
 */
@Getter
@Setter
@TableName("chat_group_user")
public class ChatGroupUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 群聊-成员id
     */
    @TableId(value = "guid", type = IdType.AUTO)
    private Long guid;

    /**
     * 群聊id
     */
    private Long gid;

    /**
     * 成员id
     */
    private Long uid;

    /**
     * 加入时间
     */
    private LocalDateTime joinTime;

    /**
     * 成员权限 0-普通成员 1-管理员 2-群主
     */
    private Byte role;
}
