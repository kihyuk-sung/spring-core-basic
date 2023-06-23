package hello.core.order

import hello.core.discount.FixDiscountPolicy
import hello.core.member.MemberId
import hello.core.member.MemoryMemberRepository

class OrderServiceImpl: OrderService {

    private val memberRepository = MemoryMemberRepository()
    private val discountPolicy = FixDiscountPolicy()

    override fun createOrder(memberId: MemberId, itemName: String, itemPrice: Int): Order? = memberRepository
        .findById(memberId)
        ?.let {
            Order(
                memberId = memberId,
                itemName = itemName,
                itemPrice = itemPrice,
                discountPrice = discountPolicy.discount(member = it, price = itemPrice)
            )
        }
}
