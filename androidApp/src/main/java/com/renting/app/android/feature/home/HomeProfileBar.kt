package com.renting.app.android.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.RentingImage
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.android.feature.auth.previewData
import com.renting.app.core.auth.model.UserInfo

@Composable
fun HomeProfileBar(userInfo: UserInfo?) {
    if (userInfo != null) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            RentingImage(
                image = userInfo.avatar,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                loading = {
                    CircularProgressIndicator()
                },
                error = {
                    AvatarPlaceholder(userInfo)
                },
                contentDescription = "User avatar",
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = "Hello 👋",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.onBackground,
                )
                Text(
                    text = userInfo.fullName,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground,
                )
            }
        }
    } else {
        // TODO skeletons
        Text(text = "Loading")
    }
}

@Preview
@Composable
fun HomeProfileBarPreview() {
    RentingPreviewContainer {
        HomeProfileBar(
            userInfo = UserInfo.previewData,
        )
    }
}
