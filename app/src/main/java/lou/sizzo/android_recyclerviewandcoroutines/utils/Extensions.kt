package lou.sizzo.android_recyclerviewandcoroutines.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import lou.sizzo.android_recyclerviewandcoroutines.R


fun View.snackbar(message:String, duration: Int = Snackbar.LENGTH_LONG){
    Snackbar.make(this, message, duration).show()
}

fun Context.toast(message:String, duration: Int = Toast.LENGTH_LONG){
    Toast.makeText(this, message, duration).show()
}

fun View.setTap(listener: (View) -> Unit){
    this.setOnClickListener(){
        listener(it)
    }
}


fun RecyclerView.layoutManagerCustom(){
    this.setHasFixedSize(true)
    this.layoutManager =  LinearLayoutManager(this.context)
}


