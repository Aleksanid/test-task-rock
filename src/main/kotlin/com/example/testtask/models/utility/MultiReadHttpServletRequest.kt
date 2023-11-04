package com.example.testtask.models.utility

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.apache.tomcat.util.http.fileupload.IOUtils
import java.io.*

/**
https://stackoverflow.com/questions/10210645/http-servlet-request-lose-params-from-post-body-after-read-it-once/17129256#17129256
 */
class MultiReadHttpServletRequest(request: HttpServletRequest?) : HttpServletRequestWrapper(request) {
    private var cachedBytes: ByteArrayOutputStream? = null

    @Throws(IOException::class)
    override fun getInputStream(): ServletInputStream {
        if (cachedBytes == null) {
            cacheInputStream()
        }
        return CachedServletInputStream(cachedBytes!!.toByteArray())
    }

    @Throws(IOException::class)
    override fun getReader(): BufferedReader {
        return BufferedReader(InputStreamReader(inputStream))
    }

    @Throws(IOException::class)
    private fun cacheInputStream() {
        cachedBytes = ByteArrayOutputStream()
        IOUtils.copy(super.getInputStream(), cachedBytes)
    }

    private class CachedServletInputStream(contents: ByteArray?) : ServletInputStream() {
        private val buffer: ByteArrayInputStream

        init {
            buffer = ByteArrayInputStream(contents)
        }

        override fun read(): Int {
            return buffer.read()
        }

        override fun isFinished(): Boolean {
            return buffer.available() == 0
        }

        override fun isReady(): Boolean {
            return true
        }

        override fun setReadListener(listener: ReadListener) {
            throw RuntimeException("Not implemented")
        }
    }
}