package hello.core.common

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Scope(value = "request")
class MyLogger {
    private lateinit var uuid: String
    private lateinit var requestURL: String

    fun setRequestURL(requestURL: String) {
        this.requestURL = requestURL
    }

    fun log(message: String) {
        println("[$uuid][$requestURL][$message]")
    }

    @PostConstruct
    fun init() {
        uuid = UUID.randomUUID().toString()
        println("[$uuid] request scope bean is created: $this")
    }

    @PreDestroy
    fun close() {
        println("[$uuid] request scope bean is closed: $this")
    }
}
