package com.jthl.aidemo.feature.vehicle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun VehicleScreenPreview() {
    MaterialTheme {
        VehicleScreen()
    }
}

@Composable
fun VehicleScreen() {
    // Define some colors matching the design
    val backgroundColor = Color(0xFFF7F8FA)
    val topGradientColors = listOf(Color(0xFFEAF2FF), Color(0xFFF7F8FA))
    val primaryTextColor = Color(0xFF333333)
    val secondaryTextColor = Color(0xFF999999)
    val blueColor = Color(0xFF2B68E3)
    val cardBgColor = Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top Section with gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(topGradientColors))
                    .padding(top = 48.dp) // Simulate status bar padding
            ) {
                Column {
                    // Top Bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(
                            text = "我的车辆",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryTextColor
                        )
                        Icon(
                            imageVector = Icons.Default.CropFree,
                            contentDescription = "Scan",
                            tint = primaryTextColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Vehicle Info
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 16.dp, top = 20.dp, bottom = 48.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "重型自卸货车",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blueColor
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "浙 A00000F",
                                fontSize = 16.sp,
                                color = Color(0xFF8BA6D7),
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                TagItem(text = "黄牌", bgColor = Color(0xFFFFF4E5), textColor = Color(0xFFFFA000), icon = Icons.Default.CreditCard)
                                TagItem(text = "电车", bgColor = Color(0xFFE8F5E9), textColor = Color(0xFF4CAF50), icon = Icons.Default.ElectricCar)
                            }
                        }
                        
                        // Placeholder for the truck image, using an icon as fallback
                        Box(
                            modifier = Modifier
                                .size(140.dp)
                                .offset(y = (-15).dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalShipping, 
                                contentDescription = "Truck", 
                                tint = blueColor.copy(alpha = 0.2f),
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            // Status Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (-30).dp), // Overlap with the top gradient
                colors = CardDefaults.cardColors(containerColor = cardBgColor),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatusItem(value = "运输中", label = "状态")
                    Divider(modifier = Modifier.height(30.dp).width(1.dp), color = Color(0xFFF0F0F0))
                    StatusItem(value = "5趟", label = "今日趟次")
                    Divider(modifier = Modifier.height(30.dp).width(1.dp), color = Color(0xFFF0F0F0))
                    StatusItem(value = "65km", label = "续航里程")
                }
            }

            // Grid Menu
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (-10).dp) 
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MenuItem(icon = Icons.Rounded.Badge, label = "车辆档案", iconColor = Color(0xFF4285F4))
                    MenuItem(icon = Icons.Rounded.Description, label = "处置证", iconColor = Color(0xFF4285F4))
                    MenuItem(icon = Icons.Rounded.DirectionsCar, label = "能耗管理", iconColor = Color(0xFF4285F4))
                    MenuItem(icon = Icons.Rounded.CarCrash, label = "维保记录", iconColor = Color(0xFF4285F4))
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MenuItem(icon = Icons.Rounded.History, label = "运送记录", iconColor = Color(0xFF4285F4))
                    MenuItem(icon = Icons.Rounded.Warning, label = "违规记录", iconColor = Color(0xFF4285F4))
                    MenuItem(icon = Icons.Rounded.LocalGasStation, label = "加油站点", iconColor = Color(0xFF4285F4))
                    MenuItem(icon = Icons.Rounded.Assessment, label = "车辆报告", iconColor = Color(0xFF4285F4))
                }
            }

            // Unbind Button
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 96.dp)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF2F3F5),
                    contentColor = secondaryTextColor
                ),
                shape = RoundedCornerShape(22.dp),
                elevation = null
            ) {
                Text(text = "解绑车辆", fontSize = 15.sp, fontWeight = FontWeight.Normal)
            }
            
            // Give space at the bottom above the navigation bar
            Spacer(modifier = Modifier.height(100.dp))
        }

        // Custom Bottom Navigation Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                color = Color.White,
                shadowElevation = 16.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavItem(icon = Icons.Default.SwapHoriz, label = "调度", isSelected = false)
                    BottomNavItem(icon = Icons.Default.Place, label = "路线", isSelected = false)
                    Spacer(modifier = Modifier.weight(1f)) // Space for the floating button
                    BottomNavItem(icon = Icons.Default.Person, label = "我的", isSelected = false)
                }
            }
            
            // Floating "Vehicle" Button in the middle-right position
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                Spacer(modifier = Modifier.weight(2f)) // Space for the first two items
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(blueColor)
                            .zIndex(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalShipping,
                            contentDescription = "车辆",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "车辆", fontSize = 12.sp, color = blueColor)
                }
                Spacer(modifier = Modifier.weight(1f)) // Space for the last item
            }
        }
    }
}

@Composable
fun TagItem(text: String, bgColor: Color, textColor: Color, icon: ImageVector) {
    Row(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun StatusItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF222222)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF999999)
        )
    }
}

@Composable
fun MenuItem(icon: ImageVector, label: String, iconColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(72.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFF0F5FF), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 13.sp,
            color = Color(0xFF333333)
        )
    }
}

@Composable
fun RowScope.BottomNavItem(icon: ImageVector, label: String, isSelected: Boolean) {
    val color = if (isSelected) Color(0xFF2B68E3) else Color(0xFF999999)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.weight(1f)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = color
        )
    }
}
