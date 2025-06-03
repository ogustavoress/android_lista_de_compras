package ogustavoress.com.github.androidlistadecompras

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //Adapter da RecyclerView (declarado como lateinit para uso posterior)
    private lateinit var adapter: ItemAdapter

    //Lista mutável de Teste para preencher a RecyclerView
    private val listaDeCompras = mutableListOf(
        ItemCompra("Arroz", 2),
        ItemCompra("Feijão", 1),
        ItemCompra("Macarrão", 3),
        ItemCompra("Tomate", 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Referência para o RecyclerView do XML
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItens)

        //Define o layout da lista (vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Cria o Adapter com a LISTA DE COMPRAS e conecta o RecyclerView
        adapter = ItemAdapter(listaDeCompras)
        recyclerView.adapter = adapter

        //Referencia o botão do XML
        val botaoAdicionar = findViewById<Button>(R.id.botaoAdicionarItem)

        botaoAdicionar.setOnClickListener{
            mostrarDialogoAdicionarItem()
        }
    }
    //Função que mostra um AlertDialog com campos para inserir um novo item
    private fun mostrarDialogoAdicionarItem(){
        //Cria a janela de diálogo
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Novo item") //Define o o título do diálogo

        //Layout vertical que terá os campos de entrada
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16,16,16,16) //Espaçamento interno

        //Campo de entrada para o nome do item
        val inputNome = EditText(this)
        inputNome.hint = "Nome do item"
        layout.addView(inputNome) //Adiciona o campo ao layout

        //Campo de entrada para a quantidade
        val inputQuantidade = EditText(this)
        inputQuantidade.hint = "Quantidade"
        inputQuantidade.inputType = InputType.TYPE_CLASS_NUMBER //Força entrada numérica
        layout.addView(inputQuantidade)

        //Define o layout como conteúdo do diálogo
        builder.setView(layout)

        //Botão "Adicionar" -> adiciona o item à lista se o nome for válido
        builder.setPositiveButton("Adicionar") { _, _ ->
            val nome = inputNome.text.toString()
            val quantidade = inputQuantidade.text.toString().toIntOrNull() ?: 1

            //Só adiciona se o nome não estiver vazio
            if (nome.isNotBlank()) {
                val novoItem = ItemCompra(nome, quantidade)
                listaDeCompras.add(novoItem) //Adiciona à lista
                adapter.notifyItemInserted(listaDeCompras.size - 1) //Atualiza o RecyclerView
            } else {
                //Mostra uma mensagem se o nome estiver vazio
                Toast.makeText(this, "Nome inválido", Toast.LENGTH_SHORT).show()
            }
        }
        
        //Botão "Cancelar" -> fecha o di[alogo sem fazer nada
        builder.setNegativeButton("Cancelar", null)

        //Exibe o diálogo na tela
        builder.show()
    }
}