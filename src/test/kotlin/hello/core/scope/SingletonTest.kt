package hello.core.scope

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

class SingletonTest: FunSpec({
    test("singletonBeanFind") {
        val ac = AnnotationConfigApplicationContext(SingletonBean::class.java)
        val singletonBean1 = ac.getBean(SingletonBean::class.java)
        val singletonBean2 = ac.getBean(SingletonBean::class.java)

        singletonBean1 shouldBeEqual singletonBean2

        ac.close()
    }
}) {
    @Scope("singleton")
    class SingletonBean {

        @PostConstruct
        fun init() = println("SingletonBean.init")


        @PreDestroy
        fun destroy() = println("SingletonBean.destroy")

    }
}
