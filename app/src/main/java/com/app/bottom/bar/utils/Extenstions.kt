package com.app.bottom.bar.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

private const val CALL_STACK_INDEX = 1

fun Any.log(message: Any?) {
    val stackTrace = Throwable().stackTrace
    val tag = if (stackTrace.size <= CALL_STACK_INDEX) {
        this.javaClass.simpleName
    } else {
        val lineNumber = stackTrace[CALL_STACK_INDEX].lineNumber
        "${this.javaClass.simpleName}:$lineNumber"
    }
    Log.d(tag, message.toString())
}

fun log(message: Any?, tag: String) {
    Log.d(tag, message.toString())
}

fun Fragment.hideKeyboard() {
    val view = activity?.findViewById<View>(android.R.id.content)
    if (view != null) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun View.visible() = run { visibility = View.VISIBLE }

fun View.gone() = run { visibility = View.GONE }

fun View.invisible() = run { visibility = View.INVISIBLE }

infix fun View.visibleIf(condition: Boolean) =
    run { visibility = if (condition) View.VISIBLE else View.GONE }

infix fun View.goneIf(condition: Boolean) =
    run { visibility = if (condition) View.GONE else View.VISIBLE }

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun Context.toast(@StringRes message: Int, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, time).show()
}

fun Context.toast(message: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, time).show()
}

fun Fragment.toast(@StringRes message: Int, time: Int = Toast.LENGTH_SHORT) {
    context?.toast(message, time)
}

fun Activity.simpleSnakeBar(@StringRes message: Int, time: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(window.decorView.rootView, message, time).show()
}

fun Activity.simpleSnakeBar(message: String, time: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(window.decorView.rootView, message, time).show()
}

fun Fragment.toast(message: String, time: Int = Toast.LENGTH_SHORT) {
    context?.toast(message, time)
}

val Long.formatAmount: String
    get() = String.format("%,d", this)

val Int.formatAmount: String
    get() = String.format("%,d", this)
