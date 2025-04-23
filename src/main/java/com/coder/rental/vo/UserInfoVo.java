package com.coder.rental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo implements Serializable {
    private Integer id;
    private String name;
    private String avatar;
    private String introduction;
    private Object[] roles;
}
