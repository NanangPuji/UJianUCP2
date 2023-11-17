package com.example.ucp2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.data.DataNama.dosen

enum class PengelolaHalaman {
    Home,
    Form,
    Detail
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengelolaHalaman(
    orderViewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold { innerPadding ->
        val uiState by orderViewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(PengelolaHalaman.Home.name){
                HalamanHome(
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Form.name)
                    })}
            composable(PengelolaHalaman.Form.name){
                val context = LocalContext.current
                HalamanForm(
                    dosenPilihan = dosen.map{ id -> context.resources.getString(id)},
                    onSelectionChanged = { orderViewModel.setD1(it)},
                    onPilihChanged = { orderViewModel.setD2(it)},
                    onSubmitBUttonClicked = {
                        orderViewModel.setC(it)
                        navController.navigate(PengelolaHalaman.Detail.name)},
                )}
            composable(
                route = PengelolaHalaman.Detail.name){
                HalamanDetail(orderUIState = uiState, onCancelButtonClicked = { cancelOrderAndNavigateToForm(navController) },
                )
            }
        }
    }
}
private fun cancelOrderAndNavigateToForm(
    navController: NavHostController
){
    navController.popBackStack(PengelolaHalaman.Form.name, inclusive = false)
}