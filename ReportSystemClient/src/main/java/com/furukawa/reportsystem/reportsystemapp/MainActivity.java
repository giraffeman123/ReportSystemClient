package com.furukawa.reportsystem.reportsystemapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.furukawa.reportsystem.reportsystemapp.api.model.CodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;
import com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto.AgregarCodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto.BuscarCodigoDefectoPorAreaAndMaquina;
import com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto.BuscarCodigoDefectoPorGravedad;
import com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto.ConsultarCodigoDefectoPorAreaAndMaquina;
import com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto.ConsultarCodigoDefectoPorGravedad;
import com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto.EliminarCodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto.ModificarCodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.ui.defecto_en_linea.AgregarDefectoEnLinea;
import com.furukawa.reportsystem.reportsystemapp.ui.defecto_en_linea.ConsultarDefectoEnLineaPorFecha;
import com.furukawa.reportsystem.reportsystemapp.ui.defecto_en_linea.ConsultarDefectoEnLineaPorLider;
import com.furukawa.reportsystem.reportsystemapp.ui.defecto_en_linea.ConsultarDefectoEnLineaPorLinea;
import com.furukawa.reportsystem.reportsystemapp.ui.defecto_en_linea.ConsultarDefectoEnLineaPorTurno;
import com.furukawa.reportsystem.reportsystemapp.ui.lider.AgregarLider;
import com.furukawa.reportsystem.reportsystemapp.ui.lider.ConsultarAllLideres;
import com.furukawa.reportsystem.reportsystemapp.ui.lider.ConsultarLider;
import com.furukawa.reportsystem.reportsystemapp.ui.lider.ConsultarLideresGeneral;
import com.furukawa.reportsystem.reportsystemapp.ui.lider.EliminarLider;
import com.furukawa.reportsystem.reportsystemapp.ui.lider.BuscarLider;
import com.furukawa.reportsystem.reportsystemapp.ui.lider.ModificarLider;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AgregarLider.OnFragmentInteractionListener,
        ConsultarLider.OnFragmentInteractionListener, ConsultarAllLideres.OnFragmentInteractionListener,
        EliminarLider.OnFragmentInteractionListener,
        BuscarLider.OnFragmentInteractionListener,ModificarLider.OnFragmentInteractionListener,
        BuscarLider.OnSendingLiderListener, ConsultarLideresGeneral.OnFragmentInteractionListener,
        AgregarDefectoEnLinea.OnFragmentInteractionListener,
        ConsultarDefectoEnLineaPorFecha.OnFragmentInteractionListener, ConsultarDefectoEnLineaPorLider.OnFragmentInteractionListener,
        ConsultarDefectoEnLineaPorLinea.OnFragmentInteractionListener, ConsultarDefectoEnLineaPorTurno.OnFragmentInteractionListener,
        AgregarCodigoDefecto.OnFragmentInteractionListener, ConsultarCodigoDefectoPorAreaAndMaquina.OnFragmentInteractionListener,
        EliminarCodigoDefecto.OnFragmentInteractionListener, ModificarCodigoDefecto.OnFragmentInteractionListener,
        BuscarCodigoDefectoPorAreaAndMaquina.OnFragmentInteractionListener, BuscarCodigoDefectoPorAreaAndMaquina.OnSendingCodigoDefectoListener,
        ConsultarCodigoDefectoPorGravedad.OnFragmentInteractionListener,BuscarCodigoDefectoPorGravedad.OnFragmentInteractionListener
        ,BuscarCodigoDefectoPorGravedad.OnSendingCodigoDefectoListenerGravedad{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        ImageView i = (ImageView) findViewById(R.id.imgLogoFurukawa);

        // Hice un comentario de prueba para Github

        //Fragment fragment = null;
        //Boolean fragmentoSeleccionado = false;

        /*** MANTENIMIENTO DE LIDERES ***/
        if (id == R.id.nav_agregar_lider) {
            AgregarLider fragment = new AgregarLider();
            i.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        } else if (id == R.id.nav_consultar_lider) {
            ConsultarLideresGeneral fragment = new ConsultarLideresGeneral();
            i.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        } else if (id == R.id.nav_modificar_lider) {
            BuscarLider fragment = new BuscarLider();
            i.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        } else if (id == R.id.nav_eliminar_lider) {
            EliminarLider fragment = new EliminarLider();
            i.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }
        /*** MANTENIMIENTO DE LIDERES ***/

        /*** MANTENIMIENTO DE CODIGO DE DEFECTO ***/
        else if (id == R.id.nav_agregar_codigo_defecto) {
            AgregarCodigoDefecto fragment = new AgregarCodigoDefecto();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        } else if (id == R.id.nav_consultar_codigo_defecto_por_area) {
            BuscarCodigoDefectoPorAreaAndMaquina fragment = new BuscarCodigoDefectoPorAreaAndMaquina();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        } else if (id == R.id.nav_consultar_codigo_defecto_por_gravedad) {
            BuscarCodigoDefectoPorGravedad fragment = new BuscarCodigoDefectoPorGravedad();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        } else if (id == R.id.nav_modificar_codigo_defecto) {
            ModificarCodigoDefecto fragment = new ModificarCodigoDefecto();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        } else if (id == R.id.nav_eliminar_codigo_defecto) {
            EliminarCodigoDefecto fragment = new EliminarCodigoDefecto();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }
        /*** MANTENIMIENTO DE CODIGO DE DEFECTO ***/

        /*** MANTENIMIENTO DEFECTO EN LINEA ***/
        else if (id == R.id.nav_agregar_defecto_en_linea) {
            AgregarDefectoEnLinea fragment = new AgregarDefectoEnLinea();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }else if (id == R.id.nav_consultar_defecto_en_linea_por_turno) {
            ConsultarDefectoEnLineaPorTurno fragment = new ConsultarDefectoEnLineaPorTurno();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }else if (id == R.id.nav_consultar_defecto_en_linea_por_fecha) {
            ConsultarDefectoEnLineaPorFecha fragment = new ConsultarDefectoEnLineaPorFecha();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }else if (id == R.id.nav_consultar_defecto_en_linea_por_lider) {
            ConsultarDefectoEnLineaPorLider fragment = new ConsultarDefectoEnLineaPorLider();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }else if (id == R.id.nav_consultar_defecto_en_linea_por_linea) {
            ConsultarDefectoEnLineaPorLinea fragment = new ConsultarDefectoEnLineaPorLinea();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }
        /*** MANTENIMIENTO DEFECTO EN LINEA ***/

        /*** CONFIGURACION WI-FI ***/
        else if (id == R.id.nav_wifi_conf) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }
        /*** CONFIGURACION WI-FI ***/

        /*** CERRAR SESION ***/
        else if (id == R.id.nav_cerrar_sesion) {
            finish();
            System.exit(0);
        }
        /*** CERRAR SESION ***/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onInputBuscarLiderSent(Lider lider) {
        ModificarLider fragment = ModificarLider.newInstance(lider.getCodigoEmpleado(),lider.getArea(),
                String.valueOf(lider.getLinea()),lider.getEmpleado().getNombre(),lider.getEmpleado().getPuesto(),
                lider.getEmpleado().getTurno());
        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
    }

    @Override
    public void OnInputBuscarCodigoDefectoSent(List<CodigoDefecto> codigoDefecto) {
        ConsultarCodigoDefectoPorAreaAndMaquina fragment = ConsultarCodigoDefectoPorAreaAndMaquina.newInstance((Serializable) codigoDefecto);
        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();

    }

    @Override
    public void OnInputBuscarCodigoDefectoGravedadSent(List<CodigoDefecto> codigoDefecto) {
        ConsultarCodigoDefectoPorGravedad fragment = ConsultarCodigoDefectoPorGravedad.newInstance((Serializable) codigoDefecto);
        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();

    }
}
