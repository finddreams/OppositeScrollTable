package com.finddreams.oppositescrolltable.model

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finddreams.oppositescrolltable.ui.theme.ColorStockRed

fun getOptionChainData(size: Int): TableViewDataSet {
    val tabViewListEntity = arrayListOf<TabViewItemsEntity>()
    // 构造期权链的列标题
    val cellWidthList = arrayListOf<TableViewHeaderEntity>()
    val headers = listOf(
         "买盘", "隐含波动率","涨跌幅","卖盘","到手盈亏平衡","涨跌额","成交量","最新价","成交额","盈亏平衡点","未平仓数","Gamma", "溢价", "Delta", "Theta"
    )
    headers.forEachIndexed { index, title ->
        cellWidthList.add(
            TableViewHeaderEntity(
                title = title,
                width = when {
                    title.length >= 6 -> 110.dp
                    title.length >= 4 -> 90.dp
                    title.length == 2 -> 60.dp
                    else -> 70.dp
                }
                ,
                asc = null,
            )
        )
    }
    // 构造每一行的数据
    repeat(size) {index->
        val tableViewItemEntities = arrayListOf<TableViewChildItemEntity>()

        // 设置期权链每一行的列内容（如不同的数值）
        repeat(headers.size) { num ->
            val value = when (num) {
                0 -> "13.95\nx40"
                1 -> "67.21%"
                2 -> "+30%"
                3 -> "50%"
                4 -> "5"
                else -> "--" // 其他列默认值
            }
            val color = if (num == 2) ColorStockRed else null // 买卖价用红色
            val tabViewItem = TableViewChildItemEntity(value, color = color, textSize = 13.sp)
            tableViewItemEntities.add(tabViewItem)
        }

        // 每一行的第一个列名
        val tabViewList = TabViewItemsEntity(childItemsLeft=tableViewItemEntities, childItemsMiddle = "${index+150}", childItemsRight = tableViewItemEntities)
        tabViewListEntity.add(tabViewList)
    }
    // 构造整个表格数据集
    val tableViewDataSet = TableViewDataSet(
        middleColumName = "期权链", // 第一个固定列的标题
        middleColumWith = 125.dp,
        childItems = tabViewListEntity,
        headers = cellWidthList,
    )
    return tableViewDataSet
}
