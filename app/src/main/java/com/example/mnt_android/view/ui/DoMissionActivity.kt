package com.example.mnt_android.view.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.DoMissionViewModel
import java.io.File
import java.io.FileInputStream

class DoMissionActivity : AppCompatActivity()
{

    lateinit var doMissionViewModel: DoMissionViewModel
    lateinit var doMissionFragment : DoMissionFragment
    lateinit var doMissionFragment2 : DoMissionFragment2
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentManager: FragmentManager
    lateinit var backPressViewModel : BackPressViewModel
    val REQUEST_TEXT_GALLERY = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do_mission)

        doMissionViewModel = ViewModelProviders.of(this)[DoMissionViewModel::class.java]
        backPressViewModel=  ViewModelProviders.of(this)[BackPressViewModel::class.java]

        fragmentManager=supportFragmentManager
        fragmentTransaction=fragmentManager.beginTransaction()
        doMissionFragment= DoMissionFragment()
        doMissionFragment2= DoMissionFragment2()



         setFrag(0)

    }

    fun setImage()
    {

        var permissionResult = checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if(permissionResult == PackageManager.PERMISSION_DENIED)
        {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
        }
        else
        {
            val intent = Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            startActivityForResult(intent, REQUEST_TEXT_GALLERY);
        }


    }

 /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK)
        {
            var ff = File(photoPath)
            var bit = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(ff))
            if(bit!=null)
            {
                Toast.makeText(this@Popupselect,"사진생성",Toast.LENGTH_LONG).show()
            }
        }

        var intent: Intent = Intent()

        intent.putExtra("path", photoPath)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
*/
    fun setFrag(n : Int)
    {
        fragmentTransaction = fragmentManager.beginTransaction()
        when(n)
        {
            0 ->
            {
                fragmentTransaction.replace(R.id.frag_do_mission,doMissionFragment)
                doMissionViewModel.fragmentNum=0
                fragmentTransaction.commit()

            }
            1->
            {
                fragmentTransaction.replace(R.id.frag_do_mission,doMissionFragment2)
                doMissionViewModel.fragmentNum=1
                fragmentTransaction.commit()
            }
        }
    }



    override fun onBackPressed() {

        when(doMissionViewModel.fragmentNum)
        {
            0->
            {
                finish()
            }
            1->
            {
                setFrag(0)
            }

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_TEXT_GALLERY)
        {
            if(data!=null) {
                val selectedImage = data?.getData();
                var filePathColumn: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
                var cursor = getContentResolver().query(
                    selectedImage,
                    filePathColumn, null, null, null
                );
                cursor.moveToFirst();

                val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                val picturePath = cursor.getString(column_index);

                cursor.close();
                // String picturePath contains the path of selected Image
                var matrix = Matrix();
                val bmp = BitmapFactory.decodeStream(FileInputStream(picturePath), null, null)
                var bm =
                    Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);

                Log.d("wlgusdnzzz", bm.toString())

                doMissionViewModel.bitmap = bm
            }


        }

    }



}