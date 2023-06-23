package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RateDiscountPolicyTest: FunSpec({
    val discountPolicy = RateDiscountPolicy()

    test("VIP는 10% 할인이 적용되어야 한다") {
        //given
        val member = Member(MemberId(1), "memberVIP", Grade.VIP)

        //when
        val discount = discountPolicy.discount(member, 10_000)

        //then
        discount shouldBe 1_000
    }

    test("VIP가 아니면 할인이 적용되지 않는다") {
        //given
        val member = Member(MemberId(2), "memberBASIC", Grade.BASIC)

        //when
        val discount = discountPolicy.discount(member, 10_000)

        //then
        discount shouldBe 0
    }
})
