# Spring Souce code learn 
1. **Spring解析xml,加载Spring 默认的标签 例如 *import* *alias* *bean* *beans* 等**

  * 解析bean的子元素 ```lookup-method```:称为**获取器注入**,是一种特殊的方法注入,通常指定一个方法返回一个抽象或者父类bean,但是具体的bean类型是通过配置文件确定的

  
  * 解析bean的子元素 ```replaced-method```:通过配置文件可以替换原来某个bean的指定方法,是一个黑科技

   

2. **Bean 的加载**

  * FactoryBean接口
    
     * T getObject(): 返回由FactoryBean创建的bean实例,如果isSingleton()返回true,则该实例会放到Spring单例缓存池中
    
     * boolean isSingleton():返回该实例是否是单例
     
     * Class<T> getObjectType():创建的bean实例类型
   
   当需要获取该bean本身的时候,只需要使用getBean(beanName)的时候加上"&"前缀即可

   