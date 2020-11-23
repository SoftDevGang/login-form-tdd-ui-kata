package org.devgang.loginform.model

class NewsModel(val items: Array<NewsItem>) {
    override fun equals(other: Any?): Boolean {
        // TODO clean up generated equals: braces, cast, simplify return
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewsModel

        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        return items.contentHashCode()
    }
}
