package com.renting.app.android.feature.login

import android.content.res.Configuration
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.renting.app.android.core.brandbook.RentingTheme

private const val SIGN_UP_TAG = "SIGNUP"

@OptIn(ExperimentalTextApi::class)
@Composable
internal fun NoAccountHelp(
    onSignUpClick: () -> Unit,
) {
    val primaryColor = MaterialTheme.colors.primary
    val signUpText = buildAnnotatedString {
        append("Don't have an account? ")
        withAnnotation(tag = SIGN_UP_TAG, annotation = "") {
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
        style = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.onBackground,
        ),
        onClick = { offset ->
            signUpText.getStringAnnotations(SIGN_UP_TAG, offset, offset)
                .firstOrNull()
                ?.let { onSignUpClick() }
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
