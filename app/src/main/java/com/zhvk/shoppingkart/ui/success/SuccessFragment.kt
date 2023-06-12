package com.zhvk.shoppingkart.ui.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.FragmentSuccessBinding

class SuccessFragment : Fragment() {

    private var _binding: FragmentSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_success, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@SuccessFragment
        }

        // Override back navigation to skip SummaryFragment.
        // TODO: Is there a better solution? Can this be done automatically or do I manually need to
        //  call navigateBackToBrowseFragment() method to skip SummaryFragment when going back?
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigateBackToBrowseFragment()
        }
        callback.isEnabled = true

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateBackToBrowseFragment() {
        val action = SuccessFragmentDirections.actionSuccessFragmentToBrowseFragment()
        findNavController().navigate(action)
    }
}