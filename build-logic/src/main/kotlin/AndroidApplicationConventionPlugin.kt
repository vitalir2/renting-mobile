import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                setupVersions()
                defaultConfig {
                    applicationId = "com.renting.app"
                    versionCode = 1
                    versionName = "0.0.1"
                    targetSdk = AndroidConstants.TARGET_SDK
                }
            }
        }
    }
}
