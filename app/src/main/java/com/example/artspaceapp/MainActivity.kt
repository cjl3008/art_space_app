package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme
import com.example.artspaceapp.ui.theme.andika
import com.example.artspaceapp.ui.theme.kavoon
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(){
    var content by remember { mutableStateOf(Content()) }

    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            modifier = Modifier.weight(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier
                .weight(0.7f)
                .wrapContentSize(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .shadow(8.dp)
                        .background(Color.White)
                        .padding(28.dp)
                ) {
                    Image(
                        painterResource(id = content.image),
                        contentDescription = null
                    )
                }
            }
            Column(modifier = Modifier
                .weight(0.3f)
                .width(IntrinsicSize.Max)
                .wrapContentSize(Alignment.Center)
                .background(Color(0xFFC9C9C9))
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(id = content.description),
                    fontSize = 20.sp,
                    fontFamily = andika
                )
                Row {
                    Text(
                        text = stringResource(content.author),
                        fontFamily = kavoon
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(text = stringResource(id = content.year))
                }
            }
        }
        Row(modifier = Modifier
            .weight(0.1f)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            PrevOrNextButton(
                btnText = R.string.button_prev,
                onClick = {content = updateContent(-1, content)}
            )
            PrevOrNextButton(
                btnText = R.string.button_next,
                onClick = {content = updateContent(1, content)}
            )
        }
    }
}

@Composable
fun PrevOrNextButton(@StringRes btnText: Int, onClick: ()-> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(110.dp)
    ) {
        Text(stringResource(btnText))
    }
}

val updateContent: (Int, Content) -> Content = {
        counter: Int, content: Content ->
    when(val newCounter = content.counter + counter) {
        1 -> Content(R.drawable.p1, R.string.title1, R.string.artist1, R.string.year1, newCounter)
        2 -> Content(R.drawable.p2, R.string.title2, R.string.artist2, R.string.year2, newCounter)
        3 -> Content(R.drawable.p3, R.string.title3, R.string.artist3, R.string.year3, newCounter)
        4 -> Content(R.drawable.p4, R.string.title4, R.string.artist4, R.string.year4, newCounter)
        5, 0 -> Content(R.drawable.p5, R.string.title5, R.string.artist5, R.string.year5, 5)
        else -> Content(R.drawable.p1, R.string.title1, R.string.artist1, R.string.year1, 1)
    }
}

data class Content(
    @DrawableRes val image: Int = R.drawable.p1,
    @StringRes val description: Int = R.string.title1,
    @StringRes val author: Int = R.string.artist1,
    @StringRes val year: Int = R.string.year1,
    val counter: Int = 1
)

@Preview(
    showBackground = true,
    showSystemUi = true,
    //device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceAppTheme {
        ArtSpaceLayout()
    }
}