package hello.core.member

class MemoryMemberRepository: MemberRepository {
    private val store = mutableMapOf<MemberId, Member>()

    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(id: MemberId): Member? = store[id]

}
