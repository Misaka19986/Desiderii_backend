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
@TableName("topic")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 板块id
     */
    @TableId(value = "tid", type = IdType.AUTO)
    private Long tid;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
