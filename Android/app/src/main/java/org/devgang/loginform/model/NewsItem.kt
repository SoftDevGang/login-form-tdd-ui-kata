package org.devgang.loginform.model

class NewsItem(val title: String) {
    override fun equals(other: Any?): Boolean {
        // TODO clean up generated equals: braces, cast, simplify return
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewsItem

        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
