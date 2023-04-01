package com.renting.app.android.feature.property

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.PropertySnippet

@Composable
fun PropertySnippetsGrid(
    snippets: List<PropertySnippet>,
    modifier: Modifier = Modifier,
    onSnippetClick: (id: Long) -> Unit,
    prependedItems: LazyGridScope.() -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
    ) {
        prependedItems()
        items(snippets, key = PropertySnippet::id) { snippet ->
            PropertySnippetCard(
                snippet = snippet,
                onClick = { onSnippetClick(snippet.id) },
                modifier = Modifier
                    .padding(8.dp),
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
