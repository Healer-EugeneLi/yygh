package com.eugeneli.yygh.common.exception;

import com.eugeneli.yygh.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @ClassName: YyghException
 * @Description: 自定义全局异常类
 * @Author EugeneLi
 * @Date: 2021/8/19
 * @Time: 20:14
 */

@Data
@ApiModel(value = "自定义全局异常类")
public class YyghException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * 通过状态码和错误信息创建异常对象
     * @param message
     * @param code
     */
    public YyghException(String message, Integer code){

        super(message);
        this.code=code;

    }


    /**
     * 接收枚举对象
     * @param resultCodeEnum
     */
    public YyghException(ResultCodeEnum resultCodeEnum){

        super(resultCodeEnum.getMessage());
        this.code=code;

    }

    @Override
    public String toString() {
        return "YyghException{" +
                "code=" + code +",messahe"+this.getMessage()+
                '}';
    }
}
