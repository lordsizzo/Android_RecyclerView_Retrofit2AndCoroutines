package lou.sizzo.android_recyclerviewandcoroutines

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import lou.sizzo.android_recyclerviewandcoroutines.adapters.Data_Adapter
import lou.sizzo.android_recyclerviewandcoroutines.databinding.ActivityMainBinding
import lou.sizzo.android_recyclerviewandcoroutines.dialogs.Dialogs
import lou.sizzo.android_recyclerviewandcoroutines.models.modelContracts
import lou.sizzo.android_recyclerviewandcoroutines.utils.layoutManagerCustom
import lou.sizzo.sizzgnature.api_data.Data_Catching
import java.lang.Exception
import java.util.ArrayList

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    lateinit var binding: ActivityMainBinding
    lateinit var pDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeRefresh.setOnRefreshListener {
            getDataAPI()
        }
        binding.recyclerView.layoutManagerCustom()

        getDataAPI()
    }

    fun getDataAPI(){

        var items: MutableList<modelContracts> = ArrayList<modelContracts>()
        pDialog = Dialogs().progressDialog(this)

        launch {
            try {
                //Inicia Loading de espera
                Handler(Looper.getMainLooper()).post {
                    pDialog.show()
                }

                //Inicia corrutina para capturar la info y se captura en el arrayList
                items = Data_Catching().awaitCatchDataRetrofit()
                println("Response data: $items")
                //¿Se muestra el dialog?
                pDialog.isShowing.let {
                    //Capturo el adapter
                    val adapter =  Data_Adapter(items, this@MainActivity)
                    adapter.apply {
                        //Aplico el adapter
                        binding.recyclerView.adapter = this
                        //Desaparece el dialog
                        pDialog.dismiss()
                        binding.swipeRefresh.isRefreshing = false
                    }
                }

            } catch (e: Exception) {
                println("Ha ocurrido un error: ${e}")
                Handler(Looper.getMainLooper()).post {
                    //¿Se muestra el dialog?
                    pDialog.isShowing.let {
                        //Capturo el adapter
                        val adapter =  Data_Adapter(items, this@MainActivity)
                        adapter.apply {

                            //Aplico el adapter
                            binding.recyclerView.adapter = this

                            //Desaparece el dialog
                            pDialog.dismiss()
                            binding.swipeRefresh.isRefreshing = false
                        }
                    }
                }
            }
        }
    }
}