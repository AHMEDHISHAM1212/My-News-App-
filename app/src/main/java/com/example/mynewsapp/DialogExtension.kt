package com.example.mynewsapp

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

fun Fragment.showDialog(
    message: String,
    posActionName: String?= null,
    posAction: DialogInterface.OnClickListener? =null,
    negActionName: String?= null,
    negAction: DialogInterface.OnClickListener? =null
): AlertDialog {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage(message)

    if (posActionName!= null){
        dialogBuilder.setPositiveButton(posActionName,posAction)
    }
    if (negActionName!=null){
        dialogBuilder.setNegativeButton(negActionName,negAction)
    }
    return dialogBuilder.show()
}

//fun Fragment.showDialog(
//    message: String,
//    posActionName: String? = null,
//    posAction: DialogInterface.OnClickListener? = null,
//    negActionName: String? = null,
//    negAction: DialogInterface.OnClickListener? = null,
//
//    ) {
//    val builder = AlertDialog.Builder(context)
//    builder.setMessage(message)
//
//    if (posActionName != null) {
//        builder.setPositiveButton(posActionName, posAction)
//    }
//    if (negActionName != null) {
//        builder.setNegativeButton(negActionName, negAction)
//    }
//
//    val dialog = builder.create()
//    dialog.show()
//
//}
fun Activity.showDialog(
    message: String,
    posActionName: String?= null,
    posAction: DialogInterface.OnClickListener? =null,
    negActionName: String?= null,
    negAction: DialogInterface.OnClickListener? =null
):AlertDialog {
    val dialogBuilder = AlertDialog.Builder(this)
    dialogBuilder.setMessage(message)

    if (posActionName!= null){
        dialogBuilder.setPositiveButton(posActionName,posAction)
    }
    if (negActionName!=null){
        dialogBuilder.setNegativeButton(negActionName,negAction)
    }
    return dialogBuilder.show()
}