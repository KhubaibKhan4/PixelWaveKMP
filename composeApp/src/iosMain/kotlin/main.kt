import androidx.compose.ui.window.ComposeUIViewController
import org.pixel.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
