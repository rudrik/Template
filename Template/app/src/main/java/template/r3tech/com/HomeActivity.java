package template.r3tech.com;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import template.r3tech.com.fragments.FavItemsFragment;
import template.r3tech.com.fragments.ItemsFragment;
import template.r3tech.com.utils.DBHelper;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pushFragment(new ItemsFragment());
                    return true;
                case R.id.navigation_dashboard:
                    pushFragment(new FavItemsFragment());
                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.frameLayout, fragment);
                ft.commit();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        DBHelper _helper = new DBHelper(this);
        SQLiteDatabase _db = _helper.getWritableDatabase();
        _helper.initiateData(HomeActivity.this);
        _helper.close();

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pushFragment(new ItemsFragment());
    }

}
