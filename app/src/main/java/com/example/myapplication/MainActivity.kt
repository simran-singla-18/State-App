package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity(), CustomStackFragment.OnFragmentInteractionListener {
  
  private val parentFragment by lazy { ParentFragment() }
  private var parentBottomSheetBehavior: BottomSheetBehavior<View>? = null
  
  private fun openParentFragment() {
    supportFragmentManager.beginTransaction().apply {
      replace(R.id.parent_fragment_container, parentFragment)
      commit()
    }
  }
  
  private fun setParentFragmentBehaviour() {
    
    parentBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.parent_fragment_container))
    
    parentBottomSheetBehavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
      override fun onStateChanged(bottomSheet: View, newState: Int) {
        if (newState == BottomSheetBehavior.STATE_DRAGGING || newState == BottomSheetBehavior.STATE_EXPANDED) {
          parentBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
      }
      
      override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    })
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    setOnClickListeners()
    openParentFragment()
    setParentFragmentBehaviour()
  }
  
  private fun setOnClickListeners() {
    findViewById<FrameLayout>(R.id.parent_fragment_container).setOnClickListener {
      toggleParentState()
    }
    
    findViewById<TextView>(R.id.toggleMain).setOnClickListener {
      toggleParentState()
    }
  }
  
  private fun toggleParentState() {
    if (parentBottomSheetBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
      parentBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    } else {
      parentBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }
  }
  
  override fun messageFromCustomStackFragment() {
    parentFragment.toggleChildSheetState()
  }
}