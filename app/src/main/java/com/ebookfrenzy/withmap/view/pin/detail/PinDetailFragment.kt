package com.ebookfrenzy.withmap.view.pin.detail

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ebookfrenzy.withmap.databinding.FragmentPinDetailBinding
import com.ebookfrenzy.withmap.view.search.SearchFragmentDirections
import com.ebookfrenzy.withmap.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created By Yun Hyeok
 * on 9월 13, 2019
 */

class PinDetailFragment : Fragment() {

    lateinit var binding: FragmentPinDetailBinding
    lateinit var originalWindowAttributes: WindowManager.LayoutParams

    private val tempSearchViewModel: SearchViewModel by sharedViewModel()

    private val returnBack = View.OnClickListener {
        tempSearchViewModel.setTempSharedData("뷰모델 셰어링 테스트")
        Log.d(
            "Malibin Debug",
            "PinDetailFragment 에서의 Live데이터 : ${tempSearchViewModel.tempSharedData.value}"
        )
        Navigation.findNavController(it).popBackStack()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("Malibin Debug", "PinDetail viewmodel instance : ${tempSearchViewModel.toString()}")
        binding = FragmentPinDetailBinding.inflate(inflater)
        binding.returnBack = returnBack

        val safeArgs = PinDetailFragmentArgs.fromBundle(arguments!!).message
        binding.pinDetail = safeArgs
        //pinDetail 에다가 String 집어넣어보기
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarTransparent()
    }

    override fun onDetach() {
        super.onDetach()
        resetStatusBarSettings()
    }

    private fun setStatusBarTransparent() {
        originalWindowAttributes = activity!!.window.attributes
        activity!!.window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //WindowManager.LayoutParams.

            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            if (Build.VERSION.SDK_INT >= 21) {
                statusBarColor = Color.TRANSPARENT
            }
        }
    }

    private fun resetStatusBarSettings() {
        activity!!.window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            attributes = originalWindowAttributes
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = activity!!.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
            return
        }
        winParams.flags = winParams.flags and bits.inv()
        win.attributes = winParams
    }


}