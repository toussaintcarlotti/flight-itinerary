import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberFlightTakeoff(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "flight_takeoff",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(6.542f, 34.792f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadTo(5.25f, 33.5f)
                quadToRelative(0f, -0.583f, 0.375f, -0.958f)
                reflectiveQuadToRelative(0.917f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.938f, 0.375f)
                quadToRelative(0.395f, 0.375f, 0.395f, 0.958f)
                quadToRelative(0f, 0.542f, -0.395f, 0.917f)
                quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
                close()
                moveToRelative(2.375f, -8.75f)
                quadToRelative(-0.417f, 0.125f, -0.834f, -0.042f)
                quadToRelative(-0.416f, -0.167f, -0.625f, -0.542f)
                lineToRelative(-3.125f, -5.291f)
                quadToRelative(-0.333f, -0.5f, -0.125f, -0.959f)
                quadToRelative(0.209f, -0.458f, 0.834f, -0.541f)
                quadToRelative(0.208f, -0.042f, 0.458f, 0.041f)
                quadToRelative(0.25f, 0.084f, 0.417f, 0.209f)
                lineToRelative(2.5f, 2.208f)
                lineToRelative(8.958f, -2.417f)
                lineToRelative(-6f, -10.083f)
                quadToRelative(-0.375f, -0.667f, -0.146f, -1.271f)
                quadToRelative(0.229f, -0.604f, 0.979f, -0.854f)
                quadToRelative(0.334f, -0.083f, 0.75f, 0f)
                quadToRelative(0.417f, 0.083f, 0.709f, 0.333f)
                lineToRelative(10.958f, 9.875f)
                lineToRelative(8.708f, -2.333f)
                quadToRelative(1.167f, -0.333f, 2.125f, 0.417f)
                quadToRelative(0.959f, 0.75f, 0.959f, 2f)
                quadToRelative(0f, 0.833f, -0.5f, 1.458f)
                reflectiveQuadToRelative(-1.25f, 0.833f)
                close()
            }
        }.build()
    }
}