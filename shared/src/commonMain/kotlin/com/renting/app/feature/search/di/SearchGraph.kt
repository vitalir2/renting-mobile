package com.renting.app.feature.search.di

import com.renting.app.feature.search.SearchRepository

interface SearchGraph {

    val searchRepository: SearchRepository
}
