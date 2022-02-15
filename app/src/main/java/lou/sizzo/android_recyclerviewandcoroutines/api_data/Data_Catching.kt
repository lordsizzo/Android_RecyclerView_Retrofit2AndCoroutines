package lou.sizzo.sizzgnature.api_data

import kotlinx.coroutines.*
import lou.sizzo.android_recyclerviewandcoroutines.api_data.server.Server_Connect
import lou.sizzo.android_recyclerviewandcoroutines.models.modelContracts
import java.util.ArrayList

class Data_Catching: Server_Connect() {

    suspend fun awaitCatchDataRetrofit(): MutableList<modelContracts>{
        var items: MutableList<modelContracts> = ArrayList<modelContracts>()
        val catchinData = GlobalScope.async {
            catchDataRetrofit()
        }
        items = catchinData.await()
        return items
    }
}