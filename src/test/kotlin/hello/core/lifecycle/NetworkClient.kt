package hello.core.lifecycle

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy

class NetworkClient (
    private var url: String? = null,
) {

    init {
        println("생성자 호출, url = $url")
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun connect() = println("connect: $url")

    fun call(message: String) = println("call: $url message = $message")

    fun disconnect() = println("close: $url")

    @PostConstruct
    fun init() = this
        .apply { println("NetworkClient.init") }
        .apply { connect() }
        .call("초기화 연결 메시지")

    @PreDestroy
    fun close() = this
        .apply { println("NetworkClient.close") }
        .disconnect()

}
