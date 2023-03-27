package com.renting.app.core

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.click
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.text.TextLayoutResult
import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.element.KNode

fun SemanticsNodeInteraction.performClickOnTaggedText(tag: String) {
    val node = fetchSemanticsNode("Cannot perform action")

    val text = node.config.getOrNull(SemanticsProperties.Text)?.firstOrNull() ?: return
    val taggedText = text.getStringAnnotations(tag, 0, text.length).firstOrNull() ?: return
    val middleIndex = (taggedText.start + taggedText.end) / 2

    val results = mutableListOf<TextLayoutResult>()
    val isCompletedLayoutSuccessfully = node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(results)
    val result: TextLayoutResult
    if (isCompletedLayoutSuccessfully == true) {
        result = results[0]
    } else {
        return
    }

    performTouchInput {
        click(result.toPosition(middleIndex))
    }
}

fun KNode.clickOnTaggedText(tag: String) {
    delegate.perform(NodeActions.ComposeBaseActionType.PERFORM_CLICK) {
        performClickOnTaggedText(tag)
    }
}

private fun TextLayoutResult.toPosition(textIndex: Int): Offset {
    val lineIndex = getLineForOffset(textIndex)
    return Offset(
        x = getHorizontalPosition(textIndex, usePrimaryDirection = true),
        y = (getLineTop(lineIndex) + getLineBottom(lineIndex)) / 2,
    )
}
