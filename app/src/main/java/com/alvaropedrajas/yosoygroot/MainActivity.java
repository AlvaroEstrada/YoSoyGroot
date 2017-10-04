package com.alvaropedrajas.yosoygroot;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBuscar;
    private Button btnAct;
    private Button btnAdd;
    private Button btnDelete;
    private BBDD_Helper helper;
    private EditText et_id;
    private EditText et_name;
    private EditText et_last;

    private String nom, ape;
    private Integer id;
    private boolean regFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnAct = (Button) findViewById(R.id.btnAct);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnAct.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        et_id = (EditText) findViewById(R.id.id);
        et_name = (EditText) findViewById(R.id.name);
        et_last = (EditText) findViewById(R.id.last);

        helper = new BBDD_Helper(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAct:

                break;
            case R.id.btnAdd:
                getDatos(view);
                if (regFlag){
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(BBDD.COLUMN_NAME_1, id);
                    values.put(BBDD.COLUMN_NAME_2, nom);
                    values.put(BBDD.COLUMN_NAME_3, ape);
                    long newRowId = db.insert(BBDD.TABLE_NAME, null, values);
                    Toast.makeText(getApplicationContext(), "Se guardo el registro con clave: "+newRowId, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBuscar:

                SQLiteDatabase db = helper.getReadableDatabase();

                String[] projection = {
                        BBDD.COLUMN_NAME_1,
                        BBDD.COLUMN_NAME_2,
                        BBDD.COLUMN_NAME_3,
                };

                String selection = BBDD.COLUMN_NAME_1 + " = ?";
                String[] selectionArgs = { et_id.getText().toString() };

                String sortOrder =
                        BBDD.COLUMN_NAME_2 + " DESC";

                Cursor c = db.query(
                        BBDD.TABLE_NAME,                          // The table to query
                        projection,                               // The columns to return
                        selection,                                // The columns for the WHERE clause
                        selectionArgs,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        sortOrder                                 // The sort order
                );

                if (c.getCount() > 0){
                    c.moveToFirst();
                }

                String item = c.getString(
                        c.getColumnIndexOrThrow(BBDD.COLUMN_NAME_2)
                );


                //HAY QUE EDITAR AQUI
                Toast.makeText(getApplicationContext(), "El nombre es: " + item, Toast.LENGTH_SHORT).show();
                c.close();
                break;
            case R.id.btnDelete:

                break;
        }

    }

    public void getDatos(View v){

        if (!TextUtils.isEmpty(et_id.getText().toString())){
            id = Integer.parseInt(et_id.getText().toString());
        }else{
            et_id.setError("Debes introducir un ID!");
            id = null;
        }

        if (!TextUtils.isEmpty(et_name.getText().toString())){
            nom = et_name.getText().toString();
        }else{
            et_name.setError("Debes introducir un nombre!");
            nom = null;
        }

        if (!TextUtils.isEmpty(et_last.getText().toString())){
            ape = et_last.getText().toString();
        }else{
            et_last.setError("Debes introducir un apellido!");
            ape = null;
        }

        if (!Objects.equals(id, null) && !Objects.equals(nom, null) && !Objects.equals(ape, null)){
            regFlag = true;
        }else{
            regFlag = false;
            Toast.makeText(this, "¡Debes rellenar todos los campos!", Toast.LENGTH_LONG).show();
            return;
        }

    }
}
