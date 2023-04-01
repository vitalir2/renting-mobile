package com.renting.app.android.core.image

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory

class RentingImageLoaderFactory(
    private val context: Context,
) : ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(context)
            .build()
    }
}

