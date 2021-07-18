package com.example.runningtrackerapp.ui.fragments

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.other.TrackingUtility
import com.example.runningtrackerapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_run.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunFragment : Fragment(R.layout.fragment_run) , EasyPermissions.PermissionCallbacks {

    private val viewModel : MainViewModel by viewModels()
    private val TAG = "RunFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //requestPermissions()
        checkBackgroundLocationPermissionAPI30(1)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
            
        }
    }

    private fun requestPermissions(){

        if(TrackingUtility.hasLocationPermissions(requireContext())){
            return
        }
        Log.i(TAG,"Permission not granted")
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.R){

                Log.i(TAG,"Before calling permissions")
                EasyPermissions.hasPermissions(
                    requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )

        }else{

                EasyPermissions.hasPermissions(
                    requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )

        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }else{
            requestPermissions()
        }
    }

    @TargetApi(30)
    private fun checkBackgroundLocationPermissionAPI30(backgroundLocationRequestCode: Int) {
        if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PermissionChecker.PERMISSION_GRANTED) return
        AlertDialog.Builder(requireContext())
            .setTitle("Get Permission")
            .setMessage("Get Permission")
            .setPositiveButton("Yes") { _,_ ->
                // this request will take user to Application's Setting page
                requestPermissions(arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION), backgroundLocationRequestCode)
            }
            .setNegativeButton("No") { dialog,_ ->
                dialog.dismiss()
            }
            .create()
            .show()

    }
}