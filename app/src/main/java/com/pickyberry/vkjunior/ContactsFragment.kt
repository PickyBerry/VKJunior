package com.pickyberry.vkjunior

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pickyberry.vkjunior.databinding.FragmentContactsBinding


//Список контактов
class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var adapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentContactsBinding.inflate(layoutInflater)
        adapter=ContactsAdapter()
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerView.context,
            LinearLayoutManager(requireContext()).orientation
        )
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        return binding.root
    }

}