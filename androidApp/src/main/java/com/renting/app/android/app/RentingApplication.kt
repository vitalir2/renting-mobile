package com.renting.app.android.app

import android.app.Application
import coil.Coil
import com.renting.app.android.core.image.RentingImageLoaderFactory
import com.renting.app.core.auth.repository.AuthRepository
import com.renting.app.core.settings.SettingsFactory
import com.renting.app.feature.root.di.DefaultRootGraph
import com.renting.app.feature.root.di.RootGraph

class RentingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDI()
        initImageLoader(
            authRepository = AppGraph.rootGraph.authGraph.authRepository,
        )
    }

    private fun initDI() {
        val rootGraph: RootGraph = DefaultRootGraph(
            settingsFactory = SettingsFactory(applicationContext),
        )
        AppGraph.setGraph(rootGraph)
    }

    private fun initImageLoader(
        authRepository: AuthRepository,
    ) {
        Coil.setImageLoader(
            factory = RentingImageLoaderFactory(
                context = applicationContext,
                getAuthToken = authRepository.authToken::value,
            )
        )
    }
}
