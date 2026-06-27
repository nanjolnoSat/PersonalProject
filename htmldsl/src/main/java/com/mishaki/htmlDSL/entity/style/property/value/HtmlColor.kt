package com.mishaki.htmlDSL.entity.style.property.value

@JvmInline
value class HtmlColor (val value: String) {
    companion object {
        val transparent = HtmlColor("transparent")

        val black = HtmlColor("black")
        val white = HtmlColor("white")
        val gray = HtmlColor("gray")
        val silver = HtmlColor("silver")

        val red = HtmlColor("red")
        val maroon = HtmlColor("maroon")

        val green = HtmlColor("green")
        val lime = HtmlColor("lime")

        val blue = HtmlColor("blue")
        val navy = HtmlColor("navy")

        val yellow = HtmlColor("yellow")
        val olive = HtmlColor("olive")

        val orange = HtmlColor("orange")

        val purple = HtmlColor("purple")
        val fuchsia = HtmlColor("fuchsia")

        val cyan = HtmlColor("cyan")
        val aqua = HtmlColor("aqua")

        val teal = HtmlColor("teal")

        val pink = HtmlColor("pink")

        val brown = HtmlColor("brown")

        val gold = HtmlColor("gold")

        val beige = HtmlColor("beige")

        val ivory = HtmlColor("ivory")

        val coral = HtmlColor("coral")

        val chocolate = HtmlColor("chocolate")

        val crimson = HtmlColor("crimson")

        val indigo = HtmlColor("indigo")

        val violet = HtmlColor("violet")

        val turquoise = HtmlColor("turquoise")

        val khaki = HtmlColor("khaki")

        val tomato = HtmlColor("tomato")

        val salmon = HtmlColor("salmon")

        val skyBlue = HtmlColor("skyblue")

        val lightGray = HtmlColor("lightgray")

        val darkGray = HtmlColor("darkgray")

        fun rgb(red: Int, green: Int, blue: Int): HtmlColor {
            require(red in 0..255) { "red must be between 0 and 255" }
            require(green in 0..255) { "green must be between 0 and 255" }
            require(blue in 0..255) { "blue must be between 0 and 255" }

            return HtmlColor("rgb($red,$green,$blue)")
        }

        fun rgba(red: Int, green: Int, blue: Int, alpha: Float): HtmlColor {
            require(red in 0..255)
            require(green in 0..255)
            require(blue in 0..255)
            require(alpha in 0f..1f)

            return HtmlColor("rgba($red,$green,$blue,$alpha)")
        }
    }
}

val String.color: HtmlColor
    get() = HtmlColor(this)