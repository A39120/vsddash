package pt.isel.vsdashbapi.vsdashapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import pt.isel.vsdashbapi.vsdashapi.authentication.VsdUser.VsdSessionMapper

@SpringBootApplication
open class VsdashapiApplication

fun main(args: Array<String>) {
	runApplication<VsdashapiApplication>(*args)
}
