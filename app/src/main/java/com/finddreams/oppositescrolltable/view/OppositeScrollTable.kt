package com.finddreams.oppositescrolltable.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finddreams.oppositescrolltable.CommDivider
import com.finddreams.oppositescrolltable.model.TableViewDataSet
import com.finddreams.oppositescrolltable.model.TableViewHeaderEntity
import com.finddreams.oppositescrolltable.model.getOptionChainData
import com.finddreams.oppositescrolltable.ui.theme.ColorDivide

@Composable
fun OppositeScrollTable(
    tableDataSet: TableViewDataSet,
    onHeaderClick: (TableViewHeaderEntity, Boolean) -> Unit,
) {
    val horizontalScrollState = rememberScrollState()
    val lazyListState = rememberLazyListState()
    LaunchedEffect(Unit) {
        horizontalScrollState.scrollTo(horizontalScrollState.maxValue)
    }
    val headers = tableDataSet.headers
    var mOffSet by remember { mutableIntStateOf(0) }
    LaunchedEffect(lazyListState) {
        snapshotFlow {
            lazyListState.firstVisibleItemIndex to lazyListState.firstVisibleItemScrollOffset
        }.collect { (index, offset) ->
            mOffSet = offset
            println("第一个可见项索引: $index，偏移量: $offset")
        }
    }
    var mSelectIndex by remember { mutableIntStateOf(-1) }
    var mIndicatorIndex by remember { mutableIntStateOf(5) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        ScrolledCellItemHeader(horizontalScrollState, tableDataSet, onHeaderClick = {})
        CommDivider()
        Box {
            LazyColumn() {
                itemsIndexed(tableDataSet.childItems) { i, item ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            ScrollTableCellItem(
                                horizontalScrollState,
                                item,
                                headers,
                                mSelectIndex == i,
                                onItemClick = {
                                    mSelectIndex = i
                                })
                            HorizontalDivider(color = ColorDivide)
                        }

                        if (i == mIndicatorIndex) {
                            CenterBox(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .offset(y = 10.dp)
                            )
                        }
                        if (i == mIndicatorIndex + 1) {
                            CenterBox(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .offset(y = (-10).dp),
                                text = item.childItemsMiddle
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
private fun BoxScope.CenterBox(modifier: Modifier, text: String? = null) {
    Box(
        modifier = modifier
            .width(80.dp)
            .height(20.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (!text.isNullOrEmpty()) {
            Text(
                text = text,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview(name = "TwoColumTableview")
@Composable
private fun PreviewTwoColumTableview() {
    val data = getOptionChainData(30)
    OppositeScrollTable(data, onHeaderClick = { header, isHeader -> })
}