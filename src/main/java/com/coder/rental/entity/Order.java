package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("busi_order")
@Schema(name = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description ="订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description ="订单编号")
    private String orderNum;

    @Schema(description ="关联车辆id")
    private Integer autoId;

    @Schema(description ="客户id")
    private Integer customerId;

    @Schema(description ="出租时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rentalTime;

    @Schema(description ="出租类型")
    private Integer typeId;

    @Schema(description ="日租金额")
    private Integer rent;

    @Schema(description ="押金")
    private Integer deposit;

    @Schema(description ="起租里程")
    private Integer mileage;

    @Schema(description ="归还时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnTime;

    @Schema(description ="归还里程")
    private Integer returnMileage;

    @Schema(description ="应付租金")
    private Integer rentPayable;

    @Schema(description ="实付租金")
    private Integer rentActual;

    @Schema(description ="押金返还状态 0 未返还  1已返还")
    private Integer depositReturn;

    @Schema(description ="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description ="更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description ="是否删除")
    private Boolean deleted;
    @TableField(exist = false)
    private String customerName;
    @TableField(exist = false)
    private String autoNum;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String customerTel;
    @TableField(exist = false)
    private Double typeDiscount;
}
