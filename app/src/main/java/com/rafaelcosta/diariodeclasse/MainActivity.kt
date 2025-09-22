package com.rafaelcosta.diariodeclasse

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        val TAG = "MainActivity"
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate Called")

        enableEdgeToEdge()
        setContent {
            DiarioDeClasseTheme {
                Scaffold(
                    topBar = {
                        DiarioDeClasseTopBar()
                    }
                ) { innerPadding ->
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

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause Called")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart Called")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop Called")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart Called")
    }
}

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
                    .calculateEndPadding(layoutDirection)
            )
    ) {
        ListaDeAlunos(
            modifier = Modifier,
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
        items(listaDeAlunos) { aluno ->
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
    var expandir by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        shape = RoundedCornerShape(
            bottomEnd = 0.dp,
            topStart = 0.dp,
            bottomStart = 20.dp,
            topEnd = 20.dp
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = fotoAluno),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .weight(1f)
                    .clip(CircleShape)
            )
            Column(
                modifier = modifier
                    .weight(2f)
            )
            {
                Text(
                    text = nomeAluno,
                    modifier.fillMaxWidth()
                )
                Text(
                    text = cursoAluno,
                    modifier.fillMaxWidth()
                )
            }

            DetalhesAlunoButton(
                { expandir = !expandir },
                modifier = modifier
                    .weight(0.5f)
                    .wrapContentSize(align = Alignment.CenterEnd)
            )

        }
        if (expandir) {
            DetalhesAluno()
        }
    }
}

@Composable
fun DetalhesAlunoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }

}

@Composable
fun DetalhesAluno() {
    Column {
        Text(
            text = "Nota: 100"
        )
        Text(
            text = "Faltas: 20%"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiarioDeClasseTopBar(modifier: Modifier = Modifier){

    CenterAlignedTopAppBar(
        title = {
            Text(
                "Diario de Classe"
            )
        },
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun DiarioDeClassePreview(){
    DiarioDeClasseTheme {
        Scaffold(
            modifier = Modifier,
            topBar = {
                DiarioDeClasseTopBar()
            }
        ) { innerPadding ->
            DiarioDeClasseApp(
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}



