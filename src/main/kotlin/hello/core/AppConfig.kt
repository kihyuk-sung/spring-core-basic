package hello.core

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.discount.RateDiscountPolicy
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import hello.core.member.MemoryMemberRepository
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun memberService(): MemberService = MemberServiceImpl(memoryMemberRepository())
        .also { println("call memberService") }

    @Bean
    fun orderService(): OrderService = OrderServiceImpl(
        memberRepository = memoryMemberRepository(),
        discountPolicy = discountPolicy(),
    ).also { println("call orderService") }

    @Bean
    fun memoryMemberRepository() = MemoryMemberRepository()
        .also { println("call memberRepository") }

    @Bean
    fun discountPolicy(): DiscountPolicy = RateDiscountPolicy()

}
