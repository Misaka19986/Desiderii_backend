package backend.desiderii.desiderii_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("post_tag")
public class PostTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态-标签id
     */
    @TableId(value = "ptid", type = IdType.AUTO)
    private Long ptid;

    /**
     * 动态id
     */
    private Long pid;

    /**
     * tag id
     */
    private Long tid;
}
