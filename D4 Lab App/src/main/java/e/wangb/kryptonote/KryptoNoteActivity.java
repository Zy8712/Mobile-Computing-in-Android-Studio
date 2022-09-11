package e.wangb.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class KryptoNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }
    public void onEncrypt(View v) {
        try {
            String key = ((EditText) findViewById(R.id.key)).getText().toString();
            String note = ((EditText) findViewById(R.id.data)).getText().toString();
            Cipher cipher = new Cipher(key);
            ((EditText) findViewById(R.id.data)).setText(cipher.encrypt(note));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void onDecrypt(View v){
        try {
            String key = ((EditText) findViewById(R.id.key)).getText().toString();
            String note = ((EditText) findViewById(R.id.data)).getText().toString();
            Cipher cipher = new Cipher(key);
            ((EditText) findViewById(R.id.data)).setText(cipher.decrypt(note));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void onSave(View v){
    try{
        String name=((EditText)findViewById(R.id.file)).getText().toString();
        File dir=this.getFilesDir();
        File file=new File(dir,name);
        FileWriter fw=new FileWriter(file);
        fw.write(((EditText)findViewById(R.id.data)).getText().toString());
        fw.close();
        Toast.makeText(this,"Note saved",Toast.LENGTH_LONG).show();
    }
    catch (Exception e){
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
    }
    }
    public void onLoad(View v){
        try{
            String name=((EditText)findViewById(R.id.file)).getText().toString();
            File dir=this.getFilesDir();
            File file=new File(dir,name);
            FileReader fr=new FileReader(file);
            String show="";
            for (int c=fr.read();c!=-1;c=fr.read()){
                show+=(char)c;
            }
            fr.close();
            ((EditText)findViewById(R.id.data)).setText(show);
            Toast.makeText(this,"Note Loaded",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
