package datasource

import interfaces.ApiUserInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        @Volatile
        private var INSTANCE: ApiUserInterface? = null
        fun getService(): ApiUserInterface {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildService()
                }
            }
            return INSTANCE!!
        }
        //инициализация Retrofit
        private fun buildService(): ApiUserInterface {
            val retrofit = Retrofit.Builder() .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiUserInterface::class.java)
        }
    }




}