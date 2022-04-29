package com.example.activitytest

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast

fun isDarkTheme(context: Context): Boolean{
    val flag = context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK
    return flag == Configuration.UI_MODE_NIGHT_YES
}
//fun Toast():Toast(){
//
//}