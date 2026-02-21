package com.godlike.taskit.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.godlike.taskit.ui.theme.InterFontFamily
import com.godlike.taskit.ui.theme.darkGray
import com.godlike.taskit.ui.theme.textFieldBackground

@Composable
fun AuthTextField(
    title: String,
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    columnModifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = columnModifier
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = darkGray,
            fontFamily = InterFontFamily,
            modifier = Modifier.padding(start = 14.dp, bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            shape = RoundedCornerShape(32.dp),
            modifier = modifier
                .fillMaxWidth()
                .then(modifier),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = textFieldBackground,
                focusedContainerColor = textFieldBackground,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
        )
    }
}