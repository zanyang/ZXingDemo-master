package com.adouble.zxingdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.encoding.EncodingHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 0;

    ImageView qrcodeImg;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_qrcode_encode)
    Button btQrcodeEncode;
    @BindView(R.id.bt_qrcode_dencode)
    Button btQrcodeDencode;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    private String inputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputMessage = etInput.getText().toString();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(MainActivity.this, scanResult, Toast.LENGTH_LONG).show();
        }
    }

    @OnClick({R.id.bt_qrcode_encode, R.id.bt_qrcode_dencode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_qrcode_encode:
                try {
//                    Bitmap mBitmap = EncodingHandler.createQRCode("www.baidu.com", 300);
//                    ivQrcode.setImageBitmap(mBitmap);
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.head);
                    if (!TextUtils.isEmpty(inputMessage)) {
                        Bitmap www = EncodingHandler.createQRCode(inputMessage, 1200, 1200, bitmap);
                        ivQrcode.setImageBitmap(www);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_qrcode_dencode:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }
}
