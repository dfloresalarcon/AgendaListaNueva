package com.example.dfloresalarcon.agendalistanueva;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dfloresalarcon.agendalistanueva.R;

public class Borrar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        //Cambiamos titulo a la activity
        setTitle("Confirm Remove!");

        //Recogemos el intent y lo casteamos a Contact
        Contact c = (Contact)getIntent().getSerializableExtra("deleteContact");

        //llamamos a los botones y cajas de texto
        final EditText txtNameDelete = (EditText)findViewById(R.id.txtNameDelete);
        final EditText txtPhoneDelete = (EditText)findViewById(R.id.txtPhoneDelete);

        Button btnYES = (Button)findViewById(R.id.btnYES);
        Button btnCANCEL = (Button)findViewById(R.id.btnCANCEL);

        //---------------------hasta aqui la llamada -------


        //-------------editamos graficos------------
        txtNameDelete.setTextColor(Color.WHITE);
        txtPhoneDelete.setTextColor(Color.WHITE);
        btnYES.setTextColor(Color.WHITE);
        btnCANCEL.setTextColor(Color.BLACK);

        txtNameDelete.setBackgroundColor(Color.GRAY);
        txtPhoneDelete.setBackgroundColor(Color.GRAY);
        btnYES.setBackgroundColor(Color.GREEN);
        btnCANCEL.setBackgroundColor(Color.RED);

        txtNameDelete.setEnabled(false);
        txtPhoneDelete.setEnabled(false);
        //------hasta aqui graficos editados---------

        //--Colocamos en las cajas de texto el objeto recibido
        txtNameDelete.setText(c.getName().toString());
        txtPhoneDelete.setText(c.getPhone().toString());
        //---- hasta aqui colocacion del objeto recibido

        //-----------------LISTENER DE CONFIRMAR
        btnYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contact contactb = new Contact("deleteThis", "0");
                Intent intent = new Intent(Borrar.this, Activity2.class);
                intent.putExtra("contact", contactb);

                setResult(RESULT_OK, intent);

                finish();
            }
        });
        //-----------------HASTA AQUI LISTENER DE CONFIRMACION


        //---LISTENER DE CANCELAR
        btnCANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        ///---------------HASTA AQUI LISTENER DE CANCELAR
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.borrar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
