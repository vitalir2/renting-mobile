package com.renting.app.android.feature.property.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.renting.app.android.core.system.openPhoneApp
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.feature.property.details.ui.PropertyDetailsComponent
import com.renting.app.feature.property.details.ui.PropertyDetailsComponent.Model
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetailsPreviews

@Composable
fun PropertyDetailsScreen(component: PropertyDetailsComponent) {
    val models = component.models.subscribeAsState()

    PropertyDetailsScreen(
        model = models.value,
        onBackButtonClick = component::onBackButtonClick,
    )
}

@Composable
private fun PropertyDetailsScreen(
    model: Model,
    onBackButtonClick: () -> Unit,
) {
    when (model) {
        is Model.Loading -> LoadingPropertyDetailsScreen()
        is Model.PropertyDetailsLoaded -> LoadedPropertyDetailsScreen(
            details = model.details,
            onBackButtonClick = onBackButtonClick,
        )
    }
}

@Composable
private fun LoadedPropertyDetailsScreen(
    details: ComponentPropertyDetails,
    onBackButtonClick: () -> Unit,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val appBarAlpha = remember { mutableStateOf(0f) }
    val screenWidthPx = getScreenWidthPx()
    val animationThreshold = -screenWidthPx * APP_BAR_ANIMATION_THRESHOLD_PERCENT
    val toolbarOffsetHeightPx = remember { mutableStateOf(-screenWidthPx) }
    val nestedScrollConnection = remember {
        createCollapsingToolbarConnection(
            toolbarOffsetPx = toolbarOffsetHeightPx,
            maxOffsetPx = screenWidthPx,
            appBarAlpha = appBarAlpha,
            animationThreshold = animationThreshold
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .align(Alignment.TopCenter),
        ) {
            PropertyDetailsHeader(
                images = details.images,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f, matchHeightConstraintsFirst = true),
            )
            Gap(8.dp)
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                PropertyDetailsMainInfoBlock(
                    mainInfo = details.mainInfo,
                )
                PropertyDetailsLocationBlock()
                PropertyDetailsOwnerBlock(
                    ownerInfo = details.ownerInfo,
                    onPhoneClicked = {
                        context.openPhoneApp(details.ownerInfo.phoneNumber)
                    },
                )
            }
        }
        PropertyDetailsBookingFooter(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        )
        PropertyDetailsScreenAppBar(
            onBackButtonClick = onBackButtonClick,
            alpha = appBarAlpha.value,
        )
    }
}

@Composable
private fun PropertyDetailsScreenAppBar(
    onBackButtonClick: () -> Unit,
    alpha: Float,
) {
    TopAppBar(
        modifier = Modifier
            .height(toolbarHeight),
        title = {},
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back to previous screen",
                modifier = Modifier
                    .clickable { onBackButtonClick() }
            )
        },
        backgroundColor = MaterialTheme.colors.background.copy(
            alpha = alpha,
        ),
        elevation = if (alpha == 1f) defaultAppBarElevation else 0.dp,
    )
}

private fun createCollapsingToolbarConnection(
    toolbarOffsetPx: MutableState<Float>,
    maxOffsetPx: Float,
    appBarAlpha: MutableState<Float>,
    animationThreshold: Float
) = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.y
        val newOffset = toolbarOffsetPx.value - delta
        toolbarOffsetPx.value = newOffset.coerceIn(-maxOffsetPx, 0f)
        appBarAlpha.value = if (toolbarOffsetPx.value > animationThreshold) {
            (animationThreshold - toolbarOffsetPx.value) / animationThreshold
        } else {
            0f
        }
        return Offset.Zero
    }
}

@Composable
@ReadOnlyComposable
private fun getScreenWidthPx(): Float {
    return with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.roundToPx().toFloat()
    }
}

private const val APP_BAR_ANIMATION_THRESHOLD_PERCENT = 0.8f
private val toolbarHeight = 48.dp
private val defaultAppBarElevation = 4.dp

@Composable
private fun LoadingPropertyDetailsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun PropertyDetailsScreenPreview() {
    RentingPreviewContainer {
        PropertyDetailsScreen(
            model = Model.PropertyDetailsLoaded(
                details = ComponentPropertyDetailsPreviews.familyHouse,
            ),
            onBackButtonClick = {},
        )
    }
}
