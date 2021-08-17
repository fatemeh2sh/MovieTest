package co.sharif.namava.data.model

data class AuthInputModel (
    val grantType: String = "client_credentials",
    val scope: String = "public"
)
