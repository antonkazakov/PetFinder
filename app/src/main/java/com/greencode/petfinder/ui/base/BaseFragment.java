package com.greencode.petfinder.ui.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */

public class BaseFragment extends Fragment {

    protected void showToast(String toastText) {
        Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
    }

}
