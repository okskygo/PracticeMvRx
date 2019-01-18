package rex.okskygo.practicemvrx.view.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.coroutineScope
import rex.okskygo.practicemvrx.di.PostService
import rex.okskygo.practicemvrx.dto.PostDto

class MainViewModel(private val postService: PostService) : ViewModel() {

    suspend fun fetchPost(id: Int): PostDto = coroutineScope {
        postService.fetchPost(id).await()
    }
}