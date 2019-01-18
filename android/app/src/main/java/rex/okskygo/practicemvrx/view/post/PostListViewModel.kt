package rex.okskygo.practicemvrx.view.post

import androidx.fragment.app.FragmentActivity
import com.airbnb.mvrx.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import rex.okskygo.practicemvrx.di.PostService
import rex.okskygo.practicemvrx.dto.PostDto
import rex.okskygo.practicemvrx.mvrx.MvRxViewModel
import kotlin.coroutines.CoroutineContext


data class PostListState(val post: Async<List<PostDto>> = Uninitialized) : MvRxState

class PostListViewModel(
    initialState: PostListState,
    private val postService: PostService
) : MvRxViewModel<PostListState>(initialState), CoroutineScope {

    private var job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        setState { copy(post = Loading()) }
        try {
            launch {
                val list = postService.fetchPosts().await()
                setState { copy(post = Success(list)) }
            }
        } catch (e: Exception) {
            setState { copy(post = Fail(e)) }
        }
    }

    companion object : MvRxViewModelFactory<PostListState> {
        @JvmStatic
        override fun create(
            activity: FragmentActivity,
            state: PostListState
        ): BaseMvRxViewModel<PostListState> {
            val service: PostService by activity.inject()
            return PostListViewModel(state, service)
        }
    }
}