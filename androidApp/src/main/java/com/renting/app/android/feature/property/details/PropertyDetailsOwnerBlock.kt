package com.renting.app.android.feature.property.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renting.app.android.core.uikit.Gap
import com.renting.app.android.core.uikit.RentingPreviewContainer
import com.renting.app.android.core.uikit.UserAvatar
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetails
import com.renting.app.feature.property.details.ui.model.ComponentPropertyDetailsPreviews

@Composable
fun PropertyDetailsOwnerBlock(
    ownerInfo: ComponentPropertyDetails.OwnerInfo,
    modifier: Modifier = Modifier,
    onPhoneClicked: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        UserAvatar(
            avatar = ownerInfo.avatar,
            fullName = ownerInfo.fullName,
            modifier = Modifier
                .size(64.dp),
        )
        Gap(8.dp)
        Column {
            Text(
                text = ownerInfo.fullName,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold,
            )
            Gap(2.dp)
            Text(
                text = "Owner",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Light,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Image(
                imageVector = Icons.Outlined.Phone,
                contentDescription = "Call to owner phone number=${ownerInfo.phoneNumber}",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onPhoneClicked() },
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            )
        }
    }
}

@Preview
@Composable
private fun PropertyDetailsOwnerBlockPreview() {
    RentingPreviewContainer {
        PropertyDetailsOwnerBlock(
            ownerInfo = ComponentPropertyDetailsPreviews.apartment.ownerInfo,
            onPhoneClicked = {},
        )
    }
}
