package com.renting.app.feature.search.results

import com.renting.app.feature.search.DefaultSearchRepository
import com.renting.app.feature.search.SearchRepository
import io.ktor.client.*

internal class DefaultSearchGraph(
    httpClient: HttpClient,
): SearchGraph {

    override val searchRepository: SearchRepository = DefaultSearchRepository(
        httpClient = httpClient,
    )
}
