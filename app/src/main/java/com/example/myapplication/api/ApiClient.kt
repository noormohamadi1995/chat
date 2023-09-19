package com.example.myapplication.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiClient @Inject constructor(
    private val json: Json
) {
    private var httpClient: HttpClient? = null
    private var httpHighTimeOutClient: HttpClient? = null

    fun getKtorClient(withHighTimeout: Boolean = false): HttpClient {
        return (if (withHighTimeout) httpHighTimeOutClient else httpClient) ?: HttpClient(OkHttp) {
            install(Logging) {
                logger = ApiLogger.KtorLogger
                level = LogLevel.ALL

            }

            install(ContentNegotiation) {
                json(json)
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
                url {
                    protocol = URLProtocol.HTTP
                    host = "213.176.29.90:44459/api"
                    }
                }
            expectSuccess = true

        }.apply {
            if (withHighTimeout) {
                httpHighTimeOutClient = this
            } else {
                httpClient = this

            }
        }
    }
}