package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior


class ParentFragment : CustomStackFragment() {
  
  private var childBottomSheetBehavior: BottomSheetBehavior<View>? = null
  private val childFragment by lazy { ChildFragment() }
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.parent_fragment_layout, container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    childFragmentManager.beginTransaction().replace(R.id.child_fragment_container, childFragment).commit()
    setChildBottomSheetBehaviour(view)
    super.onViewCreated(view, savedInstanceState)
  }
  
  private fun setChildBottomSheetBehaviour(view: View) {
    childBottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.child_fragment_container))
    childBottomSheetBehavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
      override fun onStateChanged(bottomSheet: View, newState: Int) {}
      override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    })
    
    view.findViewById<TextView>(R.id.tvToggle).setOnClickListener {
      toggleChildSheetState()
    }
  }
  
  fun toggleChildSheetState() {
    childFragment.view?.let {
      childFragment.setArrowDrawable(it, childBottomSheetBehavior?.state != BottomSheetBehavior.STATE_EXPANDED)
    }
    
    if (childBottomSheetBehavior?.state != BottomSheetBehavior.STATE_EXPANDED) {
      childBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    } else {
      childBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }
  }
}