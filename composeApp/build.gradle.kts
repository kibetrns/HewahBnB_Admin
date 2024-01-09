import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
    alias(libs.plugins.jetbrainsCompose)

    //Kotlinx.serialization
    kotlin("plugin.serialization")
}

kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            //PreCompose
            api(compose.foundation)
            api(compose.animation)
            api("moe.tlaster:precompose:1.5.10")
            api("moe.tlaster:precompose-viewmodel:1.5.10")
            api("moe.tlaster:precompose-koin:1.5.10")

            //Kotlix.serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

            //Ktor-Client
            val ktor_version = "2.3.6"
            implementation ("io.ktor:ktor-client-core:$ktor_version")
            implementation ("io.ktor:ktor-client-cio:$ktor_version")
            implementation ("io.ktor:ktor-client-content-negotiation:$ktor_version")
            implementation ("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
            implementation ("io.ktor:ktor-client-serialization:$ktor_version")
            implementation ("io.ktor:ktor-client-auth:$ktor_version")
            implementation ("io.ktor:ktor-client-logging:$ktor_version")

            //Kotlin datetime
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

            //Coil
            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha01")

            //Koin
            val koin_version = "3.5.0"
            implementation ("io.insert-koin:koin-core:$koin_version")
            implementation("io.insert-koin:koin-compose:1.1.0")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}


compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "me.adipiscing_elit"
            packageVersion = "1.0.0"
        }
    }
}
