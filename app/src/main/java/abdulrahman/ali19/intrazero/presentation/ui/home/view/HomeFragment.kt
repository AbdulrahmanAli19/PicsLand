package abdulrahman.ali19.intrazero.presentation.ui.home.view

import abdulrahman.ali19.intrazero.databinding.FragmentHomeBinding
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.presentation.ui.home.adapters.PicsumAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var _pagesAdapter: PicsumAdapter? = null
    private val pageAdapter get() = _pagesAdapter!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _pagesAdapter = PicsumAdapter(onItemClick())

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = pageAdapter
                        visibility = View.VISIBLE
                    }
                    pageAdapter.submitData(it)
                }
            }
        }

    }

    private fun onItemClick(): (Page) -> (Unit) = {
        if (!it.isAd)
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