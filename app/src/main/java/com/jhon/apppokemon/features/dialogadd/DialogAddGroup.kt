package com.jhon.apppokemon.features.dialogadd

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.DialogFragment

import com.jhon.apppokemon.R
import com.jhon.apppokemon.databinding.AlertDialogTwoOptionBinding

class DialogAddGroup(
    @DrawableRes private val icon: Int = R.drawable.ic_add,
    private val title: String,
    private val message: String,
    private val onClickConfirmAcept: (nombreGrupo: String) -> Unit,
    private val onClickConfirmCancel: () -> Unit,
    private val txtBtnAceptar: String = "",
    private val txtBtnCancelar: String = "",

    ) : DialogFragment() {

    private lateinit var binding: AlertDialogTwoOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog)
        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AlertDialogTwoOptionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnAcept.setOnClickListener {
                dialog?.dismiss()
                onClickConfirmAcept.invoke(etNombreGrupo.text.toString())

            }
            btnCancelar.setOnClickListener {
                dialog?.dismiss()
                onClickConfirmCancel.invoke()
            }
        }

        setupDialogValues()


    }

    fun setupDialogValues() {
        with(binding) {
            tvTitle.text = title
            tvContent.text = message
            ivIcon.setImageResource(icon)
            if (txtBtnAceptar.isNotEmpty()) btnAcept.text = txtBtnAceptar
            if (txtBtnCancelar.isNotEmpty()) btnCancelar.text = txtBtnCancelar
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


}