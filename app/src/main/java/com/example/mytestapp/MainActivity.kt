package com.example.mytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytestapp.ui.theme.MyTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BizCard()
                }
            }
        }
    }
}

@Composable
fun BizCard() {
    var buttonClickedState by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp),
            backgroundColor = Color.Cyan, elevation = 4.dp,
            shape = RoundedCornerShape(
                corner = CornerSize(10.dp),
            )
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateProfilePhoto()

                Divider(modifier = Modifier.padding(5.dp), color = Color.Black, thickness = 1.dp)

                AddProfileDetails("John Paul", "Android Developer", "johnpaul@gmail.com")

                Button(
                    onClick = { buttonClickedState = !buttonClickedState },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "portfolio")
                }
                if (buttonClickedState) Content() else Box {}
            }
        }
    }
}

@Composable
private fun CreateProfilePhoto(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(
            1.dp,
            Color.Black
        ), elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_person_24),
            contentDescription = "Profile Photo",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun AddProfileDetails(name: String, jobProfile: String, email: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = name, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(
            text = jobProfile,
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(text = email, fontSize = 17.sp)
    }
}

@Composable
private fun ShowProjects(projectName: String, projectDesc: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = projectName,
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(text = projectDesc, fontSize = 17.sp)
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Portfolio(data = listOf("project 1", "project 2", "project 3"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            PortfolioItem("Project", "ProjectDesc")
        }
    }
}

@Composable
fun PortfolioItem(projectName: String, projectDesc: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp), shape = RectangleShape
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.Cyan)
                .padding(16.dp)
        ) {
            CreateProfilePhoto(modifier = Modifier.size(50.dp))
            ShowProjects(projectName, projectDesc)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTestAppTheme {
        BizCard()
    }
}