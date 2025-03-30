package org.akrck02.countless.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.akrck02.countless.ui.theme.MIN_ROUNDED_SHAPE

@Composable
fun MaterialTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    onClick: () -> Unit = {},
    onFocusChanged: (Boolean) -> Unit = {},
    label: String = "Label",
    width: Dp = 300.dp,
    type: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
        .padding(10.dp)
        .height(60.dp)
        .width(width),
    enabled: Boolean = true
) {

    val colors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        focusedTextColor = MaterialTheme.colorScheme.primary,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent
    )

    val source = remember { MutableInteractionSource() }
    TextField(
        isError = isError,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, modifier = Modifier.padding(top = 3.dp)) },
        trailingIcon = { icon?.also { Icon(imageVector = it, contentDescription = label) } },
        colors = colors,
        shape = MIN_ROUNDED_SHAPE,
        interactionSource = source,
        keyboardOptions = KeyboardOptions(keyboardType = type),
        modifier = modifier
            .fillMaxHeight()
            .onFocusChanged {
                onFocusChanged(it.isFocused)
            },
        enabled = enabled,
    )

    if (source.collectIsPressedAsState().value)
        onClick()

}
