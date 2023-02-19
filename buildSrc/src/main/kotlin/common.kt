import com.android.build.api.dsl.CommonExtension

internal object AndroidConstants {
    const val COMPILE_SDK = 33
    const val TARGET_SDK = 33
    const val MIN_SDK = 23
}

internal fun CommonExtension<*, *, *, *>.setupVersions() {
    compileSdk = AndroidConstants.COMPILE_SDK
    defaultConfig {
        minSdk = AndroidConstants.MIN_SDK
    }
}
