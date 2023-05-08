package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat


class ChildFragment : CustomStackFragment() {
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.child_fragment_layout, container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  
    view.findViewById<AppCompatImageView>(R.id.ivArrow).setOnClickListener {
      setArrowDrawable(view, onStateChanged.value ?: false)
      mListener?.messageFromCustomStackFragment()
      onStateChanged.postValue(!(onStateChanged.value ?: true))
    }
  }
  
  fun setArrowDrawable(view: View, isOpen: Boolean) {
    context?.let {
      val drawable = if(isOpen) R.drawable.baseline_keyboard_arrow_down_24 else R.drawable.baseline_keyboard_arrow_up_24
      view.findViewById<AppCompatImageView>(R.id.ivArrow).setImageDrawable(ContextCompat.getDrawable(it, drawable))
    }
  }
}