package abdulrahman.ali19.intrazero.presentation.ui.details_screen

import abdulrahman.ali19.intrazero.databinding.FragmentDetailsBinding
import abdulrahman.ali19.intrazero.utils.setCoilImage
import abdulrahman.ali19.intrazero.utils.setDominantBackgroundColorWithAnimation
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainImage.setCoilImage(args.imageUrl, onSuccess = { image ->
            binding.parent.setDominantBackgroundColorWithAnimation(
                image,
                colorFrom = (binding.parent.background as ColorDrawable).color
            )
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}