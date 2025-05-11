package com.threeal.smarttourism

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.threeal.smarttourism.databinding.ActivityArBinding

class ArActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArBinding

    private lateinit var arOverlayView: ArOverlayView
    private lateinit var cameraHandler: CameraHandler
    private lateinit var locationHandler: LocationHandler
    private lateinit var rotationHandler: RotationHandler
    private lateinit var refreshHandler: RefreshHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inflate view binding
        binding = ActivityArBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Initialize your overlay and handlers
        arOverlayView     = ArOverlayView(this)
        locationHandler   = LocationHandler(this)
        rotationHandler   = RotationHandler(this)
        refreshHandler    = RefreshHandler(this)

        // 3. Set up CameraHandler with the PreviewView from binding
        cameraHandler = CameraHandler(
            previewView    = binding.cameraPreview,
            lifecycleOwner = this,
            context        = this
        )

        // 4. Wire up the back button
        binding.backButton.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        arOverlayView.start()
        locationHandler.start()
        rotationHandler.start()
        refreshHandler.start()

        // Only start camera if permission is already granted
        if (CameraHandler.CAMERA_REQUIRED_PERMISSIONS.all { checkPermission(it) }) {
            cameraHandler.start()
        }
    }


    override fun onPause() {
        super.onPause()
        arOverlayView.stop()
        locationHandler.stop()
        refreshHandler.stop()
    }

    // Helper to check a permission
    private fun checkPermission(type: String): Boolean =
        ActivityCompat.checkSelfPermission(this, type) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CameraHandler.CAMERA_REQUEST_CODE_PERMISSIONS -> {
                if (CameraHandler.CAMERA_REQUIRED_PERMISSIONS.all { checkPermission(it) }) {
                    cameraHandler.start()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.camera_permission_not_granted),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }

            LocationHandler.LOCATION_REQUEST_CODE_PERMISSIONS -> {
                if (LocationHandler.LOCATION_REQUIRED_PERMISSIONS.all { checkPermission(it) }) {
                    locationHandler.start()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.location_permission_not_granted),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }
}
