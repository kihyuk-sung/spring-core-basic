package hello.core

import hello.core.member.*

class MemberApp

fun main() {
    val memberService = MemberServiceImpl()

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
