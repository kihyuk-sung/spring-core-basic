package hello.core.singleton

class SingletonService private constructor() {
    companion object {
        private val instance = SingletonService()

        fun getInstance(): SingletonService = instance
    }

    fun logic(): Unit = println("싱글톤 객체 로직 호출")
}
