package com.example.bmi

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast

class contact_me : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: activitycontact_meBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =activitycontact_meBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnActionView.setOnClickListener(this)
        binding.btnActionDial.setOnClickListener(this)
        binding.btnActionCall.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btn_action_view ->{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/reference/android/content/Intent"))
                startActivity(intent)
            }

            R.id.btn_action_dial ->{
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:111111111")
                startActivity(intent)
            }

            R.id.btn_action_call ->{


                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PERMISSION_GRANTED){
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:1234567890")
                    startActivity(intent)
                }else{
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 1001)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001){
            if(grantResults.isNotEmpty() && permissions[0].equals(PackageManager.PERMISSION_GRANTED)){

            }else{
                Toast.makeText(this, "Please give permission", Toast.LENGTH_SHORT).show()
            }

        }
    }
}