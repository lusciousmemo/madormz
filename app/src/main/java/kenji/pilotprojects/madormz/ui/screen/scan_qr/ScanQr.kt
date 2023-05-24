package kenji.pilotprojects.madormz.ui.screen.scan_qr

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.Image
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
@ExperimentalGetImage
fun ScanQr(
    navController: NavController, scanQrViewModel: ScanQrViewModel
) {

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        scanQrViewModel.isCameraPermissionGranted.value = isGranted
    }

    val context = LocalContext.current
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember {
        PreviewView(context)
    }
    val analysisUseCase: ImageAnalysis = ImageAnalysis.Builder().build()
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing).build()
    val cameraExecutor = Executors.newSingleThreadExecutor()
    val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE
        ).build()
    val scanner = BarcodeScanning.getClient(options)

    fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                scanQrViewModel.isCameraPermissionGranted.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.CAMERA
            ) -> scanQrViewModel.isCameraPermissionGranted.value = true

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    fun processImageProxy(
        barcodeScanner: BarcodeScanner,
        imageProxy: ImageProxy,
        cameraProvider: ProcessCameraProvider
    ) {
        imageProxy.image?.let { image: Image ->
            val inputImage =
                InputImage.fromMediaImage(
                    image,
                    imageProxy.imageInfo.rotationDegrees
                )

            barcodeScanner.process(inputImage)
                .addOnSuccessListener { barcodeList ->
                    val barcode = barcodeList.getOrNull(0)

                    //'rawValue' is the decoded value of the barcode
                    barcode?.rawValue?.let { value ->
                        cameraProvider.unbindAll()
                        Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
//                        navController.navigate(MainScreen().)
                    }
                }
                .addOnFailureListener {
                    // This failure will happen if the barcode scanning model
                    // fails to download from Google Play Services
                }
                .addOnCompleteListener {
                    // When the image is from CameraX analysis use case, must
                    // call image.close() on received images when finished
                    // using them. Otherwise, new images may not be received
                    // or the camera may stall
                    imageProxy.image?.close()
                    imageProxy.close()
                }
        }
    }

    LaunchedEffect(key1 = true) {
        requestCameraPermission()
    }

    suspend fun Context.getCameraProvider(): ProcessCameraProvider =
        suspendCoroutine { continuation ->
            ProcessCameraProvider.getInstance(this).also { cameraProvider ->
                cameraProvider.addListener({
                    continuation.resume(cameraProvider.get())
                }, ContextCompat.getMainExecutor(this))
            }
        }

    LaunchedEffect(key1 = scanQrViewModel.isCameraPermissionGranted.value) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            analysisUseCase
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)

        analysisUseCase.setAnalyzer(
            // newSingleThreadExecutor() will let is perform analysis on a single worker thread
            Executors.newSingleThreadExecutor()
        ) { imageProxy ->
            processImageProxy(scanner, imageProxy, cameraProvider)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }

    if (scanQrViewModel.isCameraPermissionGranted.value) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())
    } else {
        Text("Permissiob not Granted")
    }
}