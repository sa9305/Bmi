package com.example.bmi

import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bmi.R.id.About_bmi
import java.text.DecimalFormat
import kotlin.system.exitProcess

@Suppress("UNUSED_EXPRESSION")
class MainActivity : AppCompatActivity() {
    val Tag: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d(Tag, "onCreate")
        var isclear: Boolean = false

        var weight = findViewById<EditText>(R.id.W_input)
        var Height = findViewById<EditText>(R.id.h_input)
        val c_btn = findViewById<Button>(R.id.btn_calculate)
        val result = findViewById<TextView>(R.id.result)
        val value = findViewById<TextView>(R.id.te2_input)

        c_btn.setOnClickListener() {
            if (weight.text.toString().isEmpty() && Height.text.toString().isEmpty()) {
                Toast.makeText(this, " fill both value", Toast.LENGTH_SHORT).show()
            }
            if (Height.text.toString().isEmpty() && weight.text.toString().isNotEmpty()) {
                Toast.makeText(this, " fill height value", Toast.LENGTH_SHORT).show()
                Height.requestFocus()
            }
            if (weight.text.toString().isEmpty() && Height.text.toString().isNotEmpty()) {
                Toast.makeText(this, " fill weight value", Toast.LENGTH_SHORT).show()
                weight.requestFocus()
            }
            if (Height.text.toString().isEmpty() && weight.text.toString().isNotEmpty()) {

            }
            //for replace button text
            if (isclear) {
                isclear = false
                weight.text.clear()
                weight.isEnabled = true
                Height.isEnabled = true

                Height.text.clear()
                value.setText("")
                c_btn.setText("Calculate")
                Toast.makeText(this, " clear all", Toast.LENGTH_SHORT).show()

            } else {
                if (Height.text.toString().isNotEmpty() && weight.text.toString()
                        .isNotEmpty()
                ) {
                    weight.isEnabled = false
                    Height.isEnabled = false
                    weight.requestFocus()
                    if (!isclear) {
                        isclear = true
                        c_btn.setText("clear")
                        Toast.makeText(this, " calculate bmi", Toast.LENGTH_SHORT).show()
                        val hf = Height.text.toString().toDouble()
                        val wf = weight.text.toString().toDouble()
                        val h1 = hf / 100
                        val bm1 = wf / (h1 * h1)
                        result.text = ((bm1 * 100) / 100.0).toString()
                        var msg: String = "";

                        if (bm1 < 18.5) {
                            msg = "you are underweight";
                        } else if (bm1 > 25 && bm1 < 29.9) {
                            msg = "you are overweight"
                        } else if (bm1 > 30) {
                            "obese";
                        } else {
                            msg = "you are normal";
                        }
                        val df = DecimalFormat("##3")
                        value.setText(msg);
                        result.setText(" BMI value:" + df.format(bm1).toString())

                    }
                }
            }

        }

    }

    // Here we create  menu status in our app
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_devop -> {
                val intent = Intent(this, aboutdevoler::class.java).also {
                    startActivity(it)
                }
            }
            R.id.Bmi_chart -> {
                val intent = Intent(this, Bmi_chart::class.java).also {
                    startActivity(it)
                }
            }
            // R.id.exit -> {
            //    finish()
            //   exitProcess(0)
            // }
            R.id.About_bmi -> {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=UowWA-8TiyI")
                )
                startActivity(intent)
            }
            R.id.contact_me -> {
                //  val intent = Intent(Intent.ACTION_DIAL)
                // intent.data = Uri.parse("tel:9305022629")
                val intent = Intent(this, contact_me::class.java).also {
                    startActivity(it)

                }
                /*    R.id.call -> {
                if (ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.CALL_PHONE
                    ) == PERMISSION_GRANTED
                ) {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:9305022629")
                    startActivity(intent)
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CALL_PHONE),
                        1002
                    )
                }
            }
          //  R.id.email -> {
           //     Toast.makeText(this, "email", Toast.LENGTH_SHORT).show()

           // }*/

            }
        }

            return super.onOptionsItemSelected(item)
        }

        /*override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1002) {
            if ((grantResults.isNotEmpty() && permissions[0].equals(packageManager))) {

            } else {
                Toast.makeText(this, "please give permission", Toast.LENGTH_SHORT).show()

            }


        }*/


        @Deprecated("Depricated in java", ReplaceWith("unit"))
        override fun onBackPressed() = Unit
    }































