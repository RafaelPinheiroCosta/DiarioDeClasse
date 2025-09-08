package com.rafaelcosta.diariodeclasse.data

import com.rafaelcosta.diariodeclasse.R

class DataSource() {
    fun  carregarAlunos(): List<Aluno> {
        return listOf(
            Aluno(
                nome = "Rafael Costa",
                foto = R.drawable.account_circle,
                curso = "Análise e Desenvolvimento de Sistemas"
            ),
            Aluno(
                nome = "Maria Silva",
                foto = R.drawable.account_circle,
                curso = "Engenharia de Software"
            ),
            Aluno(
                nome = "João Pereira",
                foto = R.drawable.account_circle,
                curso = "Ciência da Computação"
            ),
            Aluno(
                nome = "Ana Souza",
                foto = R.drawable.account_circle,
                curso = "Sistemas de Informação"
            ),
            Aluno(
                nome = "Carlos Oliveira",
                foto = R.drawable.account_circle,
                curso = "Redes de Computadores"
            ),
            Aluno(
                nome = "Mariana Lima",
                foto = R.drawable.account_circle,
                curso = "Segurança da Informação"
            ),
            Aluno(
                nome = "Pedro Fernandes",
                foto = R.drawable.account_circle,
                curso = "Banco de Dados"
            ),
            Aluno(
                nome = "Juliana Gomes",
                foto = R.drawable.account_circle,
                curso = "Desenvolvimento Mobile"
            ),
            Aluno(
                nome = "Lucas Almeida",
                foto = R.drawable.account_circle,
                curso = "Inteligência Artificial"
            ),
            Aluno(
                nome = "Beatriz Rodrigues",
                foto = R.drawable.account_circle,
                curso = "Computação em Nuvem"
            )
        )
    }
}