package com.godlike.taskit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.grayBackground
import com.godlike.taskit.ui.theme.lightGray

@Composable
fun SettingsTextField(
    onClick: () -> Unit,
    imageVector1: ImageVector? = null,
    text: String? = null,
    textResult: String? = null,
    imageVector2: ImageVector? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = grayBackground,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.Start)
        ) {
            if (imageVector1 != null)
                Icon(
                    imageVector = imageVector1,
                    contentDescription = "Icon",
                    tint = lightGray
                )
            if (text != null)
                Text(
                    text = text,
                    color = lightGray,
                    fontSize = 14.sp,
                    fontFamily = InterFontFamily
                )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.End),
        ) {
            if (textResult != null)
                Text(
                    text = textResult,
                    color = lightGray,
                    fontSize = 14.sp,
                    fontFamily = InterFontFamily
                )
            if (imageVector2 != null)
                Icon(
                    imageVector = imageVector2,
                    contentDescription = "Edit button",
                    tint = lightGray,
                    modifier = Modifier.clickable { onClick() }
                )
        }

    }
}

@Preview
@Composable
fun PreviewSettingsTextField() {
    SettingsTextField(onClick = {})
}