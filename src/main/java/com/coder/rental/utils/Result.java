package com.coder.rental.utils;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;
    private Result() {
    }
    /**
     * 创建一个表示操作成功的Result对象。
     *
     * @param <T> 结果对象的类型。
     * @return 返回一个初始化为成功状态的Result对象，包含成功标志、成功代码和成功消息。
    6.2 编写mybatisPlus配置类
    mybatisplus 分页插件配置
     */
    public static <T> Result<T> success() {
// 创建Result对象并初始化为成功状态
        return new Result<T>().setSuccess(true)
                .setCode(ResultCode.SUCCESS).setMessage("操作成功");
    }
    /**
     * 创建一个表示操作成功的 Result 对象。
     *
     * @param data 成功时返回的数据。
     * @param <T> 数据的类型。
     * @return 返回一个初始化为成功状态的 Result 对象，包含指定的数据。
     */
    public static <T> Result<T> success(T data) {
// 初始化 Result 对象为成功状态，并设置相关属性
        return new Result<T>().setSuccess(true)
                .setCode(ResultCode.SUCCESS)
                .setMessage("操作成功").setData(data);
    }
    /**
     * 创建一个表示操作失败的结果对象。
     *
     * @param <T> 结果对象的类型。
     * @return 返回一个初始化为失败状态的 Result 对象，其中包含错误代码和消息。
     */
    public static <T> Result<T> fail(){
// 初始化 Result 对象为失败状态，设置错误代码和消息
        return new Result<T>().setSuccess(false)
                .setCode(ResultCode.ERROR).setMessage("操作失败");
    }
}