package com.finddreams.oppositescrolltable

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.finddreams.oppositescrolltable.ui.theme.ColorDivide

@Composable
internal fun CommDivider() {
    HorizontalDivider(thickness = Dp.Hairline, color = ColorDivide)
}

@Composable
fun HorizontalSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}
@Composable
fun Modifier.clickableNoRipple(onClick: () -> Unit
): Modifier {
    return this.clickable(
        indication = null,  // 取消涟漪效
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick.invoke()
    }

}

@Composable
internal fun CenterText(text: String,modifier: Modifier= Modifier) {
    Box(
        modifier = Modifier
            .width(60.dp)
            .fillMaxHeight()
//            .background(
//                brush = Brush.horizontalGradient(
//                    colors = listOf(
//                        Color.Black.copy(alpha = 0.1f), // 左侧阴影
//                        Color.Transparent, // 中间透明
//                        Color.Black.copy(alpha = 0.1f) // 右侧阴影
//                    )
//                )
//            )
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center), // 将内容居中放置
            color = Color.White,
            shape = RoundedCornerShape(0.dp),
            shadowElevation = 2.dp // 表示表面阴影高度
        ) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = text, textAlign = TextAlign.Center)
            }
        }
    }
//    Card(
//        modifier = modifier
//            .width(60.dp)
//            .fillMaxHeight(),
//        colors= CardDefaults.cardColors(
//            containerColor = Color.White, // 卡片背景颜色
//        ),
//        shape = RoundedCornerShape(0.dp), // 圆角
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 1.dp
//        ),
//        border = BorderStroke(1.dp, ColorShow)
//    ) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(text = text, textAlign = TextAlign.Center)
//        }
//    }
}
val Dp.dpToPx
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)
        .toInt()

fun Modifier.drawSelectBorder(
    isShowSelected: Boolean = false,
    drawLeft: Boolean = false, // 是否绘制左边框
    drawRight: Boolean = false // 是否绘制右边框
): Modifier = this.drawBehind {
    if (isShowSelected&&(drawLeft||drawRight)){
        val strokeWidth = 1.5.dp.toPx() // 边框宽度
        val inset = strokeWidth/2

        // 绘制左边框
        if (drawLeft) {
            drawLine(
                color = Color.Black,
                start = Offset(inset, inset),
                end = Offset(inset, size.height - inset),
                strokeWidth = strokeWidth
            )
        }

        // 绘制上边框
        drawLine(
            color = Color.Black,
            start = Offset(0f, inset),
            end = Offset(size.width, inset),
            strokeWidth = strokeWidth
        )

        // 绘制下边框
        drawLine(
            color = Color.Black,
            start = Offset(0f, size.height - inset),
            end = Offset(size.width, size.height - inset),
            strokeWidth = strokeWidth
        )

        // 绘制右边框
        if (drawRight) {
            drawLine(
                color = Color.Black,
                start = Offset(size.width - inset, inset),
                end = Offset(size.width - inset, size.height - inset),
                strokeWidth = strokeWidth
            )
        }
    }
}
/**
 * 将数字转换成compose中的DP
 */
val Number.pxToDp
    get() = Dp(toFloat() / Resources.getSystem().displayMetrics.density)

@Composable
fun Modifier.testUIBorder(): Modifier {
    return this.border(1.pxToDp, color = Color.Red)
}