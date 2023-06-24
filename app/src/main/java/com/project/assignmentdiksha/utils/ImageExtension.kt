package com.project.assignmentdiksha.utils

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream

object ImageExtension {
    fun checkPermissionGivenOrNot(context: Context, permissionType: String) =
        ContextCompat.checkSelfPermission(
            context,
            permissionType
        ) == PackageManager.PERMISSION_GRANTED

    fun getImageUri(
        src: Bitmap,
        format: Bitmap.CompressFormat,
        quality: Int,
        contentResolver: ContentResolver
    ): Uri? {
        val os = ByteArrayOutputStream()
        src.compress(format, quality, os)
        val path = MediaStore.Images.Media.insertImage(contentResolver, src, "title", null)
        return Uri.parse(path)
    }
}