package hello.core.scan

import hello.core.AutoAppConfig
import hello.core.member.MemberService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoAppConfigTest: FunSpec({
    val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java)

    test("basicScan") {
        val memberService = ac.getBean(MemberService::class.java)
        memberService should beInstanceOf<MemberService>()
    }
})
