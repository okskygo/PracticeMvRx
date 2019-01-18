package rex.okskygo.practicemvrx

import android.app.Application
import org.koin.android.ext.android.startKoin
import rex.okskygo.practicemvrx.di.networkServiceModule
import rex.okskygo.practicemvrx.di.retrofitModule
import rex.okskygo.practicemvrx.di.viewModelModule

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(
                retrofitModule,
                networkServiceModule,
                viewModelModule
            )
        )
    }
}