package by.dzinevich.serviceconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
open class ServiceConsumerApplication

    fun main(args: Array<String>) {
        runApplication<ServiceConsumerApplication>(*args)
    }
