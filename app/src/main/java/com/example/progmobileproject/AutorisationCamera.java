package com.example.progmobileproject;

import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


public class AutorisationCamera extends Fragment implements OnClickListener {

    private RelativeLayout containerView;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == 2)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //on appelle
                takePiscture();
            }
            else if (shouldShowRequestPermissionRationale(permissions[0]) == false)
            {
                displayOptions();
            }
            else
            {
                explain();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_autorisation_camera, container, false);
        containerView = (RelativeLayout) rootView.findViewById(R.id.container);
        final Button takePiscture = (Button) rootView.findViewById(R.id.bouton_takephoto);
        takePiscture.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view)
    {
        if (ActivityCompat.checkSelfPermission(getContext(), permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if (shouldShowRequestPermissionRationale(permission.WRITE_EXTERNAL_STORAGE) == true)
            {
                explain();
            }
            else
            {
                askForPermission();
            }
        }
        else
        {
            takePiscture();
        }
    }


    private void displayOptions()
    {
        Snackbar.make(containerView, "Vous avez désactivé la permission", Snackbar.LENGTH_LONG).setAction("Paramètres", new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                final Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }).show();
    }

    private void askForPermission()
    {
        requestPermissions(new String[] { permission.WRITE_EXTERNAL_STORAGE }, 2);
    }

    private void explain()
    {
        Snackbar.make(containerView, "Cette permission est nécessaire pour appeler", Snackbar.LENGTH_LONG).setAction("Activer", new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                askForPermission();
            }
        }).show();
    }

    private void takePiscture()
    {
        //final Intent intent = new Intent(Intent.ACTION_);

        //startActivity(intent);
    }
}
