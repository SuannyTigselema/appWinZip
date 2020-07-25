package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.Models.adaptadorSD;
import com.example.myapplication.Models.archivo;
import com.example.myapplication.WebService.Asynchtask;
import com.example.myapplication.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerArchivos extends AppCompatActivity implements Asynchtask {
    ArrayList<String> listDatos;
    RecyclerView recyclerview=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_archivos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerview=(RecyclerView)findViewById(R.id.rcvListaArchivos);

        //añadir un Divider a los elementos de la lista->Diseño de la linea de separacion de los items
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //Establecer el LayoutManager para definir la forma en la que se muestran los items en este caso en  forma de lista vertical
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        //Solicitud WebService
        Map<String, String> datos = new HashMap<>();
        WebService ws= new WebService("https://mercado-a4435.firebaseio.com/archivo.json",datos,
                RecyclerArchivos.this, (Asynchtask) RecyclerArchivos.this);

        ws.execute("GET");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnurecyclerd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray JSONlistaProductos = new JSONArray(result);
        ArrayList<archivo> lstArchivos=new ArrayList<archivo>();

        //Invoco al metodo de la clase productos que es para el parseo de datos-me devuelve un arraylist de producto
        lstArchivos = archivo.JsonObjectsBuild(JSONlistaProductos);

        //HASTA AQUÍ SÍ RECIBE LOS 11 PRODUCTOS
        //Envío la lista de productos a l
        adaptadorSD adapatorSD = new adaptadorSD(this, lstArchivos);
        recyclerview.setAdapter(adapatorSD);
    }
}