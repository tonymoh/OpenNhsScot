package com.example.opennhsscot.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.opennhsscot.model.Record

@Composable
fun RecordCardView(item: Record
) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        // Display basic details from dataset.
        Text(text = "Practice Code = ${item.PracticeCode}", modifier = Modifier.padding(8.dp))
        Text(text = "Practice Name  = ${item.GPPracticeName}", modifier = Modifier.padding(8.dp))
        Text(text = "List Size = ${item.PracticeListSize}", modifier = Modifier.padding(8.dp))
        Text(text = "Postcode = ${item.Postcode}", modifier = Modifier.padding(8.dp))
    }

}