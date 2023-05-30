package com.example.jccollectwords

import android.os.Bundle
import android.provider.UserDictionary.Words
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jccollectwords.ui.theme.JCCollectWordsTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.awaitAll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JCCollectWordsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CollectWords(words = mutableListOf(""))
                }
            }
        }
    }
}

@Composable
fun WordList(words: List<String>) {
    LazyColumn {
        items(words) { word ->
            Text(text = word)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectWords(words: MutableList<String>) {

    var word by remember { mutableStateOf("") }

    var showWords by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(all = 8.dp)

    ) {
        TextField(
            value = word,
            onValueChange = { word = it },
            modifier = Modifier
                .focusable(true)
                .fillMaxWidth()
        )

        Button(onClick = { words.add(word) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "SAVE WORD")
        }

        Button(onClick = { words.clear() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "CLEAR WORDS")
        }

        Button(onClick = { showWords = true }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "SHOW WORDS")
        }

        if (showWords) {
            WordList(words = words)
        }
        if(words.size == 0){
            Text(text = "Nothing in the list so far")
        }
    }
}




@Preview(showBackground = true)
@Composable
fun CollectWordsPreview() {
    JCCollectWordsTheme {
        CollectWords(words = listOf("") as MutableList<String>)
    }
}