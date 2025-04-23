package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("busi_rental_type")
@Schema(name = "RentalType对象", description = "")
public class RentalType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description ="出租类型id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description ="出租类型名称")
    private String typeName;

    @Schema(description ="享受折扣")
    private Double typeDiscount;

    @TableField(exist = false)
    private Double lowDiscount;

    @TableField(exist = false)
    private Double highDiscount;

    @Schema(description ="备注")
    private String remark;

    @Schema(description ="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description ="修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description ="是否删除")
    private Boolean deleted;
}
