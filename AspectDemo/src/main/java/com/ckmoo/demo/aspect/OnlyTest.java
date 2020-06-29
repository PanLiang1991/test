package com.ckmoo.demo.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnlyTest {
    String name() default "lili";

    String sex() default "男";

    int age() default 18;
}
