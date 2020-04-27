package com.example.myday03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button closeButton;
        closeButton = (Button)findViewById(R.id.button);

        builder = new AlertDialog.Builder(this);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                builder.setIcon(R.drawable.waring);
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
                builder.setMessage("Do you want to close this application ??").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(getApplicationContext(),
                                "You  Choose Yes Action For Alertbox",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"You Choose No Action For Alertbox",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("AlertDialogExample");
                alert.show();
            }
        });

        Button show ;
        show = (Button)findViewById(R.id.dialog);
        show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                createItemDialog();
            }
        });

        Button detail;
        detail = (Button)findViewById(R.id.detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSingleDialog();
            }
        });

        Button multi;
        multi = (Button)findViewById(R.id.multi);
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMultiChoiceDialog();
            }
        });
    }
    private void createItemDialog() {
        final String[] items = { "Marshmallow", "Lollipop", "KitKat","Jelly Bean"};
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("เลือกเวอร์ชั่นของแอนดรอยด์")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void createSingleDialog() {
        final String[] items = { "Marshmallow", "Lollipop", "KitKat","Jelly Bean"};
        int checkedIndex = 1;
        final String[] checkedItems = { items[checkedIndex] };  //มีสมาชิกเพียงตัวเดียว

        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("เลือกเวอร์ชั่นของแอนดรอยด์")
                .setSingleChoiceItems(items, checkedIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //อ่านรายการที่ถูกคลิกเก็บพักไว้ในอาร์เรย์ เมื่อเราเปลี่ยนไปเลือกรายการอื่น
                        //ค่าที่เก็บในอาร์เรย์ ก็จะถูกแทนที่ด้วยค่าใหม่ไปเรื่อยๆ
                        checkedItems[0] = items[which];
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //นำค่าของรายการที่ถูกคลิกล่าสุดจากอาร์เรย์ไปใช้งาน
                        Toast.makeText(getBaseContext(), checkedItems[0], Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void createMultiChoiceDialog() {
        final String[] items = {"Nexus", "Samsung", "HTC", "Sony"};
        final boolean[] checkedItems = new boolean[items.length];
        checkedItems[2] = true;  //ให้สมาชิกลำดับที่ 2 ถูกเลือกไว้ล่วงหน้า (ลำดับที่ไม่กำหนดจะไม่ถูกเลือก)

        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Android Phone ที่ท่านสนใจ")
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked; //เปลี่ยนสถานะของสมาชิกในอาร์เรย์ตามการเลือก
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //เอาเฉพาะรายการที่ถูกเลือก เก็บพักไว้ใน ArrayList
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < items.length; i++) {
                    if (checkedItems[i] == true) {
                        arrayList.add(items[i]);
                    }
                }
                //เราจะนำ ArrayList ไปใช้งานโดยตรงเลยก็ได้ แต่ในที่นี้จะแแปลงจาก ArrayList ไปเป็น String Array

                String[] list = arrayList.toArray(new String[arrayList.size()]);
                getMultiChoiceDialogCheckedItems(list);
            }
        }).show();
    }
    private void getMultiChoiceDialogCheckedItems(String[] checkedItems) {
        String str = "";
        for(String s: checkedItems) {
            if(!str.equals("")) {
                str += ", ";
            }
            str += s;
        }
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
    }


}
