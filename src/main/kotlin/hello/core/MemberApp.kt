package hello.core

import hello.core.member.*
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class MemberApp

fun main() {
    val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val memberService = applicationContext.getBean("memberService", MemberService::class.java)

    val memberId = MemberId(1L)

    val member = Member(
        id = memberId,
        name = "memberA",
        grade = Grade.VIP,
    )

    memberService.join(member)

    val foundMember = memberService.findMember(memberId = memberId)

    println("new member = ${member.name}")
    println("findMember = ${foundMember?.name}")

}
