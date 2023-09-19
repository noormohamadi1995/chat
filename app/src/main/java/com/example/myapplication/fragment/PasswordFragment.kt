package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPasswordBinding
import com.example.myapplication.utility.DialogHelper
import com.example.myapplication.viewModel.LoginViewModel
import com.example.myapplication.viewState.LoginSideEffect
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class PasswordFragment : Fragment() {
    private var mBinding : FragmentPasswordBinding? = null
    private val mArgs by navArgs<PasswordFragmentArgs>()
    private val mViewModel by viewModels<LoginViewModel>()
    private var loadingDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPasswordBinding.inflate(inflater,container,false).apply {
        mBinding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.observe(
            viewLifecycleOwner,
            sideEffect = ::handleSideEffect
        )
        mBinding?.apply {
            btnSendPassword.setOnClickListener {
                val code = edtCode.text.toString()
                code.takeIf { it.isNotEmpty() }?.let {
                    requireActivity().runOnUiThread {
                        loadingDialog = DialogHelper.createLoadingDialog(requireContext())
                    }
                    mViewModel.login(mArgs.phone,code)
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
            }
            is LoginSideEffect.SuccessSendCode -> {
                requireActivity().runOnUiThread {
                    loadingDialog?.dismiss()

                }
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