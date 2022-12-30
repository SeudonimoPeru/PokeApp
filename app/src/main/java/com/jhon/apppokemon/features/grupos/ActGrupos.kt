package com.jhon.apppokemon.features.grupos

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhon.apppokemon.activity.PrincipalActivity
import com.jhon.apppokemon.databinding.ActivityGruposBinding
import com.jhon.apppokemon.features.dialogadd.DialogAddGroup
import com.jhon.apppokemon.features.main.GruposViewModel
import com.jhon.apppokemon.features.main.adapter.AdapterGrupos
import com.jhon.data.model.bean.grupo.Grupo

class ActGrupos : PrincipalActivity() {
    private lateinit var binding: ActivityGruposBinding
    private var region = ""
    private var location = ""
    private val viewModel by lazy { ViewModelProvider(this)[GruposViewModel::class.java] }
    private val adapter = AdapterGrupos(object : AdapterGrupos.OnClickListener {
        override fun onClick(regi: Grupo) {
            //VOY A WEB VIEW


        }

    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGruposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            //rcv
            apRcvRegions.layoutManager = LinearLayoutManager(baseContext)
            apRcvRegions.setHasFixedSize(true)
            apRcvRegions.adapter = adapter
            apRcvRegions.addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))

            apSwpRefresh.setOnRefreshListener {
                viewModel.getAllListGroups()
            }
            btnAdd.setOnClickListener { showDialogAddGroup() }

        }
        setupObservers()
        loadData()
    }


    fun loadData() {
        region = intent.getStringExtra("name_region").toString()
        location = intent.getStringExtra("id_location").toString()
        viewModel.setupRegion(region)
        showProgress()
        viewModel.getAllListGroups()
        with(binding) {
            title.text = "Región " + region.toUpperCase()
        }

    }


    fun setupObservers() {
        viewModel.lstGrupos.observe(this) { data ->
            hideProgress()
            binding.apSwpRefresh.isRefreshing = false
            if (data.isNotEmpty()) adapter.swap(data)
        }
        viewModel.isloading.observe(this){
            if (it){
                showProgress()
            }else{
                hideProgress()
            }
        }
    }

    private fun showDialogAddGroup() {

        DialogAddGroup(
            title = "Agregar nuevo grupo",
            message = "Ingresa el nombre",
            onClickConfirmAcept = ::saveGroup,
            onClickConfirmCancel = { }

        ).show(supportFragmentManager,"dialog_add")
    }

    private fun saveGroup(nombreGrupo : String?){
        nombreGrupo?.let {
            if(it.isNotEmpty()){
               viewModel.insertGrupo(it)
            }
        }

    }


}