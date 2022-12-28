package com.example.fragments.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.fragments.view.Fragment2ViewModel
import com.example.fragments.R
import com.example.fragments.databinding.FragmentDetailsBinding
import com.example.fragments.databinding.FragmentFragment2Binding


class DetailsFragment : Fragment() {
    private lateinit var viewModel: Fragment2ViewModel
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(Fragment2ViewModel::class.java)
        val binding = FragmentDetailsBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)

    }
}