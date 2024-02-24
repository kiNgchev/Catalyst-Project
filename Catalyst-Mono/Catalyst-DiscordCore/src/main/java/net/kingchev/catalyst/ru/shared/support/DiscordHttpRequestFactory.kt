package net.kingchev.catalyst.ru.shared.support

import org.springframework.http.HttpMethod
import org.springframework.http.client.ClientHttpRequest
import org.springframework.http.client.SimpleClientHttpRequestFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URI

import net.kingchev.catalyst.ru.utils.CommonUtils.HTTP_TIMEOUT

class DiscordHttpRequestFactory(private var token: String) : SimpleClientHttpRequestFactory() {
    init {
        setConnectTimeout(HTTP_TIMEOUT)
        setReadTimeout(HTTP_TIMEOUT)
    }

    @Throws(IOException::class)
    override fun createRequest(uri: URI, httpMethod: HttpMethod): ClientHttpRequest {
        val request = super.createRequest(uri, httpMethod)
        val headers = request.headers
        headers.add("User-Agent", "Catalyst Bot (1.0)")
        headers.add("Connection", "keep-alive")
        headers.add("Authorization", "Bot $token")
        return request
    }

    @Throws(IOException::class)
    override fun prepareConnection(connection: HttpURLConnection, httpMethod: String) {
        super.prepareConnection(connection, httpMethod)
        connection.instanceFollowRedirects = false
        connection.useCaches = false
    }
}