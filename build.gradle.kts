plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("org.jetbrains.intellij") version "1.17.2"
}

group = "com.pinealctx"
version = run {
    val ref = System.getenv("GITHUB_REF_NAME")
    if (ref != null && ref.startsWith("v")) {
        ref.removePrefix("v")
    } else {
        try {
            val v = providers.exec {
                commandLine("git", "describe", "--tags", "--abbrev=0")
                isIgnoreExitValue = true
            }.standardOutput.asText.get().trim()
            if (v.isNotEmpty()) v.removePrefix("v") else "UNKNOWN"
        } catch (e: Exception) {
            "UNKNOWN"
        }
    }
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java.srcDirs("src/main/gen")
    }
}

// Configure Gradle IntelliJ Plugin
intellij {
    version.set("2023.2.5")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("com.intellij.java"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    // Set the JVM compatibility versions
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("255.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
