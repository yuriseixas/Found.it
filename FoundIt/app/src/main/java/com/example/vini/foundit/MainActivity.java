package com.example.vini.foundit;
import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import android.content.DialogInterface;


import static android.Manifest.permission.CAMERA;


import android.net.Uri;

import android.support.v7.app.AlertDialog;

import android.util.Log;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;


public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA =0;
    private  ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        int currentApiVersion = Build.VERSION.SDK_INT;
        if(currentApiVersion >= Build.VERSION_CODES.M){
            if(checkPermission()){
                //Toast.makeText(MainActivity.this, "Permission is granted", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext(), "ACEITA", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
            }
        }
      /*  requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar acb = getActionBar();
        if(acb != null){
            acb.hide();
        }
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Toast.makeText(getApplicationContext(),"Bem-vindo ao 20ver, aproxime a sua câmera da etiqueta contendo o QRcode para escutar informações sobre o vestuário",Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);
        final TextView helloTextView = (TextView) findViewById(R.id.Resposta);
        helloTextView.setText("Bem-vindo ao 20ver, aproxime a sua câmera da etiqueta contendo o QRcode para escutar informações sobre o vestuário");*/
       /* IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Camera Scan");
        integrator.setCameraId(0);
        integrator.initiateScan();*/

    }
    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            }
        }
    }
    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(MainActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getApplicationContext(), "Bem-vindo ao 20ver, aproxime a sua câmera da etiqueta contendo o QRcode para escutar informações sobre o vestuário", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_SHORT).show();
                   // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                }

                break;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Camera Scan");
        integrator.setCameraId(0);
        integrator.initiateScan();

        //alert("Bem-vindo ao 20ver, aproxime a sua câmera da etiqueta contendo o QRcode para escutar informações sobre o vestuário");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Camera Scan");
        integrator.setCameraId(0);
        integrator.initiateScan();
    }*/

    /* @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);

            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("Camera Scan");
            integrator.setCameraId(0);
            integrator.initiateScan();
        }
    */
  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                alert(result.getContents());
                 }else{
                   alert("Scan cancelado");
            }
        }else {

            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    private void alert(String msg){
        //TextView helloTextView = (TextView) findViewById(R.id.Texto);
        //helloTextView.setText(msg);
        Intent it = new Intent(this, Activity2.class);
        it.putExtra("Resultado", msg);
        startActivity(it);

    }
*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }
    @Override
    public void handleResult(Result result) {
        final String myResult = result.getText();
        Intent it = new Intent(this, Activity2.class);
        it.putExtra("Resultado", myResult);
        startActivity(it);
       /* Log.d("QRCodeScanner", result.getText());
        Log.d("QRCodeScanner", result.getBarcodeFormat().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(MainActivity.this);
            }
        });
        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myResult));
                startActivity(browserIntent);
            }
        });
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();*/
    }
}

