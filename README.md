# zipkin-server
spring-cloud-zipkin
配置被监控的项目：https://github.com/renguoqing123/service


#子项目bootstrap.yml文件添加如下配置，可实现异步消息处理，实时监控
======================
  spring:<br>
     application:<br>
      name: meta-data<br>
    cloud:<br>
        config:<br>
          uri: http://${configurl}:777/<br><br>
          profile: ${profile}<br>
          label: master<br>
        stream:<br>
          kafka:<br>
            binder:<br>
              brokers: 192.168.84.137:9092  #ip地址根据实际kafka集群地址,如果是多个以逗号隔开<br>
              zkNodes: 192.168.84.137:2181  #ip地址根据实际zookeeper集群地址,如果是多个以逗号隔开<br>

#被监控项目配置 <br>
  zipkin:<br>
    #服务端地址<br>
    base-url: http://${configurl}:9411/<br>
    service:<br>
      #本项目服务名
      name: ${spring.application.name}<br>
  sleuth:<br>
    enabled: true #监控开关<br>
    sampler:<br>
      percentage: 1.0 #zipkin采样率，默认为0.1，改为1后全采样，但是会降低接口调用效率<br>
