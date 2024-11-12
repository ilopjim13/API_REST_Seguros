package com.example.unsecuredseguros

import com.example.unsecuredseguros.utils.DniValidator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertTrue

@SpringBootTest
class UnsecuredSegurosApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun testDniFormat() {

		val dniValidator: DniValidator = DniValidator

		assertTrue(dniValidator.checkDniFormat("717171717B"))

		assertTrue(dniValidator.checkValidDni("15309325L"))

	}

}
