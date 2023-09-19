package com.example.myapplication.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.ktor.client.plugins.logging.Logger
import timber.log.Timber

internal object ApiLogger {
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private const val tag = "ApiLogger"

    internal object KtorLogger: Logger {

        override fun log(message: String) {
            try {
                val logger = Timber.tag(tag)
                val hasJson = message
                    .lineSequence()
                    .find { it.contains("BODY Content-Type") }
                    ?.contains("application/json") ?: false
                if (hasJson) {
                    val jsonMessage = message.substring(
                        message.indexOf("BODY START") + 10,
                        message.indexOf("BODY END")
                    )
                    try {
                        if (message.contains("[request body omitted]").not()) {
                            message.replace(
                                jsonMessage,
                                "\n${gson.toJson(JsonParser.parseString(jsonMessage))}\n"
                            ).let { logger.d(it) }
                        } else logger.d(message)
                    } catch (e: Exception) {
                        logger.e(e)
                        logger.d(message)
                    }
                } else {
                    logger.d(message)
                }
            } catch (e: RuntimeException) {
                e.printStackTrace()
            } catch (e: java.lang.RuntimeException) {
                e.printStackTrace()
            }
        }
    }
}