package abdulrahman.ali19.intrazero.presentation.ui.details_screen

import abdulrahman.ali19.intrazero.R
import abdulrahman.ali19.intrazero.databinding.FragmentDetailsBinding
import abdulrahman.ali19.intrazero.utils.setGlideImg
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

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
        binding.mainImage.setGlideImg(args.imageUrl,
            onFailed = {},
            onSuccess = { resource ->
                Palette.from(resource!!.toBitmap()).generate {
                    val dominantColor =
                        it?.getDominantColor(
                            ContextCompat.getColor(requireContext(), R.color.white)
                        )!!
                    binding.parent.background = ColorDrawable(dominantColor)
                }
            })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}