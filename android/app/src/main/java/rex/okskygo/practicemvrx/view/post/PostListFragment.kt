package rex.okskygo.practicemvrx.view.post

import com.airbnb.mvrx.fragmentViewModel
import rex.okskygo.practicemvrx.BaseFragment
import rex.okskygo.practicemvrx.mvrx.simpleController
import rex.okskygo.practicemvrx.mvrx.view.basicRow
import rex.okskygo.practicemvrx.mvrx.view.loadingRow
import rex.okskygo.practicemvrx.mvrx.view.marquee

class PostListFragment : BaseFragment() {

    private val viewModel: PostListViewModel by fragmentViewModel()

    override fun epoxyController() = simpleController(viewModel) { state ->
        marquee {
            id("marquee")
            title("Retrofit Coroutines")
        }

        val post = state.post()
        if (post == null) {
            loadingRow {
                id("loading")
            }
            return@simpleController
        }

        post.forEachIndexed { index, postDto ->
            basicRow {
                id("post$index")
                title(postDto.title)
                clickListener { _ -> viewModel.fetchPosts() }
            }
        }
    }

}