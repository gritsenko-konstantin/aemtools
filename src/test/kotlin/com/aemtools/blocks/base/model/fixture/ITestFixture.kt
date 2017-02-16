package com.aemtools.blocks.base.model.fixture

import com.aemtools.blocks.base.model.assertion.IAssertionContext
import org.intellij.lang.annotations.Language

/**
 * @author Dmytro Troynikov
 */
interface ITestFixture {
    /**
     * Add html file to the fixture.
     * @param name name of the file
     * @param text the body of html file
     */
    fun addHtml(name: String,
                @Language("HTML") text: String)

    /**
     * Add java class to the fixture.
     * @param text java class
     */
    fun addClass(name: String,
                 @Language("Java") text: String)

    fun verify(verification: IAssertionContext.() -> Unit)

}