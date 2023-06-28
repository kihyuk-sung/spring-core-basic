package hello.core.lifecycle

import io.kotest.core.spec.style.FunSpec
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanLifeCycleTest: FunSpec({

    test("lifeCycleTest") {
        val ac: ConfigurableApplicationContext = AnnotationConfigApplicationContext(LifeCycleConfig::class.java)
        ac.getBean(NetworkClient::class.java)
        ac.close()
    }

}) {
    @Configuration
    class LifeCycleConfig {

        @Bean
        fun networkClient() = NetworkClient()
            .apply { this.setUrl("http://hello-spring.dev") }

    }
}
