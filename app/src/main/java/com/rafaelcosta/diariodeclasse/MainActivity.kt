package com.rafaelcosta.diariodeclasse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rafaelcosta.diariodeclasse.data.Aluno
import com.rafaelcosta.diariodeclasse.data.DataSource
import com.rafaelcosta.diariodeclasse.ui.theme.DiarioDeClasseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiarioDeClasseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiarioDeClasseApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .statusBarsPadding()
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DiarioDeClasseApp(
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        ListaDeAlunos(
            modifier = modifier,
            listaDeAlunos = DataSource().carregarAlunos()
        )
    }
}

@Composable
fun ListaDeAlunos(
    modifier: Modifier = Modifier,
    listaDeAlunos: List<Aluno>
) {
    LazyColumn(modifier = modifier) {
        items (listaDeAlunos) { aluno ->
            CardAluno(
                modifier = modifier,
                fotoAluno = aluno.foto,
                nomeAluno = aluno.nome,
                cursoAluno = aluno.curso
            )
        }
    }
}

@Composable
fun CardAluno(
    modifier: Modifier = Modifier,
    @DrawableRes fotoAluno: Int,
    nomeAluno: String,
    cursoAluno: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier=modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )   {
            Image(
                painter = painterResource(id = fotoAluno),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(text = nomeAluno)
                Text(text = cursoAluno)
            }
        }
    }
}

