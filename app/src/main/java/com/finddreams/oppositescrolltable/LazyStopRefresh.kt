package com.finddreams.oppositescrolltable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun LazyColumnWithRefreshOnScrollStop() {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    // 初始化包含 50 个元素的列表
    var dataList by remember { mutableStateOf((1..50).map { "Item $it" }) }
    var lastScrollUpdateTime by remember { mutableStateOf(0L) }
    val scrollStopThreshold = 300L // 滚动停止的时间阈值，单位为毫秒

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.isScrollInProgress }
            .collectLatest { isScrolling ->
                if (!isScrolling) {
                    // 滚动停止后执行刷新逻辑
                    coroutineScope.launch {
                        println("Scroll stopped, performing update")
                        val visibleItems = lazyListState.layoutInfo.visibleItemsInfo
                        val updatedList = dataList.toMutableList()
                        for (itemInfo in visibleItems) {
                            val position = itemInfo.index
                            updatedList[position] = updatedList[position] + " Updated"
                        }
                        dataList = updatedList
                    }
                }
            }
    }



    LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
        items(dataList) { item ->
            // 显示列表项
            Text(text = item, modifier = Modifier.padding(16.dp))
        }
    }
}


@Preview(name = "LazyStopRefresh")
@Composable
private fun PreviewLazyStopRefresh() {
    LazyColumnWithRefreshOnScrollStop()
}