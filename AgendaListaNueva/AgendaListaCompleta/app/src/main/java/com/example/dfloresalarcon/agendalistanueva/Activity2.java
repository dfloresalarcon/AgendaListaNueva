package com.example.dfloresalarcon.agendalistanueva;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dfloresalarcon.agendalistanueva.R;

public class Activity2 extends Activity {

    //creamos una variable inicial de tipo contacto
    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        //CAMBIAR TITULO A LA ACTIVITY
        setTitle("Edit Contact");


        //deserealizamos el objeto y lo metemos en la variable anteriormente creada.
        contact = (Contact)getIntent().getSerializableExtra("contact");

        //-----LLAMAMOS A LOS BOTONES Y TXTS PARA USARLOS----
        Button btnOK = (Button)findViewById(R.id.btnOK);
        final EditText txtNameEdited = (EditText)findViewById(R.id.txtNameEdited);
        final EditText txtPhoneEdited = (EditText)findViewById(R.id.txtPhoneEdited);
        //--- llamamos al boton delete
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        //-------------llamada hasta aqui--------------------------

        //LOS VALORES ENCONTRADOS EN EL OBX.
        txtNameEdited.setText(contact.getName().toString());
        txtPhoneEdited.setText(contact.getPhone().toString());

        //EDITAMOS GRAFICOS ;D
        btnOK.setTextColor(Color.BLACK);
        btnOK.setBackgroundColor(Color.GREEN);
        btnDelete.setTextColor(Color.BLACK);
        btnDelete.setBackgroundColor(Color.RED);
        txtNameEdited.setTextColor(Color.WHITE);
        txtNameEdited.setBackgroundColor(Color.GRAY);
        txtPhoneEdited.setTextColor(Color.WHITE);
        txtPhoneEdited.setBackgroundColor(Color.GRAY);
        //EDITAR GRAFICOS HASTA AQUI....


        //abrimos el listener del btnOK
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //evaluamos si las cajas de texto estan vacias, si es así, mandamos un mensaje de error en el toast.
                if (txtNameEdited.getText().toString().equalsIgnoreCase("") || txtPhoneEdited.getText().toString().equalsIgnoreCase(""))
                {
                    String msg="We need some information to modify the contact";
                    showToast(msg);
                }
                //de lo contrario hacemos nuestras operaciones
                else
                {
                    //creamos un contacto nuevo con las variables de las cajas de texto
                    contact= new Contact(txtNameEdited.getText().toString(), txtPhoneEdited.getText().toString());

                    //creamos un intent para llamar a la activity listar
                    Intent intent = new Intent(Activity2.this, Listar.class);

                    //serializamos el objeto y lo enviamos en el intento.
                    intent.putExtra("contact", contact);

                    //IMPRIMO EL TOAST PARA MI MISMO PARA VER SI SE ME MODIFICÓ EL OBJETO
                    String msg="Modified :"+"Name::: "+contact.getName().toString()+" Phone::: "+contact.getPhone().toString();
                    showToast(msg);

                    //enviamos un resultado ok y el intento con el objeto dentro, por supuesto.
                    setResult(RESULT_OK, intent);

                    //finalizamos nuestra actividad actual.
                    finish();

                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact c = new Contact(txtNameEdited.getText().toString(), txtPhoneEdited.getText().toString());

                Intent intent = new Intent(Activity2.this, Borrar.class);
                intent.putExtra("deleteContact", c);
                startActivityForResult(intent, 1);
            }
        });
    }

    //------------------ON ACTIVITY RESULT


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==1 && resultCode==RESULT_OK)
        {
            contact = (Contact)data.getSerializableExtra("contact");
            //creamos un intent para llamar a la activity listar
            Intent intent = new Intent(Activity2.this, Listar.class);

            //serializamos el objeto y lo enviamos en el intento.
            intent.putExtra("contact", contact);

            //IMPRIMO EL TOAST PARA MI MISMO PARA VER SI SE ELIMINÓ EL OBJETO
            String msg="deleted?? :"+"Name::: "+contact.getName().toString()+" Phone::: "+contact.getPhone().toString();
            showToast(msg);

            //enviamos un resultado ok y el intento con el objeto dentro, por supuesto.
            setResult(RESULT_OK, intent);

            //finalizamos nuestra actividad actual.
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity2, menu);
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

    protected void showToast(String msg)
    {
        Context contexto = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        Toast tostada = Toast.makeText(contexto, msg, duracion);
        tostada.show();
    }
}
