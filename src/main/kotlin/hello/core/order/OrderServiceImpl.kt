package hello.core.order

import hello.core.discount.DiscountPolicy
import hello.core.member.MemberId
import hello.core.member.MemberRepository

class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    private val discountPolicy: DiscountPolicy,
): OrderService {

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
