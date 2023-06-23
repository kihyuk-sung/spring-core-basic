package hello.core.beanfind

import hello.core.AppConfig
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextBasicFindTest: FunSpec({
    val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    test("빈 이름으로 조회") {
        val memberService = ac.getBean("memberService", MemberService::class.java)
        memberService should beInstanceOf<MemberServiceImpl>()
    }

    test("이름 없이 빈 타입으로 조회") {
        val memberService = ac.getBean(MemberService::class.java)
        memberService should beInstanceOf<MemberServiceImpl>()
    }

    test("구체 타입으로 빈 조회") {
        val memberService = ac.getBean(MemberServiceImpl::class.java)
        memberService should beInstanceOf<MemberServiceImpl>()
    }

    test("빈 이름으로 조회 실패") {
        shouldThrow<NoSuchBeanDefinitionException> {
            ac.getBean("xxxx")
        }
    }

})
