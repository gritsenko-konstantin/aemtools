package com.aemtools.completion.html.inserthandler

import com.aemtools.common.constant.const.DOLLAR

/**
 * @author Dmytro Troynikov
 */
class HtlTemplateInsertHandler : HtlTextInsertHandler(".=\"$DOLLAR{@ param}\"", offset = 1)
