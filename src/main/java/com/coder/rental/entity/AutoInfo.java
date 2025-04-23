package com.coder.rental.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("auto_info")
@Schema(name = "AutoInfo对象", description = "")
public class AutoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="车辆信息id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="车牌号码")
    private String autoNum;

    @Schema(description="厂商id")
    private Integer makerId;

    @Schema(description="品牌id")
    private Integer brandId;

    @Schema(description="车辆类型 0 燃油车  1 电动车  2 混动车")
    private Integer infoType;

    @Schema(description="车辆颜色 ")
    private String color;

    @Schema(description="汽车排量")
    private Double displacement;

    @Schema(description="排量计量单位")
    private String unit;

    @Schema(description="行驶里程")
    private Integer mileage;

    @Schema(description="日租金额")
    private Integer rent;

    @Schema(description="上牌日期")
    private LocalDate registrationDate;

    @Schema(description="车辆图片")
    private String pic;

    @Schema(description="押金 ")
    private Integer deposit;

    @Schema(description="状态 0-未租   1 -已租   2-维保   3-自用")
    private Integer status;

    @Schema(description="创建时间")
    @TableField( fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description="修改时间")
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description="应保次数")
    private Integer expectedNum;

    @Schema(description="实保次数")
    private Integer actualNum;

    @Schema(description="是否删除")
    private Boolean deleted;
    @TableField(exist = false)
    private String makerName;
    @TableField(exist = false)
    private String brandName;
    @TableField(exist = false)
    private LocalDate lowRegistrationDate;
    @TableField(exist = false)
    private LocalDate highRegistrationDate;
    @TableField(exist = false)
    private Double lowRent;
    @TableField(exist = false)
    private Double highRent;
}
