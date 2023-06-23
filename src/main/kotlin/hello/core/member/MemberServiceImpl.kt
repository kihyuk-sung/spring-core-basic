package hello.core.member

class MemberServiceImpl: MemberService {

    private val memberRepository = MemoryMemberRepository()

    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: MemberId): Member? = memberId
        .let(memberRepository::findById)

}
