package lou.sizzo.android_recyclerviewandcoroutines.adapters

import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import lou.sizzo.android_recyclerviewandcoroutines.R
import lou.sizzo.android_recyclerviewandcoroutines.databinding.ContractAdapterBinding
import lou.sizzo.android_recyclerviewandcoroutines.models.modelContracts
import lou.sizzo.android_recyclerviewandcoroutines.utils.setTap
import lou.sizzo.android_recyclerviewandcoroutines.utils.snackbar
import lou.sizzo.android_recyclerviewandcoroutines.utils.toast
import java.lang.Exception

class Data_Adapter(val items: List<modelContracts>, val context: Context) :
    RecyclerView.Adapter<Data_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contract_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let { holder.bindItems(it, context) }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ContractAdapterBinding.bind(itemView)

        fun bindItems(dato: modelContracts, context: Context) {
            try {
                binding.type.text = dato.title
                binding.id.text = dato.id
                if (dato.completed.toBoolean()){
                    binding.btnActivo.visibility = View.VISIBLE
                    binding.btnInactivo.visibility = View.GONE
                }else{
                    binding.btnActivo.visibility = View.GONE
                    binding.btnInactivo.visibility = View.VISIBLE
                }
                binding.btnActivo.setTap {
                    it.snackbar("Presionaste activo", Snackbar.LENGTH_SHORT)
                }
                binding.btnInactivo.setTap {
                    it.snackbar("Presionaste inactivo", Snackbar.LENGTH_SHORT)
                }
                binding.cardViewContent.setTap {
                    context.toast(dato.title, Toast.LENGTH_SHORT)
                }
            } catch (e: Exception) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}