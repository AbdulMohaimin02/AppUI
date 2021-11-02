package com.example.meditationui.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationui.R
import com.example.meditationui.standardQuadFromTo
import com.example.meditationui.ui.theme.MeditationUITheme
import com.plcoding.meditationuiyoutube.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingsSection()
            ChipSection(chips = listOf("Sweet sleep","Insomnia","Depression", "Anxiety","Loss","Gain"))
            DailyThoughts()
            FeaturedSection(feature = listOf(
                Feature(
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    BlueViolet1,
                    BlueViolet2,
                    BlueViolet3
                ),
                Feature(
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                Feature(
                    title = "Night island",
                    R.drawable.ic_headphone,
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3
                ),
                Feature(
                    title = "Calming sounds",
                    R.drawable.ic_headphone,
                    Beige1,
                    Beige2,
                    Beige3
                )
            ))



        }

        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditate", R.drawable.ic_bubble),
            BottomMenuContent("Sleep", R.drawable.ic_bubble),
            BottomMenuContent("Music", R.drawable.ic_headphone),
            BottomMenuContent("Profile", R.drawable.ic_profile),
        ),modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun GreetingsSection(
    name: String = "Abdul"
) {
    Row (
        // The space between modifier will push the composable inside the row to as far apart as possible, therefore the text will be all the way on the left and the search Icon
        // will be all the way to the right
       horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text ="Good Morning, $name",
                style = MaterialTheme.typography.h2
            )

            Text(
                text ="We hope you are having a great day!!",
                style = MaterialTheme.typography.body1

            )

        }

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            tint =  Color.White,
            modifier = Modifier.size(24.dp)
        )



    }

}

@Composable
fun ChipSection(
    chips : List<String>
){
    
    // We need to manually duplicate the remember import and then import set value and get value
    // This variable is used to remember the index which chip we have clicked 
  var selectedChipIndex by remember {
      mutableStateOf(0)
  } 
    
    LazyRow{
        // The items block defines how many items we will have and which kind of items we are trying to display
        // The items parameter defines how many items we want to display, in this case we want to display all the items that are 
        // passed in 
       items(chips.size) {
           // Here we define the contents of the lazy column 
           
           Box(
               contentAlignment = Alignment.Center,
               modifier = Modifier
                   .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                   .clickable {
                       selectedChipIndex = it
                   }
                   .clip(RoundedCornerShape(10.dp))
                   .background(
                       if (selectedChipIndex == it) {
                           ButtonBlue
                       } else {
                           DarkerButtonBlue
                       }
                   )
                   .padding(15.dp)
           ){
               Text(text = chips[it], color = TextWhite)
           }
           
       }
    }
}



@Composable
fun DailyThoughts(){
    // You don't need a box or a surface to design if you have a column or a row.
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = LightRed)
            .padding(horizontal = 10.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Daily Thoughts",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "Meditation : 3-10 mins",
                style = MaterialTheme.typography.body1,
                color = TextWhite,
                modifier = Modifier.padding(start = 10.dp,end = 10.dp,bottom = 10.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = ButtonBlue)
                .padding(10.dp),

        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play button",
                tint = Color.White,
                modifier = Modifier.size(16.dp)

            )
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun FeaturedSection(feature: List<Feature>){
    Column(
        modifier = Modifier.fillMaxWidth(),
        
    ) {
        Text(
            text = "Featured",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        
        LazyVerticalGrid(
            cells = GridCells.Fixed(2) , 
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp,bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ){
            items(feature.size){
                FeatureItem(feature = feature[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    
    // Using this method to draw this squiggles it is harder to implement, but it is then easier to animate
    // However if you do just vector graphic it is easier to implement, but harder to animate 
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        
        // Coordinates for plotting out the path of the medium color line

        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())



        // Now we need to create a path object to actually connect up the lines
        // Just copy this, don't worry about it lol
        val mediumColoredPath = Path().apply { 
            // Starting point of the curve 
            moveTo(mediumColoredPoint1.x,mediumColoredPoint1.y)
            
            // Function to now connect the points 
            standardQuadFromTo(mediumColoredPoint1,mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2,mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3,mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4,mediumColoredPoint5)
            
            // The above code draws a smooth line between the defined coordinates
            // Now we need to join up the start and end point of the curve with some straight 
            // lines that are outside the box so that we get a shape that we can fill in
            
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()

        }

        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            // Starting point of the curve 
            moveTo(mediumColoredPoint1.x,mediumColoredPoint1.y)

            // Function to now connect the points 
            standardQuadFromTo(lightPoint1,lightPoint2)
            standardQuadFromTo(lightPoint2,lightPoint3)
            standardQuadFromTo(lightPoint3,lightPoint4)
            standardQuadFromTo(lightPoint4,lightPoint5)

            // The above code draws a smooth line between the defined coordinates
            // Now we need to join up the start and end point of the curve with some straight 
            // lines that are outside the box so that we get a shape that we can fill in

            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()

        }
        // Now that we have defined where the path is and how we want to connect the points
        // we now need to draw the,
        
        // We use the canvas composable to draw things
        Canvas(modifier = Modifier.fillMaxSize()
        ){
            drawPath(
                path = mediumColoredPath,
                color =  feature.mediumColor
            )

            drawPath(
                path = lightColoredPath,
                color =  feature.lightColor
            )
        }
        
        // Above is all the code that we need to draw the path

        // This is the Box that will contain the rest of the contents of the lazy grid cell

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)

        ){
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                // The placement of the text composable inside the box composable is defined inside the text composable
                modifier = Modifier.align(Alignment.TopStart)
            )

            Icon(
                painter = painterResource(id =  feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            // Since our button doesn't have any functionality, rather than make a button we will just make a clickable text
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}


@Composable
fun BottomMenu(
    items:List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor:Color = ButtonBlue,
    initialSelectedItemIndex: Int = 0

){
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(
        // Evenly distributes each item
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(color = DeepBlue)
            .padding(15.dp)
    ){
        items. forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                
            ) {
                selectedItemIndex = index
            }

        }
    }

}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean= false,
    activeHighlightColor:Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit

){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { 
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = if (isSelected) {
                        activeHighlightColor
                    } else {
                        Color.Transparent
                    }

                )
                .padding(10.dp),
            
            
            ){
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if(isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        
        Text(
            text = item.title,
            style = MaterialTheme.typography.body1
        )
    }


}



@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationUITheme {
        HomeScreen()
    }
}