package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import java.util.*
import kotlin.random.Random

internal class ScannerTest {

    @BeforeEach
    fun initAll() {
        mockkObject(Random)
    }

    @Test
    fun getScanDataTest() {

        //given
        val byteRandom = Random.nextBytes(100)
        every { Random.nextLong(5000L, 15000L) } returns 8000L
        every { Random.nextBytes(100) } returns byteRandom

        //result
        assertEquals(byteRandom, Scanner.getScanData())
    }





    @Test
    fun throwingExceptionTest() {

        //given
        every { Random.nextLong(5000L, 15000L) } returns 14000L

        //result
        assertThrows(ScanTimeoutException::class.java) { Scanner.getScanData() }
    }


    @AfterEach
    fun tearDownAll() {
        unmockkAll()
    }
}