package hello.core.singleton

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class StatefulServiceTest:FunSpec({
    test("statefulServiceSingleton") {
        val ac = AnnotationConfigApplicationContext(TestConfig::class.java)
        val statefulService1 = ac.getBean(StatefulService::class.java)
        val statefulService2 = ac.getBean(StatefulService::class.java)

        statefulService1.order("userA", 10_000)

        statefulService2.order("userB", 20_000)

        statefulService1.price shouldBe 20_000
    }
}) {

    @Configuration
    class TestConfig {
        @Bean
        fun statefulService() = StatefulService()
    }
}
