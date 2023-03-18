package com.renting.app.android.feature.registration

import android.content.res.Configuration
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.renting.app.android.core.brandbook.RentingTheme

private const val SIGN_IN_TAG = "SIGNIN"

@OptIn(ExperimentalTextApi::class)
@Composable
internal fun AlreadyHaveAccount(
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val primaryColor = MaterialTheme.colors.primary
    val signUpText = buildAnnotatedString {
        append("Already have an account? ")
        withAnnotation(tag = SIGN_IN_TAG, annotation = "") {
            withStyle(
                SpanStyle(
                    color = primaryColor,
                    fontWeight = FontWeight.SemiBold,
                )
            ) {
                append("Sign in")
            }
        }
    }

    ClickableText(
        text = signUpText,
        modifier = modifier,
        style = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
        ),
        onClick = { offset ->
            signUpText.getStringAnnotations(SIGN_IN_TAG, offset, offset)
                .firstOrNull()
                ?.let { onSignInClick() }
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
            AlreadyHaveAccount(
                onSignInClick = {},
            )
        }
    }
}
