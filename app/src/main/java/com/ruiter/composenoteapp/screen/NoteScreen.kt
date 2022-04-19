package com.ruiter.composenoteapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruiter.composenoteapp.R
import com.ruiter.composenoteapp.components.NoteButton
import com.ruiter.composenoteapp.components.NoteInputText
import com.ruiter.composenoteapp.data.NoteDataSource
import com.ruiter.composenoteapp.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(notes: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(8.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Icon notifications"
            )
        }, backgroundColor = Color(0xFFDADFE3))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                })
            NoteInputText(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = description,
                label = "Add a description",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) description = it
                })
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    title = ""
                    description = ""
                }
            })
        }

        Divider(modifier = Modifier.padding(8.dp))

        LazyColumn {
            items(notes) {
                NoteRow(note = it, onNoteClicked = {})
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(topEnd = 32.dp, bottomStart = 32.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .clickable { }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.Start) {

            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.caption
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}