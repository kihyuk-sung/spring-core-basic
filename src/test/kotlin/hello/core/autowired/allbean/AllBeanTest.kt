package hello.core.autowired.allbean

import hello.core.AutoAppConfig
import hello.core.discount.DiscountPolicy
import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeanTest: FunSpec({
    test("find all bean") {
        val ac = AnnotationConfigApplicationContext(AutoAppConfig::class.java, DiscountService::class.java)
        val discountService = ac.getBean(DiscountService::class.java)
        val member = Member(MemberId(1L), "usreA", Grade.VIP)


        discountService should beInstanceOf<DiscountService>()
        discountService.discount(member, 20000, "fixDiscountPolicy") shouldBe 1000
        discountService.discount(member, 20000, "rateDiscountPolicy") shouldBe 2000
    }
}) {
    class DiscountService(
        private val policyMap: Map<String, DiscountPolicy>,
        private val policies: List<DiscountPolicy>,
    ) {
        fun discount(member: Member, price: Int, policy: String): Int =
            policyMap[policy]
                ?.discount(member,price)
                ?: 0
    }
}
