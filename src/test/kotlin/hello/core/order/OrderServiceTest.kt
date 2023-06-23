package hello.core.order

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberId
import hello.core.member.MemberServiceImpl
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OrderServiceTest: FunSpec({
    val memberService = MemberServiceImpl()
    val orderService = OrderServiceImpl()

    test("create Order") {
        val memberId = MemberId(1L)
        val member = Member(memberId, "memberA", Grade.VIP)

        memberService.join(member)

        val order = orderService.createOrder(memberId, "itemA", 10_000)

        order?.discountPrice shouldBe 1000

    }
})
