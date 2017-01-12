package com.aemtools.completion.htl.completionprovider

import com.aemtools.completion.blocks.BaseVariantsCheckContributorTest

/**
 * @author Dmytro Troynikov.
 */
class HtlELVariantsCheckContributorTest : BaseVariantsCheckContributorTest("com/aemtools/completion/htl/fixtures/noafter") {

    fun testContextValues() = assertVariants(CONTEXT_VALUES)

    fun testContextObjects() = defaultContextObjectTest()

    fun testContextWithinSecondEl() = defaultContextObjectTest()

    fun testHtlInAttribute() = defaultContextObjectTest()

    fun testHtlWithinTag() = defaultContextObjectTest()

    fun testHtlInScriptTag() = defaultContextObjectTest()

    fun testHtlInStyleAttribute() = defaultContextObjectTest()

    fun testHtlInStyleTag() = defaultContextObjectTest()

    fun testHtlAfterHtml() = defaultContextObjectTest()
    fun testHtlBeforeHtml() = defaultContextObjectTest()

    fun testUnknownVariable() = assertVariantsAreEmpty()

    private fun defaultContextObjectTest() = assertVariants(DEFAULT_CONTEXT_OBJECTS)

}