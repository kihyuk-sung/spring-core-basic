package hello.core.beandefinition

import hello.core.AppConfig
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext

class BeanDefinitionTest: FunSpec({
    val annotationAc = AnnotationConfigApplicationContext(AppConfig::class.java)
    val ac = GenericXmlApplicationContext("appConfig.xml")


    test("빈 설정 메타정보 확인") {
        ac.beanDefinitionNames
            .map { it to ac.getBeanDefinition(it) }
            .filter { (_, definition) -> definition.role == BeanDefinition.ROLE_APPLICATION }
            .forEach { (name, definition) -> println("beanDefinitionName = $name beanDefinition: $definition") }
    }
})
