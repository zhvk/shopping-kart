package com.zhvk.shoppingkart.ui.tutorial

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.databinding.FragmentTutorialBinding
import com.zhvk.shoppingkart.model.data.DataSource

class TutorialFragment : Fragment() {

    private var _binding: FragmentTutorialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tutorial, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner

            val viewPagerAdapter = TutorialViewPagerAdapter(DataSource.tutorialPageInfoList)
            tutorialViewPager.adapter = viewPagerAdapter
            dotsIndicator.attachTo(tutorialViewPager)

            buttonContinue.setOnClickListener {
                if (tutorialViewPager.currentItem == 2) finishTutorial()
                else tutorialViewPager.currentItem++
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateToBrowseFragment() {
        val action = TutorialFragmentDirections.actionTutorialFragmentToBrowseFragment()
        findNavController().navigate(action)
    }

    fun finishTutorial() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean(getString(R.string.tutorial_preference_key), true)
            apply()
        }
        navigateToBrowseFragment()
    }
}