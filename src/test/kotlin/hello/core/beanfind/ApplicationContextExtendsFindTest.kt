package hello.core.beanfind

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.discount.RateDiscountPolicy
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.haveSize
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextExtendsFindTest: FunSpec({
    val ac = AnnotationConfigApplicationContext(TestConfig::class.java)

    test("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류가 발생한다.") {
        shouldThrow<NoUniqueBeanDefinitionException> {
            ac.getBean(DiscountPolicy::class.java)
        }
    }

    test("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다") {
        ac.getBean("rateDiscountPolicy", DiscountPolicy::class.java) should beInstanceOf<RateDiscountPolicy>()
    }

    test("특정 하위 타입으로 조회") {
        ac.getBean(RateDiscountPolicy::class.java) should beInstanceOf<RateDiscountPolicy>()
    }

    test("부모 타입으로 모두 조회하기") {
         ac.getBeansOfType(DiscountPolicy::class.java)
             .also { it should haveSize(2) }
             .forEach { (key, value) -> println("key = $key value = $value") }
    }

    test("부모 타입으로 모두 조회하기 - Object") {
        ac.getBeansOfType(Any::class.java)
            .forEach { (key, value) -> println("key = $key value = $value") }
    }

}) {
    @Configuration
    class TestConfig {
        @Bean
        fun rateDiscountPolicy(): DiscountPolicy = RateDiscountPolicy()

        @Bean
        fun fixDiscountPolicy(): DiscountPolicy = FixDiscountPolicy()
    }
}
