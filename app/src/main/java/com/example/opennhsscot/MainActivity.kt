package com.example.opennhsscot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opennhsscot.model.Record
import com.example.opennhsscot.repo.GpDataRepository
import com.example.opennhsscot.ui.theme.OpenNhsScotTheme
import com.example.opennhsscot.views.RecordCardView
import com.example.opennhsscot.vm.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val myVM : MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Collect flow
            val gpResults by myVM.uiState.collectAsState()
            // Get as Record List
            var gpRecordList : List<Record> = gpResults.result.records

            OpenNhsScotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    }
                Column {
                    Text(
                        text = "NHS Scotland GP Data Set ",
                        modifier = Modifier
                            .padding(16.dp),
                        fontSize = 20.sp)

                    var searchText by remember { mutableStateOf(TextFieldValue("")) }

                    TextField(
                        value = searchText,
                        onValueChange = { newText ->
                            searchText = newText
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        singleLine = true,
                        label = { Text("Search Practices of List Size over") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                        shape = RoundedCornerShape(10),
                        onClick = {
                            CoroutineScope(Dispatchers.Default).launch {
                               myVM.fetchGpDataSearch(searchText.text)
                            }

                        }
                    ) {
                        Text(
                            text = "Search Practices",
                            color = Color.White,
                            fontSize = 30.sp,
                            modifier = Modifier
                        )
                    }
                    LazyColumn {
                        items(gpRecordList.size) {
                            RecordCardView(gpRecordList[it])
                        }

                    }
                }
                }
            }
        }
}