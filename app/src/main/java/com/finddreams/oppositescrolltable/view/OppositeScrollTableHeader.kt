package com.finddreams.oppositescrolltable.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.finddreams.oppositescrolltable.CenterText
import com.finddreams.oppositescrolltable.model.TableViewDataSet
import com.finddreams.oppositescrolltable.model.TableViewHeaderEntity


@Composable
internal fun ScrolledCellItemHeader(
    horizontalScrollState: ScrollState,
    dataSet: TableViewDataSet,
    onHeaderClick: (TableViewHeaderEntity) -> Unit,
) {
    val headers = dataSet.headers
    val headerList = remember { headers }
    val localHeaderList = remember {
        mutableStateListOf<TableViewHeaderEntity>().apply {
            addAll(headerList)
        }
    }

    Row(
        Modifier
            .height(35.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RightMoveHeaderItemView(
            Modifier
                .weight(0.5f), false, horizontalScrollState, localHeaderList, onHeaderClick
        )
        CenterText("行权价")
        RightMoveHeaderItemView(
            Modifier
                .weight(0.5f), true, horizontalScrollState, localHeaderList, onHeaderClick
        )
    }

}


@Composable
private fun RightMoveHeaderItemView(
    modifier: Modifier,
    isRight: Boolean,
    horizontalScrollState: ScrollState,
    localHeaderList: SnapshotStateList<TableViewHeaderEntity>,
    onHeaderClick: (TableViewHeaderEntity) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable {

            }) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .horizontalScroll(horizontalScrollState, reverseScrolling = isRight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val displayedList = if (isRight) localHeaderList else localHeaderList.asReversed()
            repeat(displayedList.size) { index ->
                val headerItem = displayedList[index]
                Row(
                    Modifier
                        .fillMaxHeight()
                        .width(headerItem.width),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = " ${headerItem.title}",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
