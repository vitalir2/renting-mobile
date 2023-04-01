package com.renting.app.android.core.image

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory

class RentingImageLoaderFactory(
    private val context: Context,
    private val getAuthToken: () -> String?,
) : ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(context)
            .components {
                add(AuthImageInterceptor(getAuthToken))
            }
            .build()
    }
}

