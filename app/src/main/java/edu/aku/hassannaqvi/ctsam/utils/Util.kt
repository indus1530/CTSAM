package edu.aku.hassannaqvi.ctsam.utils

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import edu.aku.hassannaqvi.ctsam.R
import edu.aku.hassannaqvi.ctsam.databinding.ItemDialogBinding
import edu.aku.hassannaqvi.ctsam.ui.other.EndingActivity
import java.util.*

private fun checkPermission(context: Context): IntArray {
    return intArrayOf(ContextCompat.checkSelfPermission(context,
            Manifest.permission.READ_CONTACTS), ContextCompat.checkSelfPermission(context,
            Manifest.permission.GET_ACCOUNTS), ContextCompat.checkSelfPermission(context,
            Manifest.permission.READ_PHONE_STATE), ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_FINE_LOCATION), ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_COARSE_LOCATION), ContextCompat.checkSelfPermission(context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE), ContextCompat.checkSelfPermission(context,
            Manifest.permission.CAMERA))
}

fun getPermissionsList(context: Context): List<String> {
    val permissions = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA)
    val listPermissionsNeeded: MutableList<String> = ArrayList()
    for (i in checkPermission(context).indices) {
        if (checkPermission(context)[i] != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(permissions[i])
        }
    }
    return listPermissionsNeeded
}

fun openEndActivity(activity: Activity) {
    val dialog = Dialog(activity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(R.layout.item_dialog_2)
    dialog.setCancelable(false)
    val params = WindowManager.LayoutParams()
    params.copyFrom(dialog.window!!.attributes)
    params.width = WindowManager.LayoutParams.WRAP_CONTENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.show()
    dialog.window!!.attributes = params
    dialog.findViewById<View>(R.id.btnOk).setOnClickListener { view: View? ->
        activity.finish()
        activity.startActivity(Intent(activity, EndingActivity::class.java).putExtra("complete", false)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
    dialog.findViewById<View>(R.id.btnNo).setOnClickListener { view: View? -> dialog.dismiss() }
}

@JvmOverloads
fun openWarningActivity(activity: Activity, message: String, defaultFlag: Boolean = true) {
    val dialog = Dialog(activity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val bi: ItemDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_dialog, null, false)
    dialog.setContentView(bi.root)
    bi.content.text = message
    dialog.setCancelable(false)
    val params = WindowManager.LayoutParams()
    params.copyFrom(dialog.window!!.attributes)
    params.width = WindowManager.LayoutParams.WRAP_CONTENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.window!!.attributes = params
    dialog.show()
    bi.btnOk.setOnClickListener {
        val endSecAActivity = activity as EndSectionActivity
        endSecAActivity.endSecActivity(defaultFlag)
    }
//    bi.btnNo.setOnClickListener {
//        dialog.dismiss()
//    }
}

@JvmOverloads
fun contextEndActivity(activity: Activity, defaultFlag: Boolean = true) {
    val dialog = Dialog(activity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(R.layout.item_dialog_2)
    dialog.setCancelable(false)
    val params = WindowManager.LayoutParams()
    params.copyFrom(dialog.window!!.attributes)
    params.width = WindowManager.LayoutParams.WRAP_CONTENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.show()
    dialog.window!!.attributes = params
    val endSecAActivity = activity as EndSectionActivity
    dialog.findViewById<View>(R.id.btnOk).setOnClickListener { endSecAActivity.endSecActivity(defaultFlag) }
    dialog.findViewById<View>(R.id.btnNo).setOnClickListener { dialog.dismiss() }
}

fun getMemberIcon(gender: Int, age: String): Int {
    val memAge = age.toInt()
    return if (memAge == -1) R.drawable.boy else if (memAge > 10) if (gender == 1) R.drawable.ctr_male else R.drawable.ctr_female else if (gender == 1) R.drawable.ctr_childboy else R.drawable.ctr_childgirl
}

fun getMemberIcon(gender: Int): Int {
    return if (gender == 1) R.drawable.ctr_childboy else R.drawable.ctr_childgirl
}

interface EndSectionActivity {
    fun endSecActivity(flag: Boolean)
}