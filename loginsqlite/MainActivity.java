package ma.projet.android.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signin,signup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);
        signup=(Button) findViewById(R.id.btnsignup);
        signin=(Button) findViewById(R.id.btnsignin);
        DB=new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String user= username.getText().toString();
             String pass= password.getText().toString();
             String repass= repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                   if(pass.equals(repass)) {
                       Boolean checkuser=DB.checkusername(user);
                       if(checkuser==false) {
                           Boolean insert = DB.insertData(user, pass);
                           if(insert==true) {
                               Toast.makeText(MainActivity.this, "Registered Successfuly", Toast.LENGTH_SHORT).show();
                               Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                               startActivity(intent);

                           }else {
                               Toast.makeText(MainActivity.this, "Registered failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else {
                           Toast.makeText(MainActivity.this, "user already exists!", Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(MainActivity.this, "passwords not matching", Toast.LENGTH_SHORT).show();
                   }
                   }
                }

        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            }
        });
    }


}