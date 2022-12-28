package com.example.fragments.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fragments.PrefsHelper
import com.example.fragments.R
import com.example.fragments.data.Menudata
import com.example.fragments.view.Fragment2ViewModel

class Fragment2 : Fragment(), Recycler.MenuItemListener {

    private lateinit var viewModel: Fragment2ViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var adapter: Recycler
    private lateinit var navController: NavController
    private lateinit var newaarry: ArrayList<Menudata>
     lateinit var image: Array<Int>
    lateinit var name: Array<String>
    var sel: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        image = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.d1,
            R.drawable.d2,
            R.drawable.d3,
            R.drawable.d8,
            R.drawable.d4,
            R.drawable.d5,
            R.drawable.d6,
            R.drawable.d7)
        name = arrayOf(
            "soup",
            "Chicken",
            "Vegetables",
            "Bread",
            "Salad",
            "Beef",
            "Cold drinks",
            "Lemon",
            "Fresh",
            "Smoothie",
            "Coffee",
            "Tea",
            "Nescafe",
            "Turkey C.")


        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)
        val view = inflater.inflate(R.layout.fragment_fragment2, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutStyle = PrefsHelper.getItemType(requireContext())

        recyclerView.layoutManager =
            if (layoutStyle == "grid") {
                GridLayoutManager(requireContext(), 2)
            } else {
                LinearLayoutManager(requireContext())

            }
        recyclerView.setHasFixedSize(true)
        newaarry = arrayListOf()
        getdata()
        viewModel = ViewModelProvider(requireActivity()).get(Fragment2ViewModel::class.java)



        return view
    }


    override fun onMenuItemClick(menudata: Menudata) {
        viewModel.selectedMenu.value = menudata
        navController.navigate(R.id.action_fragment2_to_DetailsFragment)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_main, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    private fun getdata() {
        for (i in image.indices) {
            val menu = Menudata(image[i], name[i], sel = false, price=34)
            newaarry.add(menu)
        }
        recyclerView.adapter = Recycler(newaarry, this)


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_view_grid -> {
                PrefsHelper.setItemType(requireContext(), "grid")
                recyclerView.layoutManager =
                    GridLayoutManager(requireContext(), 2)
                recyclerView.adapter = Recycler(newaarry, this)
            }
            R.id.action_view_list -> {
                PrefsHelper.setItemType(requireContext(), "list")
                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext())
                recyclerView.adapter = Recycler(newaarry, this)
            }

            R.id.action_settings -> {
                navController.navigate(R.id.settingsActivity)

            }
        }
        return true
    }

}