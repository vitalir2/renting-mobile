package com.renting.app.android.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.search.SearchInputComponent

@Composable
fun SearchInput(
    component: SearchInputComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.models.subscribeAsState()
    SearchInput(
        content = state.query,
        modifier = modifier,
        onQueryChanged = component::onQueryChanged,
        onSearchClicked = component::onSearchClicked,
        onFullFiltersClicked = component::onFullFiltersClicked,
    )
}

@Composable
fun SearchInput(
    content: String,
    modifier: Modifier = Modifier,
    onQueryChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onFullFiltersClicked: () -> Unit,
) {
    OutlinedTextField(
        value = content,
        onValueChange = onQueryChanged,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = MaterialTheme.colors.primary,
            backgroundColor = MaterialTheme.colors.surface,
        ),
        placeholder = {
            Text("Search")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .clickable { onSearchClicked() },
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = null,
                modifier = Modifier
                    .clickable { onFullFiltersClicked() },
                tint = MaterialTheme.colors.primary,
            )
        },
        singleLine = true,
    )
}

@Preview
@Composable
private fun SearchInputPreview() {
    RentingPreviewContainer(color = MaterialTheme.colors.background) {
        SearchInput(
            content = "",
            onQueryChanged = {},
            onSearchClicked = {},
            onFullFiltersClicked = {},
        )
    }
}
