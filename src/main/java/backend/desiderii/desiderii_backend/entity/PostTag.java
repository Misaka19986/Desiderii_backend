package backend.desiderii.desiderii_backend.entity;

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
 * @since 2024-01-19 12:29:48
 */
@Getter
@Setter
@TableName("post_tag")
public class PostTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态id
     */
    private Long pid;

    /**
     * tag id
     */
    private Long tid;
}
