package com.example.ucp2

import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanForm(
    dosenPilihan: List<String>,
    onSelectionChanged: (String) -> Unit,
    onPilihChanged: (String) -> Unit,

    onSubmitBUttonClicked: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var nama by rememberSaveable { mutableStateOf("") }
    var nim by rememberSaveable { mutableStateOf("") }
    var konsentrasi by rememberSaveable { mutableStateOf("") }
    var judul by remember { mutableStateOf("") }
    var dosenYgDipilih by remember { mutableStateOf("") }


    var listData: MutableList<String> = mutableListOf(nama, nim, konsentrasi, judul)

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = "Formulir Pengajuan Skripsi", fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = nama, onValueChange = { nama = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = stringResource(id = R.string.nama)) })
            OutlinedTextField(
                value = nim, onValueChange = { nim = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = stringResource(id = R.string.nim)) })
            OutlinedTextField(
                value = konsentrasi, onValueChange = { konsentrasi = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = {
                    Text(text = stringResource(id = R.string.konsentrasi))
                })
            OutlinedTextField(
                value = judul, onValueChange = { judul = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = {
                    Text(text = stringResource(id = R.string.judul))
                })
            Spacer(modifier = Modifier.padding(2.dp))
            Column(
                modifier = Modifier
                    .weight(1f, false)
                    .padding(dimensionResource(R.dimen.padding_small)),
                verticalArrangement = Arrangement.SpaceEvenly //atas ke bawah(make evenly biar rata kanan kiri)

            ) {
                Row {
                    Column {
                        Text(
                            text = "Dosen Pembimbing 1"
                        )
                        dosenPilihan.forEach { item ->
                            Row(modifier = Modifier.selectable(
                                selected = dosenYgDipilih == item,
                                onClick = {
                                    dosenYgDipilih = item
                                    onSelectionChanged(item)
                                }
                            ),
                                verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(selected = dosenYgDipilih == item,
                                    onClick = {
                                        dosenYgDipilih = item
                                        onSelectionChanged(item)
                                    }
                                )
                                Text(item)
                            }
                        }
                    }
                    Column{Text(
                        text = "Dosen Pembimbing 2"
                    )
                        dosenPilihan.forEach { item ->
                            Row(modifier = Modifier.selectable(
                                selected = dosenYgDipilih == item,
                                onClick = {
                                    dosenYgDipilih = item
                                    onPilihChanged(item)
                                }
                            ),
                                verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(selected = dosenYgDipilih == item,
                                    onClick = {
                                        dosenYgDipilih = item
                                        onPilihChanged(item)
                                    }
                                )
                                Text(item)
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .weight(1f, false),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onSubmitBUttonClicked(listData) })
                    {
                        Text(text = stringResource(id = R.string.btn_submit))
                    }
                }
            }
        }
}