package com.finddreams.oppositescrolltable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.finddreams.oppositescrolltable.model.getOptionChainData
import com.finddreams.oppositescrolltable.view.OppositeScrollTable
import com.finddreams.oppositescrolltable.ui.theme.OppositeScrollListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OppositeScrollListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val data = getOptionChainData(30)
                    Column(
                        modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text ="期权链",
                            color = Color.Black,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OppositeScrollTable(
                            data,
                            onHeaderClick = { header, isHeader -> })
                    }
                }
            }
        }
    }
}