package com.example.fadhilamadan.a9gag;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class mainActivity extends AppCompatActivity {
    Dialog dialog;

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

        Button buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(mainActivity.this);
                dialog.setContentView(R.layout.login_layout);
                dialog.setTitle("LOGIN FORM");
                dialog.show();

                //Button dialogButton = (Button) dialog.findViewById(R.id.btn)
                Button dialogButton = (Button) dialog.findViewById(R.id.btnSubmit);
                final TextView uname = (TextView)dialog.findViewById(R.id.txtUser);
                final TextView upass = (TextView) dialog.findViewById(R.id.txtPass);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),halamanutama.class);
                        startActivity(intent);
                    }
                });
            }
        });

        ReadData rd = new ReadData(this);
        rd.execute("http://192.168.43.146/penir/penir.php");

    }
}
