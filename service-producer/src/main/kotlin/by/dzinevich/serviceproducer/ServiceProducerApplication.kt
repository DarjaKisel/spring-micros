package by.dzinevich.serviceproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ServiceProducerApplication

fun main(args: Array<String>) {
    runApplication<ServiceProducerApplication>(*args)
}
