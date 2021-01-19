# cloudsecurity
spring security + oauth2 最终实现<br>
因为后面需要修改为jwt 实现, 届时会修改代码, 为留痕 采取分支<br>
>branch <br>
  >>jwt: jwt 实现<br>
  >>master: 最终实现<br>
  >>test: spring security + oauth2 测试<br>
  
出现一次问题, 不知道看解决问题的方案啊
Description:
 
The bean 'clientDetailsService', defined in com.codeus.wecode.service.DBClientDetailsService, could not be registered. A bean with that name has already been defined in BeanDefinition defined in class path resource [org/springframework/security/oauth2/config/annotation/configuration/ClientDetailsServiceConfiguration.class] and overriding is disabled.
 
Action:
(这就是解决方案: 设置 spring.main.allow-bean-definition-overriding=true)
Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true