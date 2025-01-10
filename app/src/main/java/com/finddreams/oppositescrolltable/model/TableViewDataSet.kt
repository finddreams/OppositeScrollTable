package com.finddreams.oppositescrolltable.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class TableViewSort {
    ASC,//升序
    DESC,//降序
    NONE
}
data class TableViewDataSet(
    val middleColumName: String,
    val middleColumWith: Dp,
    val itemHeight: Dp=50.dp,
    val childItems:ArrayList<TabViewItemsEntity>,
    val headers:ArrayList<TableViewHeaderEntity>,
)
