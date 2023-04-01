package com.renting.app.android.feature.property

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.PropertySnippet

@Composable
fun PropertySnippetsGrid(
    snippets: List<PropertySnippet>,
    modifier: Modifier = Modifier,
    onSnippetClick: (id: Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
    ) {
        items(snippets, key = PropertySnippet::id) { snippet ->
            PropertySnippetCard(
                snippet = snippet,
                onClick = { onSnippetClick(snippet.id) },
            )
        }
    }
}

@Preview
@Composable
private fun PropertySnippetGridPreview() {
    RentingPreviewContainer {
        PropertySnippetsGrid(
            snippets = List(5) { PropertySnippet.preview },
            onSnippetClick = {},
        )
    }
}
