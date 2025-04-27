package com.example.androidlabs

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

class Clickable : ClickableSpan() {
    override fun onClick(widget: View) {
        TODO("Not yet implemented")
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }
}