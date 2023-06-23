package hello.core

import hello.core.discount.FixDiscountPolicy
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import hello.core.member.MemoryMemberRepository
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl

class AppConfig {

    fun memberService(): MemberService = MemberServiceImpl(MemoryMemberRepository())

    fun orderService(): OrderService = OrderServiceImpl(
        memberRepository = MemoryMemberRepository(),
        discountPolicy = FixDiscountPolicy(),
    )

}
