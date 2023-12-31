package hello.core.web

import hello.core.common.MyLogger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class LogDemoController(
    private val logDemoService: LogDemoService,
    private val myLogger: MyLogger,
) {

    @RequestMapping("/log-demo")
    @ResponseBody
    fun logDemo(request: HttpServletRequest): String = request
        .requestURL
        .toString()
        .also { myLogger.setRequestURL(it) }
        .let { logDemoService.logic("testId") }
        .let { "OK" }

}
