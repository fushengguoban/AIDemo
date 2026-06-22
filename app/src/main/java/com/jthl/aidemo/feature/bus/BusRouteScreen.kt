package com.jthl.aidemo.feature.bus

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class RouteInfo(
    val routeName: String,
    val price: String,
    val time: String,
    val status: String,
    val isArrived: Boolean,
    val stations: List<StationInfo>
)

data class StationInfo(
    val name: String,
    val isCurrent: Boolean = false,
    val hasBus: Boolean = false
)

@Composable
fun BusRouteScreen() {
    // Generate some mock data
    val mockStations30 = List(30) { index ->
        StationInfo(
            name = if (index == 0) "起始站" else if (index == 29) "终点站" else if (index == 10) "最长名称字段展示" else "站名称",
            isCurrent = index == 8,
            hasBus = index == 2 || index == 8
        )
    }
    
    val mockStations60 = List(60) { index ->
        StationInfo(
            name = if (index == 0) "起始站" else if (index == 59) "终点站" else "站名",
            isCurrent = index == 20,
            hasBus = index == 5 || index == 20 || index == 45
        )
    }

    val mockRoutes = listOf(
        RouteInfo("121路", "2元", "07:05-21:50", "已到站", true, mockStations30),
        RouteInfo("121路", "2元", "07:05-21:50", "即将到站", false, mockStations60),
        RouteInfo("121路", "2元", "07:05-21:50", "即将到站", false, mockStations30)
    )

    // A page is 3 routes. We will just duplicate it to make 3 pages for demonstration.
    val pages = listOf(mockRoutes, mockRoutes, mockRoutes)

    val pagerState = rememberPagerState(pageCount = { pages.size })

    val isInspection = androidx.compose.ui.platform.LocalInspectionMode.current

    // Auto-scroll logic (every 30 seconds flip page, wrap around)
    LaunchedEffect(pagerState, isInspection) {
        if (!isInspection) {
            while (true) {
                delay(30000)
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomBarView(pagerState.currentPage, pagerState.pageCount)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))
                .padding(paddingValues)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false // Not touchable / un-swipeable
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Spacer(modifier = Modifier.height(0.dp))
                    pages[page].forEach { route ->
                        RouteCard(route = route)
                    }
                }
            }
        }
    }
}

@Composable
fun RouteCard(route: RouteInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min) // Allows left panel and divider to fill height
        ) {
            RouteLeftPanel(route, modifier = Modifier.fillMaxHeight())
            
            HorizontalDivider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight(),
                color = Color(0xFFEEEEEE)
            )
            
            BusRouteLineView(
                stations = route.stations,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp, horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun RouteLeftPanel(route: RouteInfo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(100.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = route.routeName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "票价: ${route.price}",
            fontSize = 12.sp,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), color = Color(0xFF333333))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "首末班时间",
            fontSize = 10.sp,
            color = Color(0xFF666666)
        )
        Text(
            text = route.time,
            fontSize = 10.sp,
            color = Color(0xFF666666),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(16.dp))
        
        val buttonBgColor = if (route.isArrived) Color(0xFFE91E63) else Color(0xFFFFB300)
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(buttonBgColor, RoundedCornerShape(4.dp))
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = route.status,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BusRouteLineView(stations: List<StationInfo>, modifier: Modifier = Modifier) {
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    val availableWidth = configuration.screenWidthDp.dp - 24.dp - 100.dp - 8.dp
    val itemWidthDp = availableWidth / stations.size
    // Dynamic font size depending on available width. Fits up to 60 items nicely by scaling down.
    val calculatedFontSize = itemWidthDp.value * 0.9f
    val fontSize = calculatedFontSize.coerceIn(6f, 12f).sp

    Box(modifier = modifier.fillMaxWidth()) {
        // Draw dashed blue line
        Canvas(modifier = Modifier.matchParentSize()) {
            val itemWidthPx = size.width / stations.size
            val lineY = 25.dp.toPx() // 20.dp (bus icon) + 2.dp (dot top padding) + 3.dp (half dot size)
            val halfItemWidth = itemWidthPx / 2
            val startX = halfItemWidth
            val endX = size.width - 2.dp.toPx() // Arrow end

            drawLine(
                color = Color(0xFF4285F4),
                start = Offset(startX, lineY),
                end = Offset(endX, lineY),
                strokeWidth = 1.5.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )

            // Arrow at the end
            val arrowSize = 4.dp.toPx()
            val path = androidx.compose.ui.graphics.Path().apply {
                moveTo(endX + arrowSize, lineY)
                lineTo(endX - arrowSize, lineY - arrowSize)
                lineTo(endX - arrowSize, lineY + arrowSize)
                close()
            }
            drawPath(path, color = Color(0xFF4285F4))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stations.forEach { station ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    // Bus Icon
                    Box(modifier = Modifier.height(20.dp), contentAlignment = Alignment.Center) {
                        if (station.hasBus) {
                            Icon(
                                imageVector = Icons.Default.DirectionsBus,
                                contentDescription = null,
                                tint = Color(0xFFE91E63),
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                    
                    // Dot
                    val dotColor = if (station.isCurrent) Color(0xFFE91E63) else Color(0xFF4285F4)
                    Box(
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(dotColor)
                    )
                    
                    // Vertical Text
                    val textColor = if (station.isCurrent) Color(0xFFE91E63) else Color(0xFF666666)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        station.name.forEach { char ->
                            Text(
                                text = char.toString(),
                                fontSize = fontSize,
                                color = textColor,
                                lineHeight = fontSize * 1.1f
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBarView(currentPage: Int, pageCount: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF51B7E6)) // Light blue bottom bar
            .height(36.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "V1.8.0",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { index ->
                val isSelected = index == currentPage
                Box(
                    modifier = Modifier
                        .width(if (isSelected) 24.dp else 8.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isSelected) Color.White else Color.White.copy(alpha = 0.5f))
                )
            }
        }
    }
}

@Preview(widthDp = 1080, heightDp = 1920, ) // Landscape preview to better simulate the screenshot
@Composable
fun BusRouteScreenPreview() {
    MaterialTheme {
        BusRouteScreen()
    }
}
