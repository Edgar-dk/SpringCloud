package com.sias.springcloud.dao;

import com.sias.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Edgar
 * @create 2022-04-25 21:22
 * @faction:
 */

@Mapper
/*
1:为了把mapper这个接口交給Spring管理
2:为了不再写mapper映射文件（.xml）
3:为了给mapper接口 自动根据一个添加@Mapper注解的接口生成一个实现类*/
public interface PaymentDao {


    /*01.插入数据
    *
    *    数据插入的形式，是以对象的形式去插入的
    *    插入完成之后，还需要得出一个结果，是否
    *    插入成功*/
    public int create(Payment payment);

    /*02.查询数据*/
    public Payment getPaymentById(@Param("id") Long id);
}
