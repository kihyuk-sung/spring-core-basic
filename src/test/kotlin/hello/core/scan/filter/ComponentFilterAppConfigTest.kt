package hello.core.scan.filter

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

class ComponentFilterAppConfigTest : FunSpec({
    test("filterScan") {
        val ac = AnnotationConfigApplicationContext(ComponentFilterAppConfig::class.java)
        val beanA = ac.getBean("beanA", BeanA::class.java)

        beanA shouldNotBe null

        shouldThrow<NoSuchBeanDefinitionException> {
            ac.getBean("beanB", BeanB::class.java)
        }
    }

}) {
    @Configuration
    @ComponentScan(
        includeFilters = [Filter(type = FilterType.ANNOTATION, classes = [MyIncludeComponent::class])],
        excludeFilters = [Filter(type = FilterType.ANNOTATION, classes = [MyExcludeComponent::class])],
    )
    class ComponentFilterAppConfig
}
