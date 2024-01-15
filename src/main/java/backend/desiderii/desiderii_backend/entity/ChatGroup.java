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
@TableName("chat_group")
public class ChatGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 群聊id
     */
    @TableId(value = "gid", type = IdType.AUTO)
    private Long gid;

    /**
     * 群主id
     */
    private Long uid;

    /**
     * 群聊名称
     */
    private String title;

    /**
     * 简介
     */
    private String intro;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
