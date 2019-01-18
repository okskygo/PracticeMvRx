package rex.okskygo.practicemvrx.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { RetrofitRepositoryImpl() }
    single { get<RetrofitRepositoryImpl>().provideOkHttp() }
    single(name = "retrofit") { get<RetrofitRepositoryImpl>().provideRetrofit(get()) }
}

interface RetrofitRepository {

    fun provideOkHttp(): OkHttpClient

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit

}

class RetrofitRepositoryImpl : RetrofitRepository {

    override fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    override fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

}