package com.sias.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;

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

}
