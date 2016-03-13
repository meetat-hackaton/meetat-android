package android.de.meetat;

import android.app.SearchManager;
import android.content.Context;
import android.de.meetat.authenticate.LoginFragment;
import android.de.meetat.authenticate.RegisterFragment;
import android.de.meetat.friends.FriendsFragment;
import android.de.meetat.profile.ProfileFragment;
import android.de.meetat.reminder.MyRemindersFragment;
import android.de.meetat.search.SearchFragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import fr.tkeunebr.gravatar.Gravatar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (!Session.getSessionReminder().isLogged(getApplicationContext())) {
            showNavView(false);
            selectItem(Navigation.Login);
        } else {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

            drawer.addDrawerListener(toggle);
            navigationView.setNavigationItemSelectedListener(this);
            toggle.syncState();
            selectItem(Navigation.Start);
        }
    }

    private void showNavView(Boolean doShow) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (doShow) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, navigationView);
        }
        else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, navigationView);;
        }

    }

    private void get_gravatar(String email, Context context) {
        String gravatarUrl = Gravatar.init().with(email).size(50).build();
        Picasso.with(context)
                .load(gravatarUrl)
                .into((ImageView) this.findViewById(R.id.gravatar));
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
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
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
        Navigation nav = Navigation.fromId(id);
        return selectItem(nav);
    }

    public Boolean selectItem(Navigation position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (position) {
            case Login:
                fragment = new LoginFragment();
                title = "Login";
                break;
            case Register:
                fragment = new RegisterFragment();
                title = "Register";
                break;
            case Start:
                fragment = new SearchFragment();
                title = "Start Here";
                break;
            case Profile:
                fragment = new ProfileFragment();
                title = "My Profile";
                break;
            case Friends:
                fragment = new FriendsFragment();
                title = "My Friends";
                break;
            case MyReminders:
                fragment = new MyRemindersFragment();
                title = "Created Reminders";
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
