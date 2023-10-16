package interfaces

import model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiUserInterface {
    @GET("users")
    suspend fun getUsers(): List<User>
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User?
}