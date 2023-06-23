package hello.core.singleton

import hello.core.AppConfig
import hello.core.member.MemberService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest: FunSpec({

    test("스프링 없는 DI 컨테이너") {
        val appConfig = AppConfig()
        val memberService1 = appConfig.memberService()
        val memberService2 = appConfig.memberService()

        memberService1 shouldNotBe  memberService2
    }

    test("싱글톤 패턴을 적용한 객체 사용") {
        val singletonService1 = SingletonService.getInstance()
        val singletonService2 = SingletonService.getInstance()

        singletonService1 shouldBeEqual singletonService2
    }

    test("스프링 컨테이너와 싱글톤") {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService1 = ac.getBean("memberService", MemberService::class.java)
        val memberService2 = ac.getBean("memberService", MemberService::class.java)

        memberService1 shouldBe memberService2
    }
})
