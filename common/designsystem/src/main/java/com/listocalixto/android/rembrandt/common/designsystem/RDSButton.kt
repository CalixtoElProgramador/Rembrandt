package com.listocalixto.android.rembrandt.common.designsystem

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RBDSButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun RDSButtonPreview() {
    MaterialTheme {
        RBDSButton(
            modifier = Modifier,
            text = "Hello world!",
            onClick = {},
        )
    }
}
