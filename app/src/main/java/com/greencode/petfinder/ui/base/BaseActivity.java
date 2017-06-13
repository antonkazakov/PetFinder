package com.greencode.petfinder.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * @author Anton Kazakov
 * @date 11.06.17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
    }

    @LayoutRes
    public abstract int getLayout();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    protected <T> void startActivity(Class<T> t) {
        if (t == Activity.class) {
            Intent intent = new Intent(this, t);
            startActivity(intent);
        }
    }

    protected <T> void startActivity(Class<T> t, Bundle bundle) {
        if (t == Activity.class) {
            Intent intent = new Intent(this, t);
            intent.putExtra("extras", bundle);
            startActivity(intent);
        }
    }

    protected void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
    }

    

}
