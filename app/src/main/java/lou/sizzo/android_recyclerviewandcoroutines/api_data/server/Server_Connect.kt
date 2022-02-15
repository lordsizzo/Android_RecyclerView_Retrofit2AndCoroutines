package lou.sizzo.android_recyclerviewandcoroutines.api_data.server

import lou.sizzo.android_recyclerviewandcoroutines.models.modelContracts
import lou.sizzo.android_recyclerviewandcoroutines.utils.ApiService
import lou.sizzo.android_recyclerviewandcoroutines.utils.ConfigUrl
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

abstract class Server_Connect {
    fun catchDataRetrofit(): MutableList<modelContracts> {
        var items: MutableList<modelContracts> = ArrayList<modelContracts>()
        val api = ApiService.createRetrofit()
        val response = api.getVerbList(ConfigUrl.lista_todos)
        val dataString: String? = response.execute().body()?.string()
        if (dataString != "[]") {
            val data = JSONArray(dataString)
            for (i in 0 until data.length()) {
                val c: JSONObject = data.getJSONObject(i)
                val e = modelContracts(
                    c.getString("title"),
                    c.getString("completed"),
                    c.getString("id")
                )
                items.add(e)
            }
        }
        return items
    }
}