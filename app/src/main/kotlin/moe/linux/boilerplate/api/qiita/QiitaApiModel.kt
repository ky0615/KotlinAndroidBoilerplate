package moe.linux.boilerplate.api.qiita

data class StockListResponse(
        val id: String,

        val title: String,

        val url: String,

        val tags: List<Tag>,

        val user: User
)

data class Tag(
        val name: String,

        val version: String
)

data class User(
        val id: String,

        val name: String,

        val description: String
)
