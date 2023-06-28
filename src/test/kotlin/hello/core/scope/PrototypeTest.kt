package hello.core.scope

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldNotBeEqual
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

class PrototypeTest: FunSpec({
    test("prototypeBeanTest") {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java)
        val prototypeBean1 = ac.getBean(PrototypeBean::class.java)
        val prototypeBean2 = ac.getBean(PrototypeBean::class.java)

        prototypeBean1 shouldNotBeEqual prototypeBean2

        prototypeBean1.destroy()
        prototypeBean2.destroy()
        ac.close()
    }
}) {

    @Scope("prototype")
    class PrototypeBean{

        @PostConstruct
        fun init() = println("PrototypeBean.init")


        @PreDestroy
        fun destroy() = println("PrototypeBean.destroy")

    }
}
