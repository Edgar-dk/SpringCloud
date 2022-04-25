package com.sias.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Timer;

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
    private T       data;


    /*02.定义两个消息*/
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
