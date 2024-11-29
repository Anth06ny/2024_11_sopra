package com.amonteiro.a2024_11_sopra.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.amonteiro.a2024_11_sopra.R
import com.amonteiro.a2024_11_sopra.ui.MyError
import com.amonteiro.a2024_11_sopra.ui.PictureRowItem
import com.amonteiro.a2024_11_sopra.ui.Routes
import com.amonteiro.a2024_11_sopra.ui.theme._2024_11_sopraTheme
import com.amonteiro.a2024_11_sopra.viewmodel.MainViewModel


@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, locale = "fr")
@Composable
fun SearchScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    _2024_11_sopraTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            val mainViewModel = MainViewModel()
            mainViewModel.loadFakeData(true, "UN message d'erreur")

            SearchScreen(modifier = Modifier.padding(innerPadding), mainViewModel = mainViewModel)
        }
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier, mainViewModel: MainViewModel = viewModel (),  navHostController: NavHostController? = null) {

    //collectAsStateWithLifecycle transforme en donnée observable pour compose
    val runInProgress by mainViewModel.runInProgress.collectAsStateWithLifecycle()
    val list by mainViewModel.dataList.collectAsStateWithLifecycle() //.filter { it.title.contains(searchText.value, true) }
    val errorMessage by mainViewModel.errorMessage.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        var searchText  =  rememberSaveable { mutableStateOf("")}

        SearchBar(searchText = searchText)



        AnimatedVisibility(runInProgress, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            CircularProgressIndicator()
        }

        MyError(errorMessage =  errorMessage)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)
        , modifier = modifier.weight(1f)
        ) {

            items(list.size) {
                PictureRowItem(data = list[it], onPictureClick = {
                    navHostController?.navigate(Routes.DetailScreen.withObject(list[it]))
                })
            }
        }

        Row (modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){

            Spacer(
                Modifier
                    .size(ButtonDefaults.IconSpacing)
                    .weight(1f))

            Button(
                onClick = { searchText.value = "" },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                modifier = Modifier.weight(3f)
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.clear_filter))
            }

            Spacer(Modifier.size(ButtonDefaults.IconSpacing))

            Button(
                onClick = { mainViewModel.loadWeathers(searchText.value) },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                modifier = Modifier.weight(3f)
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.bt_load))
            }

            Spacer(
                Modifier
                    .size(ButtonDefaults.IconSpacing)
                    .weight(1f))
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, searchText: MutableState<String>) {

    TextField(
        value = searchText.value, //Valeur affichée
        onValueChange = { searchText.value = it }, //Nouveau texte entrée
        leadingIcon = { //Image d'icone
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        singleLine = true,
        label = { Text("Enter text") }, //Texte d'aide qui se déplace

        //Comment le composant doit se placer
        modifier = modifier
            .fillMaxWidth() // Prend toute la largeur
            .heightIn(min = 56.dp) //Hauteur minimum
    )
}

//Non utilisé pour simplifier le code
// Utilisation :
//    var searchText by remember { mutableStateOf("") }
//    SearchBarOfficiel(text=searchText, onValueChange = {searchText = it})
@Composable
fun SearchBarOfficiel(modifier: Modifier = Modifier, text:String, onValueChange: (String) -> Unit) {
    TextField(
        value = text, //Valeur affichée
        onValueChange = onValueChange, //Nouveau texte entrée
        leadingIcon = { //Image d'icone
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        singleLine = true,
        label = { Text("Enter text") }, //Texte d'aide qui se déplace
        //Comment le composant doit se placer
        modifier = modifier
            .fillMaxWidth() // Prend toute la largeur
            .heightIn(min = 56.dp) //Hauteur minimum
    )
}

