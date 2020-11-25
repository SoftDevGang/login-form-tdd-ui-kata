package org.devgang.loginform.model

class NewsModel(val items: Array<NewsItem>) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }

        return items.contentEquals((other as NewsModel).items)
    }

    override fun hashCode(): Int {
        return items.contentHashCode()
    }
}
