package com.mishaki.htmlDSL.entity.style.property.value.size

import com.mishaki.htmlDSL.entity.style.property.value.font.HtmlFontSizeKeyword
import com.mishaki.htmlDSL.entity.style.property.value.font.HtmlLineHeightKeyword

/**
 * Implementations:
 * - [HtmlSize]
 * - [HtmlSizeKeyword]
 */
interface HtmlBoxSize

/**
 * Implementations:
 * - [HtmlSize]
 * - [HtmlFontSizeKeyword]
 */
interface HtmlFontSize

/**
 * Implementations:
 * - [HtmlSize]
 * - [HtmlLineHeightKeyword]
 * - [HtmlRatio]
 */
interface HtmlLineHeight
