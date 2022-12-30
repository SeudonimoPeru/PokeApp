package com.jhon.apppokemon.features.dialogmygroup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhon.apppokemon.databinding.AlertDialogMyGroupBinding
import com.jhon.apppokemon.features.main.adapter.AdapterGrupos
import com.jhon.data.model.bean.grupo.Grupo

class DialogMyGroup(
    private val listGroup: List<Grupo> = listOf(),
    private val title: String,
    private val onClickConfirmCancel: () -> Unit,
    private val txtBtnCancelar: String = "",

    ) : DialogFragment() {

    private lateinit var binding: AlertDialogMyGroupBinding

    private val adapter = AdapterGrupos(object : AdapterGrupos.OnClickListener {
        override fun onClick(regi: Grupo) {
            //VOY A WEB VIEW


        }

    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog)
        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AlertDialogMyGroupBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

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

            //rcv
            apRcvRegions.layoutManager = LinearLayoutManager(requireContext())
            apRcvRegions.setHasFixedSize(true)
            apRcvRegions.adapter = adapter
            apRcvRegions.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter.swap(listGroup)
            if (txtBtnCancelar.isNotEmpty()) btnCancelar.text = txtBtnCancelar
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


}