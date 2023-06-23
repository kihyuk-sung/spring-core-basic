package hello.core.xml

import hello.core.member.MemberService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf
import org.springframework.context.support.GenericXmlApplicationContext

class XmlAppContext: FunSpec({
    test("xml load") {
        val ac = GenericXmlApplicationContext("appConfig.xml")
        ac.getBean("memberService", MemberService::class.java) should beInstanceOf<MemberService>()
    }
})
