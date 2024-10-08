package pt.isel.daw.e5.beanfunctions

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.net.CookieHandler
import java.net.CookieManager
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

private val log = LoggerFactory.getLogger("main")

interface HttpClientService {
    fun get(uri: String): String
}

@Component
class DefaultHttpClientService(
    private val httpClient: HttpClient,
) : HttpClientService {
    override fun get(uri: String): String =
        httpClient.send(
            HttpRequest.newBuilder(URI(uri)).build(),
            BodyHandlers.ofString(),
        ).body()
}

@Component
class BeanConfig {
    @Bean
    fun cookieHandler(): CookieHandler = CookieManager()

    @Bean
    fun httpClient(cookieHandler: CookieHandler): HttpClient =
        HttpClient
            .newBuilder()
            .cookieHandler(cookieHandler)
            .build()

    /*
     * This @Bean annotated method provide the context with a recipe on how to create
     * an `HttpClient` instance.
     */
}

private fun main() {
    log.info("started")
    // Create the context
    val context = AnnotationConfigApplicationContext()
    context.scan("pt.isel.daw.e5")
    context.refresh()
    val httpClientService = context.getBean<HttpClientService>()
    val response = httpClientService.get("https://httpbin.org/get")
    log.info("Response is {}", response)
}