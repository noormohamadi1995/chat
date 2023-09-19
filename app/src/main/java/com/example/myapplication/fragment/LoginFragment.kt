package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.utility.DialogHelper
import com.example.myapplication.utility.gone
import com.example.myapplication.utility.visible
import com.example.myapplication.viewModel.LoginViewModel
import com.example.myapplication.viewState.LoginSideEffect
import com.example.myapplication.viewState.LoginViewState
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var mBinding : FragmentLoginBinding? = null
    private val mViewModel by viewModels<LoginViewModel>()
    private var loadingDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater,container,false).apply {
        mBinding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.observe(
            viewLifecycleOwner,
            sideEffect = ::handleSideEffect
        )

        mBinding?.apply {
            btnSendCode.setOnClickListener {
                val phone = edtPhone.text.toString()
                phone.takeIf { it.isNotEmpty() }?.let {
                    requireActivity().runOnUiThread {
                        loadingDialog = DialogHelper.createLoadingDialog(requireContext())
                    }
                    mViewModel.generateCode(it)
                }
            }
        }
    }

    private fun handleSideEffect(loginSideEffect: LoginSideEffect) = mBinding?.apply {
        when(loginSideEffect){
            LoginSideEffect.ErrorSendCode -> {
                requireActivity().runOnUiThread {
                    loadingDialog?.dismiss()
                }

                Toast.makeText(requireContext(),"UnSuccessfully!",Toast.LENGTH_SHORT).show()
            }
            is LoginSideEffect.SuccessSendCode -> {
                requireActivity().runOnUiThread {
                    loadingDialog?.dismiss()
                }
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPasswordFragment(phone = loginSideEffect.phone))
            }
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}