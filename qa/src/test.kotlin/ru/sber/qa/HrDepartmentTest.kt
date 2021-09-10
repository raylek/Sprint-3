package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*


internal class HrDepartmentTest {

    val certificateRequest = mockk<CertificateRequest>()
    val hrDepartment = mockkClass(HrDepartment::class)
    val certificate = mockkClass(Certificate::class)

    @Test
    fun receiveRequestTest() {

        every { hrDepartment.clock } returns Clock.fixed(Instant.parse("2021-09-16T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        assertDoesNotThrow { HrDepartment.receiveRequest(certificateRequest) }

    }

    @Test
    fun notAllowedExceptionTest() {

        every { hrDepartment.clock } returns Clock.fixed(Instant.parse("2021-09-15T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        assertThrows(NotAllowReceiveRequestException::class.java) { HrDepartment.receiveRequest(certificateRequest) }

    }

    @Test
    fun dayOfWeekExceptionTest() {

        every { hrDepartment.clock } returns Clock.fixed(Instant.parse("2021-09-18T12:00:00Z"), ZoneOffset.UTC)

        assertThrows(WeekendDayException::class.java) { HrDepartment.receiveRequest(certificateRequest) }

    }

    @Test
    fun processRequestTest() {

        val hrEmployeeNumber = 1L

        every { hrDepartment.clock } returns Clock.fixed(Instant.parse("2021-09-28T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate
        HrDepartment.receiveRequest(certificateRequest)

        assertDoesNotThrow { HrDepartment.processNextRequest(hrEmployeeNumber) }

    }


}