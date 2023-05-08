package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

open class CustomStackFragment : Fragment() {
  
  val onStateChanged: MutableLiveData<Boolean> = MutableLiveData()
  var mListener: OnFragmentInteractionListener? = null
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    onStateChanged.observe(viewLifecycleOwner) {
      if (it)
        Toast.makeText(activity?.applicationContext, "stateChange", Toast.LENGTH_SHORT).show()
    }
  }
  
  override fun onAttach(context: Context) {
    super.onAttach(context)
    mListener = if (context is OnFragmentInteractionListener) {
      context
    } else {
      throw RuntimeException(
        context.toString()
            + " must implement OnFragmentInteractionListener"
      )
    }
  }
  
  override fun onDetach() {
    super.onDetach()
    mListener = null
  }
  
  interface OnFragmentInteractionListener {
    fun messageFromCustomStackFragment()
  }
}