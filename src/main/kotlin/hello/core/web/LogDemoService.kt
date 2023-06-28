package hello.core.web

import hello.core.common.MyLogger
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service

@Service
class LogDemoService(
    private val myLoggerProvider: ObjectProvider<MyLogger>,
) {
    fun logic(id: String) = myLoggerProvider
        .getObject()
        .log("service id = $id")
}
