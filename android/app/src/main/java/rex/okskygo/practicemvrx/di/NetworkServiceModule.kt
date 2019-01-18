package rex.okskygo.practicemvrx.di

import kotlinx.coroutines.Deferred
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import rex.okskygo.practicemvrx.dto.PostDto

val networkServiceModule = module {
    single { NetworkServiceRepositoryImpl(get(name = "retrofit")) }
    single { get<NetworkServiceRepositoryImpl>().providePostService() }
}

interface NetworkServiceRepository {
    fun providePostService(): PostService
}

class NetworkServiceRepositoryImpl(private val retrofit: Retrofit) : NetworkServiceRepository {

    override fun providePostService(): PostService {
        return retrofit.create(PostService::class.java)
    }
}

interface PostService {
    @GET("/posts/{id}")
    fun fetchPost(@Path("id") id: Int): Deferred<PostDto>

    @GET("/posts")
    fun fetchPosts(): Deferred<List<PostDto>>
}