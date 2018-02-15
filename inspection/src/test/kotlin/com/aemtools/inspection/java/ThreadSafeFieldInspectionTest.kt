package com.aemtools.inspection.java

import com.aemtools.test.HtlTestCase
import com.aemtools.test.base.BaseLightTest
import com.aemtools.test.fixture.JdkProjectDescriptor
import com.intellij.codeInspection.ex.LocalInspectionToolWrapper
import com.intellij.testFramework.LightProjectDescriptor
import java.io.File

/**
 * Test for [ThreadSafeFieldInspection].
 *
 * @author Dmytro Troynikov
 */
class ThreadSafeFieldInspectionTest : BaseLightTest(true) {

  override fun getTestDataPath(): String {
    return File(HtlTestCase.testResourcesPath).absolutePath
  }

  fun testThreadSafeFieldInspection() {
    myFixture.enableInspections(ThreadSafeFieldInspection())

    myFixture.testInspection("com/aemtools/inspection/java/thread-safe-field",
        LocalInspectionToolWrapper(ThreadSafeFieldInspection()))
  }

  override fun getProjectDescriptor(): LightProjectDescriptor {
    return JdkProjectDescriptor()
  }

}
