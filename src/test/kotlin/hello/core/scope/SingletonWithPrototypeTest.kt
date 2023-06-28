package hello.core.scope

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import jakarta.inject.Provider
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

class SingletonWithPrototypeTest: FunSpec({
    test("prototypeFind") {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java)

        val prototypeBean1 = ac.getBean(PrototypeBean::class.java)
        prototypeBean1.addCount()

        prototypeBean1.getCount() shouldBe 1

        val prototypeBean2 = ac.getBean(PrototypeBean::class.java)
        prototypeBean2.addCount()

        prototypeBean2.getCount() shouldBe 1
    }

    test("singletonClientUsePrototype") {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java, ClientBean::class.java)

        val clientBean1 = ac.getBean(ClientBean::class.java)
        clientBean1.logic() shouldBe 1

        val clientBean2 = ac.getBean(ClientBean::class.java)
        clientBean2.logic() shouldBe 1
    }

}) {

    @Scope("singleton")
    class ClientBean (
        private val prototypeBeanProvider: Provider<PrototypeBean>,
    ) {
        fun logic(): Int {
            val prototypeBean = prototypeBeanProvider.get()
            prototypeBean.addCount()
            return prototypeBean.getCount()
        }

    }

    @Scope("prototype")
    class PrototypeBean {
        private var count = 0
        
        fun addCount() {
            count += 1
        }

        fun getCount() = this.count

        @PostConstruct
        fun init() = println("PrototypeBean.init $this")


        @PreDestroy
        fun destroy() = println("PrototypeBean.destroy")
    }

}
