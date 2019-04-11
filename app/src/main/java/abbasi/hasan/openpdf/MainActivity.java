package abbasi.hasan.openpdf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button openPdfFromFile = findViewById(R.id.button_open_from_file);
        openPdfFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ "book.pdf");
                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(Uri.fromFile(file),"application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                Intent intent = Intent.createChooser(target, "فتح الملف");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this ,"قم بتثبيت تطبيق قراءة ملفات PDF" ,Toast.LENGTH_LONG).show();
                }
            }
        });
        Button openPdfFromURL = findViewById(R.id.button_open_from_url);
        openPdfFromURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                alert.setTitle("اختر مكان العرض");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "تطبيق الهاتف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://justlens.net/recommendation.pdf"));
                        startActivity(browserIntent);
                    }
                });
                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "هذا التطبيق", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                alert.show();
            }
        });
    }



}
