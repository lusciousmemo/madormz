package kenji.pilotprojects.jetpackcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kenji.pilotprojects.jetpackcodelab.ui.theme.MaDormzTheme

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaDormzTheme {
                PhotographerCard(modifier = Modifier)
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable { /***/ }
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)

        ) {
            Text(
                text = "Keng Ruksasin",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "3 minutes ago",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.alpha(0.8f)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LayoutsCodeLab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Keng App Bar")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.AccountBox, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier) {
    Column(modifier.padding(8.dp)) {
        Text(text = "Hi")
        Text(text = "there there there there there there")
    }
}

@Composable
fun PhotographerCardPreview() {
    MaDormzTheme {
        PhotographerCard(modifier = Modifier)
    }
}

@Composable
fun ButtonSlotAPI(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    /* TODO: TopAppBar by SLOT API */
}


@Composable
fun TopAppBarPreview() {
    /* TODO: TopAppBar by SLOT API */
}


@Composable
fun BottomAppBarPreview() {
    /* TODO: TopAppBar by SLOT API */
}


@Composable
fun FabPreview() {
    /* TODO: TopAppBar by SLOT API */
}

@Composable
fun DrawerPreview() {
    /* TODO: TopAppBar by SLOT API */
}










