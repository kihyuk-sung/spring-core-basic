package hello.core.member

import org.springframework.stereotype.Component

@Component
class MemoryMemberRepository: MemberRepository {
    companion object {
        private val store = mutableMapOf<MemberId, Member>()
    }

    override fun save(member: Member) {
        store[member.id] = member
    }

    override fun findById(id: MemberId): Member? = store[id]

}
