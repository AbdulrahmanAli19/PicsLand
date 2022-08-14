package abdulrahman.ali19.intrazero.presentation.ui.home.view

import abdulrahman.ali19.intrazero.databinding.FragmentHomeBinding
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.presentation.ui.home.adapters.HomePageAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.buffer

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
        viewModel.getPages("1", "10")
        _pagesAdapter = HomePageAdapter(onItemClick())
        lifecycleScope.launchWhenCreated {
            viewModel.getPagesState.buffer().collect {
                if (it.isEmpty) {
                    //TODO: Show the empty layout
                    binding.recyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                } else if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else if (it.data.isNotEmpty()) {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = pageAdapter
                        visibility = View.VISIBLE
                    }
                    pageAdapter.setList(it.data)
                } else if (it.error.isNotEmpty()) {
                    //TODO: Show the error layout
                    Log.d(TAG, "onViewCreated: ${it.error}")
                }
            }
        }

    }

    private fun onItemClick(): (Page) -> (Unit) = {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToDetailsFragment(
                authorName = it.author,
                imageUrl = it.imageUrl
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _pagesAdapter = null
    }

}