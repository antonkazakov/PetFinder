package com.greencode.petfinder.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */

public class BaseFragment extends Fragment {

    protected <T> void startActivity(Class<T> t) {
        if (t == Activity.class) {
            Intent intent = new Intent(getActivity(), t);
            startActivity(intent);
        }
    }

    protected <T> void startActivity(Class<T> t, Bundle bundle) {
        if (t == Activity.class) {
            Intent intent = new Intent(getActivity(), t);
            intent.putExtra("extras", bundle);
            startActivity(intent);
        }
    }

    protected void showToast(String toastText) {
        Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
    }

}
