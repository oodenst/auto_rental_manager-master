package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("auto_brand")
@Schema(name = "AutoBrand对象", description = "")

public class AutoBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="品牌id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="厂商id")
    private Integer mid;

    @Schema(description="品牌名称")
    private String brandName;

    @Schema(description="是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    private String makerName;
}
