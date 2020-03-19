package com.malav.deeplinked;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    String payeeAddress = "9654508740@upi";
    String payeeName = "Abhishek Kumar";
    String transactionNote = "Test for Deeplinking";
    String amount = "1";
    String currencyUnit = "INR";
    TextView infoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSubmit = (Button) findViewById(R.id.submit);
        final EditText upiEt = (EditText) findViewById(R.id.upiEt);
        infoTv = (TextView) findViewById(R.id.infoTv);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("upi://pay?pa="+upiEt.getText().toString()+"&pn="+payeeName+"&tn="+transactionNote+
                        "&am="+amount+"&cu="+currencyUnit);
                Log.d(TAG, "onClick: uri: "+uri);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(checkIfCanHandle(intent)) {
                    startActivityForResult(intent, 1);
                }
            }
        });

    }


    private boolean checkIfCanHandle(Intent intent){
        PackageManager packageManager = this.getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            return true;
        } else {
            setMessage("No Intent available to handle action");
            return false;
        }
    }

    private void setMessage(String message){
        infoTv.setText(message);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: requestCode: "+requestCode +"\n");
        stringBuilder.append("onActivityResult: resultCode: "+resultCode +"\n");
        Log.d(TAG, "onActivityResult: requestCode: "+requestCode);
        Log.d(TAG, "onActivityResult: resultCode: "+resultCode);
        //txnId=UPI20b6226edaef4c139ed7cc38710095a3&responseCode=00&ApprovalRefNo=null&Status=SUCCESS&txnRef=undefined
        //txnId=UPI608f070ee644467aa78d1ccf5c9ce39b&responseCode=ZM&ApprovalRefNo=null&Status=FAILURE&txnRef=undefined

        if(data!=null) {
            stringBuilder.append("data  : " +data.toString());
            Log.d(TAG, "onActivityResult: data: " + data.getStringExtra("response"));
            Toast.makeText(getApplicationContext(),"onActivityResult: data: " + data.getStringExtra("response"),Toast.LENGTH_LONG).show();
            String res = data.getStringExtra("response");
            String search = "SUCCESS";
            if (res.toLowerCase().contains(search.toLowerCase())) {
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }

            Bundle bundle = data.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    String out = "key : " +key + " : " + (bundle.get(key) != null ? bundle.get(key) : " NULL ") +"\t\n";
                    stringBuilder.append(out);
                }
            }
        }else{
            stringBuilder.append("data is null \n");
        }

        setMessage(stringBuilder.toString());

        Log.i(TAG,stringBuilder.toString());


    }
}
