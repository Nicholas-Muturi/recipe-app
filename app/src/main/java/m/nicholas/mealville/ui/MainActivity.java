package m.nicholas.mealville.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.fragment_container) FrameLayout fragment_container;
    @BindView(R.id.bottom_nav) BottomNavigationView bottom_nav;
    private Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottom_nav.setOnNavigationItemSelectedListener(this);
        selectedFragment = HomeFragment.newInstance();
        implementSelectedFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.custom_recipe:
                selectedFragment = CustomRecipeFragment.newInstance();
                break;
            case R.id.nav_add:
                selectedFragment = NewRecipeFragment.newInstance();
                break;
            case R.id.nav_profile:
                selectedFragment = ProfileFragment.newInstance();
                break;
            case R.id.nav_home:
            default:
                selectedFragment = HomeFragment.newInstance();
        }
        implementSelectedFragment();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        inflater.inflate(R.menu.menu_logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.searchMenuOption){
            selectedFragment = SearchFragment.newInstance();
            implementSelectedFragment();
        }
        if(item.getItemId() == R.id.firebaseLogout){
            logoutUser();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void implementSelectedFragment(){
        getSupportFragmentManager().beginTransaction().replace(fragment_container.getId(),selectedFragment).commit();
    }
}
