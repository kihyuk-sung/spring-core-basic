package hello.core.order

import hello.core.member.MemberId

interface OrderService {
    fun createOrder(memberId: MemberId, itemName: String, itemPrice: Int): Order?
}
