package rex.okskygo.practicemvrx.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import rex.okskygo.practicemvrx.view.main.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}