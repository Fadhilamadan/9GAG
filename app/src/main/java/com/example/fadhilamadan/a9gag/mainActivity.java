package com.example.fadhilamadan.a9gag;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class mainActivity extends AppCompatActivity {
    //final ProductHelper product = new ProductHelper(getApplicationContext());
    Dialog dialog;
    ArrayList<Username> p;
    TextView uname;
    TextView upass;
    //public static final int REQUEST_SELECT_CONTACT = 1;

    public static void readDataFinish(Context context, String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to quit?").setTitle("Exit");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mainActivity.this.finish();
            }
        });
        builder.setNegativeButton("No",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProductHelper product = new ProductHelper(getApplicationContext());
        product.getWritableDatabase();
        p = product.sqlSelectUsername();

        ImageButton buttonLogin = (ImageButton) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(mainActivity.this);
                dialog.setContentView(R.layout.login_layout);
                dialog.setTitle("LO GIN FORM");
                dialog.show();

                //Button dialogButton = (Button) dialog.findViewById(R.id.btn)
                Button dialogButton = (Button) dialog.findViewById(R.id.btnSubmit);


                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uname = (TextView)dialog.findViewById(R.id.txtUser);
                        upass = (TextView) dialog.findViewById(R.id.txtPass);
                        for (int i =0; i< p.size(); i++) {
                            if (uname.getText().toString().equals(p.get(i).getUsername().toString())){
                                if (upass.getText().toString().equals(p.get(i).getPassword().toString())){
                                    Intent intent = new Intent(getApplicationContext(),halamanutama.class);
                                    String ambilNamaUser = uname.getText().toString();
                                    intent.putExtra("namaUser", ambilNamaUser);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(mainActivity.this, "password salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(mainActivity.this,p.get(i).getUsername().toString(),Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }
                        //Toast.makeText(mainActivity.this, "hai", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        /*ReadData rd = new ReadData(this);
        rd.execute("http://192.168.0.11/penir/penir.php");*/
    }
}
