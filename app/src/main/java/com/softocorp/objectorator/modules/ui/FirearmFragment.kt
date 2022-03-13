package com.softocorp.objectorator.modules.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softocorp.objectorator.R
import com.softocorp.objectorator.databinding.FragmentFirearmBinding
import com.softocorp.objectorator.model.ReadingModel

class FirearmFragment : Fragment() {

    private var _binding: FragmentFirearmBinding? = null

    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirearmBinding.inflate(inflater, container, false)

        val list: ArrayList<ReadingModel> = ArrayList()
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        list.add(ReadingModel(-1, "Firearm", "Accuracy: 80%"))
        val adapter = ReadingAdapter(list)
        binding.recyclerFirearm.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}