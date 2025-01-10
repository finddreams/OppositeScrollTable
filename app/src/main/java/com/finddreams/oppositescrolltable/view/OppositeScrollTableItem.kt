package com.finddreams.oppositescrolltable.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finddreams.oppositescrolltable.CenterText
import com.finddreams.oppositescrolltable.clickableNoRipple
import com.finddreams.oppositescrolltable.drawSelectBorder
import com.finddreams.oppositescrolltable.model.TabViewItemsEntity
import com.finddreams.oppositescrolltable.model.TableViewChildItemEntity
import com.finddreams.oppositescrolltable.model.TableViewHeaderEntity

/**
 * 滑动表格子项
 * Copyright (c) finddreams https://github.com/finddreams
 */
@Composable
internal fun ScrollTableCellItem(
    horizontalScrollState: ScrollState,
    childItems: TabViewItemsEntity,
    headerList: ArrayList<TableViewHeaderEntity>,
    isShowSelected: Boolean = false,
    onItemClick: () -> Unit
) {
    // 记录当前选中的区域，初始为左边区域选中
    var isSelectedLeft by remember { mutableStateOf(true) }

    Row(
        Modifier
            .height(50.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 左侧区域
        Box(
            modifier = Modifier
                .weight(0.5f)
                .drawSelectBorder(
                    isShowSelected,
                    drawLeft = isSelectedLeft,
                    drawRight = false
                )
                .clickableNoRipple {
                    // 点击后选中左边区域
                    isSelectedLeft = true
                    onItemClick()
                }
        ) {
            MoveItemView(false, horizontalScrollState, childItems.childItemsLeft, headerList)
        }
        CenterText(
            text = childItems.childItemsMiddle, modifier = Modifier.drawSelectBorder(
                isShowSelected,
                drawLeft = !isSelectedLeft,
                drawRight = isSelectedLeft
            )
        )

        // 右侧区域
        Box(
            modifier = Modifier
                .weight(0.5f)
                .drawSelectBorder(
                    isShowSelected,
                    drawLeft = false,
                    drawRight = !isSelectedLeft
                )
                .clickableNoRipple {
                    // 点击后选中右边区域
                    isSelectedLeft = false
                    onItemClick()
                }
        ) {
            MoveItemView(true, horizontalScrollState, childItems.childItemsRight, headerList)
        }
    }
}

@Composable
private fun MoveItemView(
    isRight: Boolean,
    horizontalScrollState: ScrollState,
    childItems: ArrayList<TableViewChildItemEntity>,
    headerList: ArrayList<TableViewHeaderEntity>
) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .horizontalScroll(horizontalScrollState, reverseScrolling = isRight),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isRight) Arrangement.End else Arrangement.Start
    ) {
        //左边的要倒着排序
        val displayedList = if (isRight) childItems else childItems.asReversed()
        val displayedHeader = if (isRight) headerList else headerList.asReversed()
        displayedList.forEachIndexed { index, data ->
            Text(
                text = data.value,
                color = data.color ?: Color.Black,
                textAlign = TextAlign.Center,
                fontSize = data.textSize ?: 12.sp,
                modifier = Modifier.width(displayedHeader[index].width)
            )
        }
    }
}