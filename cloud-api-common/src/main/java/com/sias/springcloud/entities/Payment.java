package com.sias.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Edgar
 * @create 2022-04-25 18:33
 * @faction:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    /*序列化的目的是为了让存放的数据更好的读取出来*/
    private Long id;
    private String serial;
}
