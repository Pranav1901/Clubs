package android.example.cells;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    TextView textView;
    EditText name, domain, dob, dep, fcord, pname;
    Button insert;
    DBHelper DB;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // get the content of both the edit text
            String nameT = name.getText().toString();
            String domainT = domain.getText().toString();
            String dobT = dob.getText().toString();
            String depT = dep.getText().toString();
            String fcordT = fcord.getText().toString();
            String pnameT = pname.getText().toString();



            // check whether both the fields are empty or not
            boolean flag = !nameT.isEmpty() && !domainT.isEmpty()&& !dobT.isEmpty() && !depT.isEmpty() && !fcordT.isEmpty() && !pnameT.isEmpty();
            System.out.println(flag);
            if(flag) insert.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag",0);
        textView = findViewById(R.id.inserttextView);
        insert = findViewById(R.id.btninset);
        insert.setVisibility(View.INVISIBLE);


        if(flag==1){
            textView.setText("Update Information");
            insert.setText("UPDATE");
        }
        name = findViewById(R.id.name);
        domain = findViewById(R.id.domain);
        dob = findViewById(R.id.dob);
        dep = findViewById(R.id.department);
        fcord = findViewById(R.id.fCordinator);
        pname = findViewById(R.id.president);
        DB = new DBHelper(this);
        int count [] = new int [1];
        name.addTextChangedListener(new TextValidator(name) {
            @Override
            public void validate(TextView textView, String text) {
                if(name.length()==0){
                    name.setError("Name is Required");
                }
                else if(!checkS(name.getText().toString())){
                    name.setError("Invalid Name");
                }
                else{
                    count[0]++;
                }
            }
        });
        domain.addTextChangedListener(new TextValidator(domain) {
            @Override
            public void validate(TextView textView, String text) {
                if(domain.length()==0){
                    domain.setError("Name is Required");
                }
                else if(!checkS(domain.getText().toString())){
                    domain.setError("Invalid Name");
                }
                else{
                    count[0]++;
                }
            }
        });
        dob.addTextChangedListener(new TextValidator(dob) {
            @Override
            public void validate(TextView textView, String text) {
                if(dob.length()==0){
                    dob.setError("Name is Required");
                }
                else if(!checkN(dob.getText().toString())){
                    dob.setError("Invalid Name");
                }
                else{
                    count[0]++;
                }
            }
        });
        dep.addTextChangedListener(new TextValidator(dep) {
            @Override
            public void validate(TextView textView, String text) {
                if(dep.length()==0){
                    dep.setError("Name is Required");
                }
                else if(!checkS(dep.getText().toString())){
                    dep.setError("Invalid Name");
                }
                else{
                    count[0]++;
                }
            }
        });
        fcord.addTextChangedListener(new TextValidator(fcord) {
            @Override
            public void validate(TextView textView, String text) {
                if(fcord.length()==0){
                    fcord.setError("Name is Required");
                }
                else if(!checkS(fcord.getText().toString())){
                    fcord.setError("Invalid Name");
                }
                else{
                    count[0]++;
                }
            }
        });
        pname.addTextChangedListener(new TextValidator(pname) {
            @Override
            public void validate(TextView textView, String text) {
                if(pname.length()==0){
                    pname.setError("Name is Required");
                }
                else if(!checkS(pname.getText().toString())){
                    pname.setError("Invalid Name");
                }
                else{
                    count[0]++;
                }
                System.out.println("Count = "+count[0]);
                if(count[0]>=6){
                    insert.setVisibility(View.VISIBLE);
                }
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String domaintTxt = domain.getText().toString();
                String dobTxt = dob.getText().toString();
                String depTXT = dep.getText().toString();
                String fcordTXT = fcord.getText().toString();
                String pnameTXT = pname.getText().toString();
                Intent intent1 = new Intent(InsertActivity.this,AdminPanel.class);
                    if(flag==0){
                        boolean checkinsertdata = DB.insertclubdata(nameTxt,domaintTxt,dobTxt,depTXT,fcordTXT,pnameTXT);
                        if(checkinsertdata==true){
                            Toast.makeText(InsertActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(InsertActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        boolean checkupdatedata = DB.updateclubdata(nameTxt,domaintTxt,dobTxt,depTXT,fcordTXT,pnameTXT);
                        if(checkupdatedata==true){
                            Toast.makeText(InsertActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(InsertActivity.this,"Entry Not Updated",Toast.LENGTH_SHORT).show();
                        }
                    }
                    startActivity(intent1);
                }
        });

    }
    private  boolean checkN(String num){
        if(checkS(num))return false;
        int x = Integer.parseInt(num);
        if(x<2002||x>2022)return false;
        else return true;
    }
    private  boolean checkS(String name){
        for(int i =0;i<name.length();i++){
            if(name.charAt(i)>='0'&&name.charAt(i)<='9'){
                return false;
            }
        }
        return true;
    }
}