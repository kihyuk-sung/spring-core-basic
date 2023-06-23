package hello.core.singleton

import hello.core.AppConfig
import hello.core.member.MemberRepository
import hello.core.member.MemberServiceImpl
import hello.core.order.OrderServiceImpl
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigurationSingletonTest: FunSpec({
    test("configurationTest") {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService = ac.getBean("memberService", MemberServiceImpl::class.java)
        val orderService = ac.getBean("orderService", OrderServiceImpl::class.java)
        val memberRepository = ac.getBean(MemberRepository::class.java)

        memberService.memberRepository shouldBe orderService.memberRepository
        memberService.memberRepository shouldBe memberRepository
    }

    test("configurationDeep") {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)
        println(ac.getBean(AppConfig::class.java).javaClass)
    }
})
