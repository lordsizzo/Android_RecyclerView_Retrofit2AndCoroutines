package lou.sizzo.android_recyclerviewandcoroutines.utils

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET
    fun getVerbList(@Url url: String): Call<ResponseBody>

    @Multipart
    @GET
    fun uploadFileApi(@Url url: String, @Part body: MultipartBody.Part, @Part("value") value: RequestBody, @Part("mobile") mobile: RequestBody): Call<ResponseBody>


    companion object {
        fun createRetrofit(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(ConfigUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}