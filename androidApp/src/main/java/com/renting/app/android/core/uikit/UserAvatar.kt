package com.renting.app.android.core.uikit

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.renting.app.core.auth.model.UserInfo
import com.renting.app.core.model.Image

@Composable
fun UserAvatar(
    avatar: Image?,
    fullName: String,
    modifier: Modifier = Modifier,
) {
    RentingImage(
        image = avatar,
        modifier = modifier
            .clip(CircleShape),
        loading = {
            CircularProgressIndicator()
        },
        error = {
            AvatarPlaceholder(fullName)
        },
        contentDescription = "User avatar",
    )
}
