package com.example.reader_app_ver2.screens.home

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.net.wifi.hotspot2.pps.HomeSp
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reader_app_ver2.component.Center
import com.example.reader_app_ver2.model.Book
import com.example.reader_app_ver2.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    Scaffold(topBar = {
        ReaderAppBar(title = "ReaderApp", navController = navController)
    }, floatingActionButton = { FABContent {} }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            //HomeというScreenの中にScaffoldがあってそのメインとなるのがHomeContentここにいろんな要素を入れる
            HomeContent(navController = navController)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {
    TopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showProfile) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Logo Icon",
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(12.dp)
                        )
                        .scale(0.6f)
                )
                Text(
                    text = title,
                    color = Color.Red.copy(alpha = 0.7f),

                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                )
            }
        }
    }, actions = {
        IconButton(onClick = {
            FirebaseAuth.getInstance().signOut().run {
                navController.navigate(ReaderScreens.LoginScreen.name)
            }
        }) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "log out")
        }
    }, colors = TopAppBarDefaults.smallTopAppBarColors())
}

@Composable
fun FABContent(onTap: (String) -> Unit) {
    FloatingActionButton(
        onClick = { onTap("") },
        shape = RoundedCornerShape(50.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "")
    }
}

//ここのHomeContentはScaffoldのメインの要素、このHomeContentの中にまたTitleSectionやら何やらある
@Composable
fun HomeContent(navController: NavController) {

    val currentUser = if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
    else "N/A"


    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n " + " activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(fraction = 0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { navController.navigate(ReaderScreens.ReaderStatsScreen.name) }
                        .size(45.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = currentUser!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1, overflow = TextOverflow.Clip
                )
                Divider()
            }

        }
    }
}

@Preview
@Composable
fun ListCard(
    book: Book = Book("123", "走れメロス", "None", ""),
    onPressDetail: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val resources = context.resources

    //ここで現在のディスプレイのサイズなどを取得することができる
    val displayMetrics = resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    Card(
        shape = RoundedCornerShape(29.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            //invoke operatorは明示的な呼び出し、必須ではない
            .clickable { onPressDetail.invoke(book.title.toString()) }
    ) {

        Column(modifier = Modifier.width(screenWidth.dp)) {
            
        }

    }
}

@Composable
fun ReadingNowArea(books: List<Book>, navController: NavController) {
    //NextTask
}

@Composable
fun TitleSection(modifier: Modifier = Modifier, label: String) {
    Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {
        Column {
            Text(
                text = label,
                fontSize = 19.sp,
                textAlign = TextAlign.Left,
                fontStyle = FontStyle.Normal
            )

        }
    }
}
