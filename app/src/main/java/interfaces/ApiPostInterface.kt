package interfaces

import com.google.gson.JsonObject
import model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPostInterface {
    @GET("posts")
    suspend fun getPosts(): List<Post>
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post?
    @GET("posts")
    suspend fun getPostsByUserId(@Query("userId") userId: Int): List<Post>
    @POST("posts")
    suspend fun createPost(@Body body: JsonObject): Response<JsonObject>

}