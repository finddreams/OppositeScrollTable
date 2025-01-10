package com.finddreams.oppositescrolltable.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

data class TabViewItemsEntity(
    val childItemsLeft:ArrayList<TableViewChildItemEntity>,
    val childItemsMiddle: String,
    val childItemsRight:ArrayList<TableViewChildItemEntity>,
)

data class TableViewHeaderEntity(
    val  title:String = "",
    val  width:Dp = 60.dp,
    var  asc: TableViewSort? = null,
    var  sortType:Int = 0
)
data class TableViewChildItemEntity(
    val value:String,
    val color: Color? = null,
    val textSize: TextUnit? = null,
)