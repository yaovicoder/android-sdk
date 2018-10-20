package com.nanjsdk.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {

    //Views define

    //Variables define
    private String _password = Const.DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("NANJ Wallet");
        initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            _password = bundle.getString(Const.BUNDLE_KEY_PASSWORD, Const.DEFAULT);
        }
    }

    private void initView() {
        TabLayout tableLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.pagerTabLayout);
        TabViewPagerAdapter viewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerAdapter.onResume(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle when tab unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle when tab reselected
            }
        });
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("menu click", "onContextItemSelected: " + item.getItemId());
        switch (item.getItemId()) {
            case R.id.menuNewWallet:
                Intent intent = new Intent(this, WalletsActivity.class);
                intent.putExtra(Const.BUNDLE_KEY_PASSWORD, _password);
                startActivityForResult(intent, 100);
                break;
            case R.id.chooseCoinType:
                startActivityForResult(new Intent(this, ChooseCoinTypeActivity.class), 1002);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}