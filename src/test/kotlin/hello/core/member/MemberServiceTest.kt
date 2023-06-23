package hello.core.member

import hello.core.AppConfig
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MemberServiceTest: BehaviorSpec({
    val memberService = AppConfig().memberService()

    given("member") {
        val memberId = MemberId(1L)
        val member = Member(id = memberId, name = "memberId", grade = Grade.VIP)

        `when`("join") {
            memberService.join(member)

            then("can find joined member") {
                memberService.findMember(memberId) shouldBe member
            }
        }
    }

})
