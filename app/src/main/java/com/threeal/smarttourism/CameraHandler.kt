package com.threeal.smarttourism

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

class CameraHandler(
    private val previewView: PreviewView,
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) {

    companion object {
        const val CAMERA_REQUEST_CODE_PERMISSIONS = 10
        val CAMERA_REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    // Use the provided Context to get a CameraProvider
    private val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    // Check whether a single permission is already granted
    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_GRANTED
    }

    /**
     * Call this from your Activity (or Fragment) after
     * inflating its view and before you need the camera preview.
     */
    fun start() {
        // Request camera permission if needed
        if (!CAMERA_REQUIRED_PERMISSIONS.all { checkPermission(it) }) {
            // If context is an Activity, cast and request
            if (context is androidx.fragment.app.FragmentActivity) {
                ActivityCompat.requestPermissions(
                    context,
                    CAMERA_REQUIRED_PERMISSIONS,
                    CAMERA_REQUEST_CODE_PERMISSIONS
                )
            }
            return
        }

        // Once permissions are granted, bind the camera lifecycle
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Build a Preview use case
            val preview = Preview.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            // Connect the PreviewView's SurfaceProvider to the Preview use case
            preview.setSurfaceProvider(previewView.surfaceProvider)

            // Bind to lifecycle
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview
            )
        }, ContextCompat.getMainExecutor(context))
    }
}
