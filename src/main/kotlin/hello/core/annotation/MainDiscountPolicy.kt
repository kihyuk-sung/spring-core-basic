package hello.core.annotation

import org.springframework.beans.factory.annotation.Qualifier
import java.lang.annotation.Inherited


@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Qualifier("mainDiscountPolicy")
annotation class MainDiscountPolicy
