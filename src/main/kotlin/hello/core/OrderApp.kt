package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberId
import hello.core.member.MemberServiceImpl
import hello.core.order.OrderServiceImpl

class OrderApp

fun main() {
    val memberService = MemberServiceImpl()
    val orderService = OrderServiceImpl()

    val memberId = MemberId(1L)

    val member = Member(
        id = memberId,
        name = "memberA",
        grade = Grade.VIP,
    )

    memberService.join(member)

    val order = orderService.createOrder(memberId = memberId, itemName = "itemA", itemPrice = 10_000)

    println("order = $order")
    println("order.calculatePrice = ${order?.calculatePrice()}")

}
