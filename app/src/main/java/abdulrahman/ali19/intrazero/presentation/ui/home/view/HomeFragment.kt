package abdulrahman.ali19.intrazero.presentation.ui.home.view

import abdulrahman.ali19.intrazero.databinding.FragmentHomeBinding
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.presentation.ui.home.adapters.HomePageAdapter
import abdulrahman.ali19.intrazero.utils.NetworkResult
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var _pagesAdapter: HomePageAdapter? = null
    private val pageAdapter get() = _pagesAdapter!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _pagesAdapter = HomePageAdapter(onItemClick())
        viewModel.getPagesState.observe(viewLifecycleOwner) {
            when (it) {
                NetworkResult.EmptyResult -> {
                    Log.d(TAG, "onViewCreated: EmptyResult")
                }
                is NetworkResult.Error -> {
                    Log.d(TAG, "onViewCreated: ${it.errorString}")
                }
                NetworkResult.Loading -> {
                    Log.d(TAG, "onViewCreated: Loading..")
                }
                is NetworkResult.Success -> {
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = pageAdapter
                    }
                    pageAdapter.setList(it.data)
                }
            }
        }
    }

    private fun onItemClick(): (Page) -> (Unit) = {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _pagesAdapter = null
    }

}