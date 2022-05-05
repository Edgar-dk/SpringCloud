package com.sias.springcloud.what;

/**
 * @author Edgar
 * @create 2022-05-05 15:03
 * @faction:
 */
public class what {

    /*这个是支付模块
     * 端口号是8001，接触数据库的，可以操作数据库
     * 由于其他端口的访问，启动支付服务
     *
     * 这种端口可以有多个，防止一个端口宕机，可以起动
     * 另外一个端口
     *
     * 这个模块是注册到zookeeper中的，这个软件安装在虚拟机上
     * 所以需要在yml中连接上对应的IP*/
}
