package m.nicholas.mealville.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.fragment_container) FrameLayout fragment_container;
    @BindView(R.id.bottom_nav) BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottom_nav.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(fragment_container.getId(),new HomeFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment;
        switch (menuItem.getItemId()){
            case R.id.nav_search:
                selectedFragment = new SearchFragment();
                break;
            case R.id.nav_add:
                selectedFragment = new NewRecipeFragment();
                break;
            case R.id.nav_profile:
                selectedFragment = new ProfileFragment();
                break;
            case R.id.nav_home:
            default:
                selectedFragment = new HomeFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(fragment_container.getId(),selectedFragment).commit();
        return true;
    }
}
