package shafiee.mr.interviewtest.ui.places

import android.os.Bundle
import shafiee.mr.interviewtest.base.BaseActivity
import shafiee.mr.interviewtest.databinding.ActivityPlacesBinding

class PlacesActivity : BaseActivity() {

    private lateinit var binding: ActivityPlacesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}