package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author teacher_shi
 * @since 2024-10-30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auto_maker")

@Schema(name = "AutoMaker对象", description = "AutoMaker对象")
public class AutoMaker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "厂商id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "厂商名称")
    private String name;

    @Schema(description = "排序字母")
    private String orderLetter;

    @Schema(description = "是否删除 0未删除 1删除")
    private Boolean deleted;
}
