package rex.okskygo.practicemvrx.view.main

import rex.okskygo.practicemvrx.BaseFragment
import rex.okskygo.practicemvrx.R
import rex.okskygo.practicemvrx.mvrx.MvRxEpoxyController
import rex.okskygo.practicemvrx.mvrx.simpleController
import rex.okskygo.practicemvrx.mvrx.view.basicRow
import rex.okskygo.practicemvrx.mvrx.view.marquee

class MainFragment : BaseFragment() {

    override fun epoxyController(): MvRxEpoxyController = simpleController {

        marquee {
            id("marquee")
            title("Practice MvRx")
            subtitle("Select a demo below")
        }

        basicRow {
            id("hello_world")
            title("Hello World")
            subtitle("Simple MvRx usage")
            clickListener { _ -> navigateTo(R.id.action_main_to_helloWorldFragment) }
        }

        basicRow {
            id("retrofit_coroutines")
            title("Retrofit Coroutines")
            subtitle("Simple Coroutines usage")
            clickListener { _ -> navigateTo(R.id.action_main_to_PostListFragment) }
        }
    }

}