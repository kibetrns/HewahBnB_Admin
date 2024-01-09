package di

import data.repository.HBAdminRepository
import data.service.HBAdminService
import data.service.HBAdminServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import viewmodel.HBAdminViewModel

val appModule = module {

    single<HttpClient>(named("default")) {
        HttpClient(CIO) {
            engine {
                requestTimeout = 5_000
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.DEFAULT
            }

            install(ContentNegotiation) {

                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    factory<HBAdminService> {
        HBAdminServiceImpl(defaultCSAService = get(named("default")))
    }

    factory<HBAdminRepository> {
        HBAdminRepository(hbAdminService = get())
    }

    single {
        HBAdminViewModel(repository = get())

    }

}