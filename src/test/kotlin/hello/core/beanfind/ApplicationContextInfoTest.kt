package hello.core.beanfind

import hello.core.AppConfig
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextInfoTest: FunSpec({
    val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    test("모든 빈 출력하기") {
        ac.beanDefinitionNames
            .forEach { printBean(it, ac.getBean(it)) }
    }

    test("애플리케이션 빈 출력하기") {
        ac.beanDefinitionNames
            .map { it to ac.getBeanDefinition(it) }
            .filter { (_, definition) -> definition.role == BeanDefinition.ROLE_APPLICATION }
            .forEach { (name, _) -> printBean(name, ac.getBean(name)) }
    }
})

private fun printBean(beanName: String?, bean: Any) {
    println("name = $beanName object = $bean")
}
