package abdulrahman.ali19.intrazero

import abdulrahman.ali19.intrazero.ui.theme.IntrazeroTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntrazeroTheme {
                DefaultPreview()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IntrazeroTheme {

    }
}