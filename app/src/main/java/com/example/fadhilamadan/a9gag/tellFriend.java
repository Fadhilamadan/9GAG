package com.example.fadhilamadan.a9gag;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class tellFriend extends AppCompatActivity {
    public EditText hp;
    public EditText msg;
    public static final int REQUEST_SELECT_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell_friend);
        ImageButton kirim = (ImageButton) findViewById(R.id.imgKirim);
        hp = (EditText) findViewById(R.id.txtNomor);
        msg = (EditText) findViewById(R.id.txtIsiPesan);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sms = new Intent(Intent.ACTION_SEND);
                sms.setData(Uri.parse("smsto"));
                sms.setType("text/plain");
                sms.putExtra("address"  , new String ( hp.getText().toString()));
                sms.putExtra("sms_body", msg.getText().toString());
                if (sms.resolveActivity(getPackageManager()) != null) {
                    startActivity(sms);
                }

            }
        });

        ImageButton kontak = (ImageButton) findViewById(R.id.imgKontak);
        kontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(i, REQUEST_SELECT_CONTACT);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                hp.setText(number);
            }
        }
    }

}
