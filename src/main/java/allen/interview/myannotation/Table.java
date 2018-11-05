package allen.interview.myannotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//注解作用在类上
@Retention(RetentionPolicy.RUNTIME)//作用在运行时期
@Documented
@Inherited
public @interface Table {
	String value() default "";
}
