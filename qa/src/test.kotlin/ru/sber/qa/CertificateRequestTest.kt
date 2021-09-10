package ru.sber.qa

import io.mockk.*
import io.mockk.mockkObject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.util.*
import kotlin.random.Random

internal class CertificateRequestTest {


    val certificateType = mockk<CertificateType>()
    val employeeNumber = 14524L
    val certificateRequest = CertificateRequest(employeeNumber, certificateType)
    val hrEmployeeNumber = 547L
    val byteRandom = Random.nextBytes(100)

    @BeforeEach
    fun init() {

        mockkConstructor(Certificate::class)
        mockkObject(Scanner)
        every { Scanner.getScanData() } returns byteRandom
    }

    @Test
    fun getScanDataTest() {

        //given
        val certificate = certificateRequest.process(hrEmployeeNumber)

        // then
        assertEquals(byteRandom, certificate.data)
        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(certificateRequest, certificate.certificateRequest)
    }

    @Test
    fun getCertificateType() { assertEquals(certificateType, certificateRequest.certificateType) }

    @Test
    fun getEmployeeNumberTest() { assertEquals(employeeNumber, certificateRequest.employeeNumber) }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}