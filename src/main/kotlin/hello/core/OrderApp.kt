package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberId
import hello.core.member.MemberService
import hello.core.order.OrderService
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class OrderApp

fun main() {
    val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = applicationContext.getBean("memberService", MemberService::class.java)
    val orderService = applicationContext.getBean("orderService", OrderService::class.java)

    val memberId = MemberId(1L)

    val member = Member(
        id = memberId,
        name = "memberA",
        grade = Grade.VIP,
    )

    memberService.join(member)

    val order = orderService.createOrder(memberId = memberId, itemName = "itemA", itemPrice = 20_000)

    println("order = $order")
    println("order.calculatePrice = ${order?.calculatePrice()}")

}
