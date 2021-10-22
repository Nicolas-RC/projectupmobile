package co.edu.sena.projectupmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomerAdapterCard: RecyclerView.Adapter<CustomerAdapterCard.ViewHolder>(){

    val titulos = arrayOf(
        "Informe Levantamiento Información",
        "Diagramas I",
        "Sesion de Revision",
    )

    val finicio = arrayOf(
        "2021/05/19",
        "2021/04/20",
        "2021/10/27",
    )

    val fcierre = arrayOf(
        "2021/05/25",
        "2021/04/23",
        "2021/11/03",
    )


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }



    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
    viewHolder.TituloEvent.text = titulos[i]
    viewHolder.FechaInicio.text = finicio[i]
    viewHolder.FechaCierre.text = fcierre[i]
    viewHolder.Imagen.setImageResource(R.drawable.img_card_evento)
    }

    override fun getItemCount(): Int {
        return titulos.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var Imagen: ImageView
        var TituloEvent: TextView
        var FechaInicio: TextView
        var FechaCierre: TextView

        init {
            Imagen = itemView.findViewById(R.id.imagenEvento)
            TituloEvent = itemView.findViewById(R.id.TituloEvento)
            FechaInicio = itemView.findViewById(R.id.FechaInicio)
            FechaCierre = itemView.findViewById(R.id.FechaCierre)
        }


    }
}