package ogustavoress.com.github.androidlistadecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Adapter que conecta a lista de dados(ItemCompra) com a RecyclerView
class ItemAdapter (
    //Lista de itens a ser exibida na tela
    private val listaItens: List<ItemCompra>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    //ViewHolder representa 1 item visual da lista
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //TextView do layout
        val textoItem: TextView = itemView.findViewById(android.R.id.text1)
    }

    //Cria a viewHolder: cria visualmente um item da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //Layout padrão do Android só com um TextView
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent,false)
        return ItemViewHolder(view)
    }

    //Preenche os dados em cada item da lista
    //(chamado toda vez que um item aparecer)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //Pegamos o item correspondente na lista
        val item = listaItens[position]
        //Mostramos o nome e a quantidade no TextView
        val context = holder.itemView.context
        holder.textoItem.text = context.getString(R.string.item_format, item.nome, item.quantidade)
    }

    //Retorna a quantidade de itens na lista (RecyclerView)
    override fun getItemCount(): Int {
        return listaItens.size
    }
}
