package ogustavoress.com.github.androidlistadecompras

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //Lista de Teste para preencher a RecyclerView
    private val listaDeCompras = listOf(
        ItemCompra("Arroz", 2),
        ItemCompra("Feijão", 1),
        ItemCompra("Macarrão", 3),
        ItemCompra("Tomate", 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1. Referência para o RecyclerView do XML
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItens)

        //2. Define o layout da lista (vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //3. Conecta o RecyclerView com o adapter, passando a lista de itens
        recyclerView.adapter = ItemAdapter(listaDeCompras)

    }
}