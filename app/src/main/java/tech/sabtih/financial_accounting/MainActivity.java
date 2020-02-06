package tech.sabtih.financial_accounting;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import tech.sabtih.financial_accounting.listeners.OnListInteractionListener;
import tech.sabtih.financial_accounting.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements OnListInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;
    boolean selectionmode = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);










    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (selectionmode) {
            getMenuInflater().inflate(R.menu.main_selection, menu);
        }else {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(selectionmode){

            HomeFragment homef = (HomeFragment) getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager().getFragments().get(0);
            homef.selectionCanceled();

            onSelectModeEnded();
            return true;

        }else {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_users){
            HomeFragment homef = (HomeFragment) getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager().getFragments().get(0);
            homef.deleteSelected();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectModeStarted() {
        selectionmode = true;
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        Toolbar toolbar_select = findViewById(R.id.toolbar_selection);
        toolbar_select.setVisibility(View.VISIBLE);



        setSupportActionBar(toolbar_select);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        invalidateOptionsMenu();
        getMenuInflater().inflate(R.menu.main_selection,toolbar_select.getMenu());

    }

    @Override
    public void onSelectModeEnded() {
        selectionmode = false;
        Toolbar toolbar_select = findViewById(R.id.toolbar_selection);
        toolbar_select.setVisibility(View.GONE);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);


        setSupportActionBar(toolbar);
    }

    @Override
    public void onSelectionUpdated(int selected) {
        if(selectionmode){
            TextView tvcount = findViewById(R.id.selected_count);

            tvcount.setText(""+selected);

            if(selected == 0){
                onSelectModeEnded();
            }
        }

    }
}
