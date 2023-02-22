package com.renting.app.core

import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

abstract class ComposeTestCase : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport(),
)
