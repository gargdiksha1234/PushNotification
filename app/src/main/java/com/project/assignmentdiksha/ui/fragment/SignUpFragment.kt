package com.project.assignmentdiksha.ui.fragment


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.assignmentdiksha.R
import com.project.assignmentdiksha.base.BaseFragment
import com.project.assignmentdiksha.databinding.FragmentSignUpBinding
import com.project.assignmentdiksha.utils.ImageExtension
import com.project.assignmentdiksha.viewModel.SignupViewModel


class SignUpFragment : BaseFragment() {
    private lateinit var signUpViewModel:SignupViewModel
    private var imageUri: Uri?=null
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        initViewModel()
        initObserver()
    }

    private fun initViewModel() {
        signUpViewModel= ViewModelProvider(this)[SignupViewModel::class.java]
        binding.signupViewModel=signUpViewModel
        binding.lifecycleOwner=this

    }

    private fun setListener() {
        binding.tvSignupbutton.setOnClickListener{
            findNavController().navigate(R.id.welcomeFragment, null)
        }
        binding.ivUserimage.setOnClickListener {
            uploadImage()
        }
        binding.tvHaveaccount.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }
    private fun initObserver() {
        signUpViewModel.navigate.observe(this, Observer {
            if(it)
            {  findNavController().navigate(R.id.loginFragment, null)}

        })
    }
private fun uploadImage() {
    val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.camera_gallery, null)
    val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
    val messageBoxInstance = messageBoxBuilder.show()
    val camera1 = messageBoxView.findViewById(R.id.bt_camera) as Button
    camera1.setOnClickListener {
        messageBoxInstance.dismiss()
        camera()
        val gallery1 = messageBoxView.findViewById(R.id.bt_gallery) as Button
        gallery1.setOnClickListener() {
            messageBoxInstance.dismiss()
            gallery()
        }
    }
}

    private fun camera() {
        if (ImageExtension.checkPermissionGivenOrNot(
                requireContext(),
                android.Manifest.permission.CAMERA
            )
        ) {
            openCamera()
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 10)
            Log.d("camera","Camera Permission denied")
        }
    }
    private fun gallery() {
        if (ImageExtension.checkPermissionGivenOrNot(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            openGallery()
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 12)
            Log.d("gallery","Gallery Permission denied")
        }
    }
    private fun openCamera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLaunch.launch(camera)
    }
    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        resultLauncher.launch(gallery)
    }

    // set image in imageview from camera
    private var resultLaunch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val bmp: Bitmap = data?.extras?.get("data") as Bitmap
                val a = ImageExtension.getImageUri(
                    bmp,
                    Bitmap.CompressFormat.JPEG,
                    25,
                    activity?.contentResolver!!
                )
                imageUri = a!!
                binding.ivUserimage.setImageBitmap(bmp)
                signUpViewModel.path.value=imageUri.toString()
                // addImageToFirebase()
            }
        }
    // set image in imageview from gallery
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                imageUri = data!!.data!!
                binding.ivUserimage.setImageURI(imageUri)
                signUpViewModel.path.value=imageUri.toString()
                // addImageToFirebase()
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        requireActivity().onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 10) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
                Log.d("camera","Camera Permission Given")
            } else {

                Log.d("camera","Camera Permission denied")
            }
        } else {
            if (requestCode == 12) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                    Log.d("Gallery","Gallery Permission Given")
                } else {
                    Log.d("Gallery","Gallery Permission denied")                }
            }

        }

    }

}