package com.uso.proyectouso;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uso.proyectouso.Navegacion.Contactos.ContactosFragment;
import com.uso.proyectouso.Navegacion.Contactos.ProveedoresFragment;
import com.uso.proyectouso.Navegacion.Inicio.InicioFragment;
import com.uso.proyectouso.Navegacion.Reportes.ReportesFragment;
import com.uso.proyectouso.Navegacion.Scanner.ScannerFragment;


public class Principal extends AppCompatActivity{
    BottomNavigationView btnNavegacion;
    MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            initTitle();
        }
        btnNavegacion = findViewById(R.id.btnNavegacion);

        btnNavegacion.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_navegacion,
                    new InicioFragment()).commit();
        }
    }
    private void initTitle() {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(btnNavegacion.getMenu().getItem(0).getTitle());
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    toolbar.setTitle(item.getTitle());
                    switch (item.getItemId()) {
                        case R.id.navigacion_Inicio:
                            selectedFragment = new InicioFragment();
                            break;
                        case R.id.navigacion_contacto:
                            selectedFragment = new ContactosFragment();
                            break;
                        case R.id.navigacion_scanner: {
                            selectedFragment = new ScannerFragment();
                            break;
                        }
                        case R.id.navigacion_reporte:
                            selectedFragment = new ReportesFragment();
                            break;
                        case R.id.navigacion_Proveedores:
                            selectedFragment = new ProveedoresFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_navegacion,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_configuracion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnConfiguracion:
                Toast.makeText(this, "Confuracion selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.btnCerrarSesion:
                Toast.makeText(this, "Cerrar Sesion selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
