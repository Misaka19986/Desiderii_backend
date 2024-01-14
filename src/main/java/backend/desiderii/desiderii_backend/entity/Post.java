package backend.desiderii.desiderii_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Misakaless19986
 * @since 2024-01-14 09:14:05
 */
@Getter
@Setter
@TableName("post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    /**
     * 所有者id
     */
    private Long uid;

    /**
     * 所属板块id
     */
    private Long tid;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除 0-未删除 1-已删除
     */
    private Boolean isDelete;
}
