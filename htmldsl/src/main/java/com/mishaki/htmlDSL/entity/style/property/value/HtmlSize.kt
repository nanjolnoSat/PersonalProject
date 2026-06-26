package com.mishaki.htmlDSL.entity.style.property.value

@JvmInline
value class HtmlSize(val value: String)

val Int.px: HtmlSize
    get() = HtmlSize("${this}px")

val Float.px: HtmlSize
    get() = HtmlSize("${this}px")

val Double.px: HtmlSize
    get() = HtmlSize("${this}px")

val Int.percent: HtmlSize
    get() = HtmlSize("${this}%")

val Float.percent: HtmlSize
    get() = HtmlSize("${this}%")

val Double.percent: HtmlSize
    get() = HtmlSize("${this}%")

val Int.em: HtmlSize
    get() = HtmlSize("${this}em")

val Float.em: HtmlSize
    get() = HtmlSize("${this}em")

val Double.em: HtmlSize
    get() = HtmlSize("${this}em")

val Int.rem: HtmlSize
    get() = HtmlSize("${this}rem")

val Float.rem: HtmlSize
    get() = HtmlSize("${this}rem")

val Double.rem: HtmlSize
    get() = HtmlSize("${this}rem")

val Int.vh: HtmlSize
    get() = HtmlSize("${this}vh")

val Float.vh: HtmlSize
    get() = HtmlSize("${this}vh")

val Double.vh: HtmlSize
    get() = HtmlSize("${this}vh")

val Int.vw: HtmlSize
    get() = HtmlSize("${this}vw")

val Float.vw: HtmlSize
    get() = HtmlSize("${this}vw")

val Double.vw: HtmlSize
    get() = HtmlSize("${this}vw")

val auto = HtmlSize("auto")

val fitContent = HtmlSize("fit-content")

val minContent = HtmlSize("min-content")

val maxContent = HtmlSize("max-content")

operator fun HtmlSize.plus(other: HtmlSize): HtmlSize {
    return HtmlSize("calc($value + ${other.value})")
}

operator fun HtmlSize.minus(other: HtmlSize): HtmlSize {
    return HtmlSize("calc($value - ${other.value})")
}

operator fun HtmlSize.times(value: Int): HtmlSize {
    return HtmlSize("calc(${this.value} * $value)")
}

operator fun HtmlSize.times(value: Float): HtmlSize {
    return HtmlSize("calc(${this.value} * $value)")
}

operator fun HtmlSize.times(value: Double): HtmlSize {
    return HtmlSize("calc(${this.value} * $value)")
}

operator fun HtmlSize.div(value: Int): HtmlSize {
    return HtmlSize("calc(${this.value} / $value)")
}

operator fun HtmlSize.div(value: Float): HtmlSize {
    return HtmlSize("calc(${this.value} / $value)")
}

operator fun HtmlSize.div(value: Double): HtmlSize {
    return HtmlSize("calc(${this.value} / $value)")
}