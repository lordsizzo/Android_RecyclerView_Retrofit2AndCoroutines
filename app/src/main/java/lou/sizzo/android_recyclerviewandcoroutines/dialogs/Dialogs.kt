package lou.sizzo.android_recyclerviewandcoroutines.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import lou.sizzo.android_recyclerviewandcoroutines.databinding.SplashBinding

class Dialogs {
    fun progressDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        var bindingPDialog: SplashBinding = SplashBinding.inflate(
            LayoutInflater.from(context))
        dialog.setContentView(bindingPDialog.root)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        return dialog
    }
}