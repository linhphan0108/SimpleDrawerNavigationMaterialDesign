package com.linhphan.simpledrawernavigationmaterialdesign.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.linhphan.simpledrawernavigationmaterialdesign.R;
import com.linhphan.simpledrawernavigationmaterialdesign.view.fragment.ContentFragment;

public class MainActivity extends AppCompatActivity {
    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
//                if (!menuItem.isChecked())
//                    menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();
                String data;
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.inbox:
                        Toast.makeText(getApplicationContext(), "Inbox Selected", Toast.LENGTH_SHORT).show();
                        data = "Inbox Selected";
                        break;
                    case R.id.starred:
                        Toast.makeText(getApplicationContext(), "Stared Selected", Toast.LENGTH_SHORT).show();
                        data = "Stared Selected";
                        break;
                    case R.id.sent_mail:
                        Toast.makeText(getApplicationContext(), "Send Selected", Toast.LENGTH_SHORT).show();
                        data = "send Selected";
                        break;
                    case R.id.drafts:
                        Toast.makeText(getApplicationContext(), "Drafts Selected", Toast.LENGTH_SHORT).show();
                        data = "Drafts Selected";
                        break;
                    case R.id.all_mail:
                        Toast.makeText(getApplicationContext(), "All Mail Selected", Toast.LENGTH_SHORT).show();
                        data = "All Mail Selected";
                        break;
                    case R.id.trash:
                        Toast.makeText(getApplicationContext(), "Trash Selected", Toast.LENGTH_SHORT).show();
                        data = "Trash Selected";
                        break;

//                    case R.id.spam:
//                        Toast.makeText(getApplicationContext(),"Spam Selected",Toast.LENGTH_SHORT).show();
//                        data = "Spam Selected";
//                        break;

                    case R.id.submenu_1:
                        Toast.makeText(getApplicationContext(), "submenu_1", Toast.LENGTH_SHORT).show();
                        data = "submenu_1";
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        data = "Something wrong";
                        break;
                }

                openFragment(data);
//
//                navigationView.getMenu().clear();
//                navigationView.inflateMenu(R.menu.drawer_navigation_item);
//                navigationView.getMenu().findItem(menuItem.getItemId()).setChecked(true);

                return true;
            }
        });


        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //open the default fragment
        openFragment("Inbox Selected");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void openFragment(String data) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame, ContentFragment.newInstance(data)).commit();
        setTitle(data);
        CoordinatorLayout layout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        Snackbar.make(layout, "Welcome to AndroidHive", Snackbar.LENGTH_INDEFINITE).show();
    }
}
