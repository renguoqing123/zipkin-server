# zipkin-server
spring-cloud-zipkin
配置被监控的项目：https://github.com/renguoqing123/service


#外部项目bootstrap.yml文件添加如下配置，可实现异步消息处理，实时监控；（另外需要搭建一套kafka环境）
------------
```
#子项目bootstrap.yml文件添加如下配置，可实现异步消息处理，实时监控
spring:
  application:
    name: meta-data
  cloud:
      config: #配置中心地址
        uri: http://${configurl}:777/
        profile: ${profile}
        label: master
      stream:
        kafka:
          binder:
            brokers: 192.168.84.137:9092  #ip地址根据实际kafka集群地址,如果是多个以逗号隔开
            zkNodes: 192.168.84.137:2181  #ip地址根据实际zookeeper集群地址,如果是多个以逗号隔开

#被监控项目配置
  zipkin:
    #服务端地址
    base-url: http://127.0.0.1:9411/
    service:
      #本项目服务名
      name: ${spring.application.name}
  sleuth:
    enabled: true #监控开关
    sampler:
      percentage: 1.0 #zipkin采样率，默认为0.1，改为1后全采样，但是会降低接口调用效率
```
