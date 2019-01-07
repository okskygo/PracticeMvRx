package rex.okskygo.practicemvrx.view.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.airbnb.mvrx.BaseMvRxFragment
import rex.okskygo.practicemvrx.R
import rex.okskygo.practicemvrx.mvrx.view.Marquee

class HelloWorldFragment : BaseMvRxFragment() {
    private lateinit var marquee: Marquee

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_hello_world, container, false).apply {
                findViewById<Toolbar>(R.id.toolbar).setupWithNavController(findNavController())
                marquee = findViewById(R.id.marquee)
            }

    override fun invalidate() {
        marquee.setTitle("Hello World")
    }
}