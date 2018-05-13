###java注解总结
>Annotation是Java提供的一种元程序中的元素关联任何信息和元数据（metadata）的途径和方法。
Annotation(注解)是一个接口，程序可以在编译期通过AbstractProcessor处理注解元素，
或者运行期通过反射来获取指定程序元素的Annotation对象，然后通过Annotation对象来获取其中的元数据

####首先了解元注解
  元注解即为注解的注解，用来定义新的注解使用，如下：
  * @Target ; 该注解用来声明注解作用的范围或使用位置
    * CONSTRUCTOR:用于描述构造器
    * FIELD:用于描述域
    * LOCAL_VARIABLE:用于描述局部变量
    * METHOD:用于描述方法
    * PACKAGE:用于描述包
    * PARAMETER:用于描述参数
    * TYPE:用于描述类、接口(包括注解类型) 或enum声明
  * @Retention ;该注解声明了注解的声明周期，在什么时期存在
    * SOURCE:在源文件中有效
    * CLASS:在class文件中有效
    * RUNTIME:在运行时有效（即运行时保留）
  * @Documented ；是一个标记注解，有该注解的注解会在java文档中保留
  * @Inherited 
  该注解表明子类是有继承了父类的注解。比如一个注解被该元注解修饰，并且该注解的作用在父类上，
  那么子类有持有该注解。如果注解没有被该元注解修饰，则子类不持有父类的注解。
 
####自定义注解实战：详见myannotation包下边的类实现
使用元注解定义了注解的作用于，作用时期，参数类型等以后，一般是通过java反射机制
来首先判断当前类是否有@MyAnnotation,有则进行相应的逻辑处理，包括执行特定动作
获取注解参数等行为。 还可以获取在该类下添加了@MYAnnotation注解所有方法，对获取到
的所有方法进行逻辑操作。

##案例实战（模仿ORM框架实现生成sql语句）

1. 自定义@Table @Column 注解
2. 定义一个实体类User，添加一些字段，并使用自定义注解
3. new User的对象，添加一些字段
4. 通过反射和注解来实现sql语句的生成

详细实现请查看 myannotation包下的相关类