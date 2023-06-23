package hello.core.member

import org.springframework.stereotype.Component

@Component
class MemberServiceImpl(
    val memberRepository: MemberRepository
): MemberService {

    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: MemberId): Member? = memberId
        .let(memberRepository::findById)

}
