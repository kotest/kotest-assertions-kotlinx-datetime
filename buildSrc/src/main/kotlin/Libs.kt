object Libs {

   const val kotlinVersion = "1.5.21"
   const val org = "io.kotest.extensions"
   const val dokkaVersion = "0.10.1"

   object Kotest {
      private const val version = "4.6.1"
      const val assertionsShared = "io.kotest:kotest-assertions-shared:$version"
      const val api = "io.kotest:kotest-framework-api:$version"
      const val junit5 = "io.kotest:kotest-runner-junit5-jvm:$version"
   }

   object KotlinTime {
      private const val version = "0.2.1"
      const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:$version"
   }
}
