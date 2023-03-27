package com.renting.app.android.feature.login

import android.content.res.Configuration
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.renting.app.android.core.brandbook.RentingTheme

@OptIn(ExperimentalTextApi::class)
@Composable
internal fun NoAccountHelp(
    onSignUpClick: () -> Unit,
) {
    val primaryColor = MaterialTheme.colors.primary
    val signUpText = buildAnnotatedString {
        append("Don't have an account? ")
        withAnnotation(tag = LoginScreenTags.SIGN_UP_SPAN_TAG, annotation = "") {
            withStyle(
                SpanStyle(
                    color = primaryColor,
                    fontWeight = FontWeight.SemiBold,
                )
            ) {
                append("Sign up")
            }
        }
    }

    ClickableText(
        text = signUpText,
        modifier = Modifier
            .testTag(LoginScreenTags.SIGN_UP_TEXT),
        style = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.onBackground,
        ),
        onClick = { offset ->
            signUpText.getStringAnnotations(
                LoginScreenTags.SIGN_UP_SPAN_TAG, offset, offset
            ).firstOrNull()?.let { onSignUpClick() }
        },
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun NoAccountHelpPreview() {
    RentingTheme {
        Surface {
            NoAccountHelp {}
        }
    }
}
