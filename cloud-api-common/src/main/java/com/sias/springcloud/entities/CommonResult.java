package com.sias.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Edgar
 * @create 2022-04-25 19:29
 * @faction:
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T>{


    /*这个是后台操作数据是否成功的标志
    *
    * 一般是标识符（0/1），还有message，失败的话，说明失败的原因
    * 这里面的T当前的一个属性的类型暂时不知道，可以调用的时候，在去传递
    * 第三个参数，表示全部参数的属性*/


    private Integer code;
    private String message;
    private T data;


    /*02.定义两个消息*/
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
    /*public CommonResult() {
    }
    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }*/
}
