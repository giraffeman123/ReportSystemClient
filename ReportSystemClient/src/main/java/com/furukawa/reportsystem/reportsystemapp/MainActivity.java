package com.furukawa.reportsystem.reportsystemapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto.*;
import com.furukawa.reportsystem.reportsystemapp.ui.defecto_en_linea.*;
import com.furukawa.reportsystem.reportsystemapp.ui.lider.*;

public abstract class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AgregarLider.OnFragmentInteractionListener,ConsultarLider.OnFragmentInteractionListener,
                EliminarLider.OnFragmentInteractionListener,ModificarLider.OnFragmentInteractionListener,AgregarDefectoEnLinea.OnFragmentInteractionListener,
                ConsultarDefectoEnLineaPorFecha.OnFragmentInteractionListener, ConsultarDefectoEnLineaPorLider.OnFragmentInteractionListener,
                ConsultarDefectoEnLineaPorLinea.OnFragmentInteractionListener,ConsultarDefectoEnLineaPorTurno.OnFragmentInteractionListener, AgregarCodigoDefecto.OnFragmentInteractionListener,
                ConsultarCodigoDefectoPorArea.OnFragmentInteractionListener,EliminarCodigoDefecto.OnFragmentInteractionListener, ModificarCodigoDefecto.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Fragment fragment;
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Boolean fragmentoSeleccionado = false;

        /*** MANTENIMIENTO DE LIDERES ***/
        if (id == R.id.nav_agregar_lider) {
            fragment = new AgregarLider();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
            fragmentoSeleccionado=true;
        } else if (id == R.id.nav_consultar_lider) {
            fragment = new ConsultarLider();
            fragmentoSeleccionado = true;
        } else if (id == R.id.nav_modificar_lider) {
            fragment = new ModificarLider();
            fragmentoSeleccionado = true;
        } else if (id == R.id.nav_eliminar_lider) {
            fragment = new EliminarLider();
            fragmentoSeleccionado = true;
        }

        /*** MANTENIMIENTO DE CODIGO DE DEFECTO ***/
        else if (id == R.id.nav_agregar_codigo_defecto) {
            fragmentoSeleccionado = true;
            //fragment = new AgregarCodigoDefecto();
        } else if (id == R.id.nav_consultar_codigo_defecto_por_area) {
            fragmentoSeleccionado = true;
            //fragment = new ConsultarCodigoDefectoPorArea();
        } else if (id == R.id.nav_modificar_codigo_defecto) {
            fragmentoSeleccionado = true;
            //fragment = new ModificarCodigoDefecto();
        } else if (id == R.id.nav_eliminar_codigo_defecto) {
            fragmentoSeleccionado = true;
            //fragment = new EliminarCodigoDefecto();
        }
        /*** MANTENIMIENTO DE CODIGO DE DEFECTO ***/

        /*** MANTENIMIENTO DEFECTO EN LINEA ***/
        else if (id == R.id.nav_agregar_defecto_en_linea) {
            fragmentoSeleccionado = true;
            //fragment = new AgregarDefectoEnLinea()
        }else if (id == R.id.nav_consultar_defecto_en_linea_por_turno) {
            fragmentoSeleccionado = true;
            //fragment = new ConsultarDefectoEnLineaPorTurno();
        }else if (id == R.id.nav_consultar_defecto_en_linea_por_fecha) {
            fragmentoSeleccionado = true;
            //fragment = new ConsultarDefectoEnLineaPorFecha();
        }else if (id == R.id.nav_consultar_defecto_en_linea_por_lider) {
            fragmentoSeleccionado = true;
            //fragment = new ConsultarDefectoEnLineaPorLider();
        }else if (id == R.id.nav_consultar_defecto_en_linea_por_linea) {
            fragmentoSeleccionado = true;
            //fragment = new ConsultarDefectoEnLineaPorLinea();
        }
        /*** MANTENIMIENTO DEFECTO EN LINEA ***/

        /*** CONFIGURACION WI-FI ***/
        else if (id == R.id.nav_wifi_conf) {
            fragmentoSeleccionado = true;
        }
        /*** CONFIGURACION WI-FI ***/

        /*** CERRAR SESION ***/
        else if (id == R.id.nav_cerrar_sesion) {
            finish();
            System.exit(0);
        }
        /*** CERRAR SESION ***/

        if(fragmentoSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
