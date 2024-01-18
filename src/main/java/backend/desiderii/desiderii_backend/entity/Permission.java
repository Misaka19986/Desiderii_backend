package backend.desiderii.desiderii_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    /**
     * 权限
     */
    private String perm;
}
