package hello.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCoreBasicApplication

fun main(args: Array<String>) {
	runApplication<SpringCoreBasicApplication>(*args)
}
