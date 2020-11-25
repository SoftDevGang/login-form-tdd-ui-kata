package org.devgang.loginform.model

class NewsItem(val title: String) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }

        return title == (other as NewsItem).title
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
