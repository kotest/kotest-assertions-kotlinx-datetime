plugins {
   signing
   `java-library`
   `maven-publish`
   kotlin("multiplatform") version "1.8.21"
}

repositories {
   mavenLocal()
   mavenCentral()
   maven {
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
   }
}

group = "io.kotest.extensions"
version = Ci.version

kotlin {

   targets {

      jvm {
         compilations.all {
            kotlinOptions {
               jvmTarget = "1.8"
            }
         }
      }

      js(IR) {
         browser()
         nodejs()
      }

      linuxX64()

      mingwX64()

      macosX64()
      tvos()
      iosX64()
      iosArm64()
      iosArm32()
   }

   sourceSets {

      val commonMain by getting {
         dependencies {
            implementation(libs.kotest.assertions.shared)
            implementation(libs.kotlinx.datetime)
         }
      }

      val jvmMain by getting {
         dependsOn(commonMain)
      }

      val jvmTest by getting {
         dependsOn(jvmMain)
         dependencies {
            implementation(libs.kotest.runner.junit5)
         }
      }

      val desktopMain by creating {
         dependsOn(commonMain)
      }

      val macosX64Main by getting {
         dependsOn(desktopMain)
      }

      val mingwX64Main by getting {
         dependsOn(desktopMain)
      }

      val linuxX64Main by getting {
         dependsOn(desktopMain)
      }

      val iosX64Main by getting {
         dependsOn(desktopMain)
      }

      val iosArm64Main by getting {
         dependsOn(desktopMain)
      }

      val iosArm32Main by getting {
         dependsOn(desktopMain)
      }

//      val watchosMain by getting {
//         dependsOn(desktopMain)
//      }

      val tvosMain by getting {
         dependsOn(desktopMain)
      }
   }
}

tasks.named<Test>("jvmTest") {
   useJUnitPlatform()
   filter {
      isFailOnNoMatchingTests = false
   }
   testLogging {
      showExceptions = true
      showStandardStreams = true
      events = setOf(
         org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
         org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
      )
      exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
   }
}

apply("./publish-mpp.gradle.kts")
