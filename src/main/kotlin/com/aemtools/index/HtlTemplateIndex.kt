package com.aemtools.index

import com.aemtools.completion.util.extractTemplateDefinition
import com.aemtools.completion.util.findChildrenByType
import com.aemtools.completion.util.getHtmlFile
import com.aemtools.constant.const.htl.DATA_SLY_TEMPLATE
import com.aemtools.index.dataexternalizer.TemplateDefinitionExternalizer
import com.intellij.psi.xml.XmlAttribute
import com.intellij.util.indexing.DataIndexer
import com.intellij.util.indexing.FileBasedIndex
import com.intellij.util.indexing.FileContent
import com.intellij.util.indexing.ID
import com.intellij.util.io.DataExternalizer
import com.intellij.xml.index.XmlIndex
import java.io.Serializable

/**
 * @author Dmytro_Troynikov
 */
class HtlTemplateIndex : XmlIndex<TemplateDefinition>() {

    companion object {
        val HTL_TEMPLATE_ID: ID<String, TemplateDefinition>
                = ID.create<String, TemplateDefinition>("HtlTemplateIndex")
    }

    override fun getIndexer(): DataIndexer<String, TemplateDefinition, FileContent> {
        return DataIndexer<String, TemplateDefinition, FileContent> { inputData ->
            val content = inputData.contentAsText

            if (content.contains(DATA_SLY_TEMPLATE)) {

                val file = inputData.psiFile.getHtmlFile()
                        ?: return@DataIndexer mutableMapOf()
                val attributes = file.findChildrenByType(XmlAttribute::class.java)

                val templates = attributes.filter { it.name.startsWith(DATA_SLY_TEMPLATE) }

                val templateDefinitions = templates.flatMap {
                    with(it.extractTemplateDefinition()) {
                        if (this != null) {
                            listOf(this)
                        } else {
                            listOf()
                        }
                    }
                }

                val path = inputData.file.path
                templateDefinitions.forEach { path }

                return@DataIndexer mutableMapOf(*templateDefinitions.map {
                    "${path}.$${it.name}" to it
                }.toTypedArray())
            }
            mutableMapOf()
        }
    }

    override fun getInputFilter(): FileBasedIndex.InputFilter {
        return FileBasedIndex.InputFilter { it.extension?.endsWith("html") ?: false }
    }

    override fun getValueExternalizer(): DataExternalizer<TemplateDefinition> {
        return TemplateDefinitionExternalizer
    }

    override fun getName(): ID<String, TemplateDefinition> {
        return HTL_TEMPLATE_ID
    }

}

data class TemplateDefinition(var path: String?,
                              val name: String,
                              val parameters: List<String>) : Serializable