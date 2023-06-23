package hello.core.order

import hello.core.member.MemberId

data class Order(
    val memberId: MemberId,
    val itemName: String,
    val itemPrice: Int,
    val discountPrice: Int,
)  {

    fun calculatePrice() = itemPrice - discountPrice
}
