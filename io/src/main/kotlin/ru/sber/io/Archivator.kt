package ru.sber.io

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.Charset
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {

        ZipOutputStream(FileOutputStream("io\\logfile.zip")).use { zip ->
            zip.putNextEntry(ZipEntry("io\\logfile.log"))
            zip.write(FileInputStream("io\\logfile.log").use { input ->
                input.readAllBytes()
            })
        }


    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {

        FileOutputStream("io\\unzippedLogfile.log").use { out ->
            ZipInputStream(FileInputStream("io\\logfile.zip")).use { zip ->
                zip.nextEntry
                out.write(zip.readAllBytes())
            }
        }
    }

}