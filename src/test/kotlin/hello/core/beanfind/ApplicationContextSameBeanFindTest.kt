package hello.core.beanfind

import hello.core.member.MemberRepository
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import hello.core.member.MemoryMemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.be
import io.kotest.matchers.maps.haveSize
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf
import io.kotest.matchers.types.instanceOf
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextSameBeanFindTest: FunSpec({
    val ac = AnnotationConfigApplicationContext(SameBeanConfig::class.java)

    test("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다") {
        shouldThrow<NoUniqueBeanDefinitionException> {
            ac.getBean(MemberRepository::class.java)
        }
    }

    test("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다") {
        ac.getBean("memberRepository1", MemberRepository::class.java) should beInstanceOf<MemberRepository>()
    }

    test("특정 타입을 모두 조회하기") {
        ac.getBeansOfType(MemberRepository::class.java)
            .also { it should haveSize(2) }
            .forEach { (name, bean) -> println("key: $name value: $bean") }

    }

}) {

    @Configuration
    class SameBeanConfig {

        @Bean
        fun memberRepository1(): MemberRepository = MemoryMemberRepository()

        @Bean
        fun memberRepository2(): MemberRepository = MemoryMemberRepository()
    }
}
