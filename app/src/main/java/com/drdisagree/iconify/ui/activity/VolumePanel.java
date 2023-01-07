package com.drdisagree.iconify.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.drdisagree.iconify.Iconify;
import com.drdisagree.iconify.R;
import com.drdisagree.iconify.config.PrefConfig;
import com.drdisagree.iconify.utils.OverlayUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class VolumePanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_panel);

        // Header
        CollapsingToolbarLayout collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setTitle(getResources().getString(R.string.activity_title_volume_panel));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch thin_bg = findViewById(R.id.thin_bg);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch thick_bg = findViewById(R.id.thick_bg);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch no_bg = findViewById(R.id.no_bg);

        thin_bg.setChecked(PrefConfig.loadPrefBool(Iconify.getAppContext(), "IconifyComponentVPBG1.overlay"));

        thin_bg.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                thick_bg.setChecked(false);
                no_bg.setChecked(false);
                OverlayUtil.disableOverlay("IconifyComponentVPBG2.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG2.overlay", false);
                OverlayUtil.disableOverlay("IconifyComponentVPBG3.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG3.overlay", false);
                OverlayUtil.enableOverlay("IconifyComponentVPBG1.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG1.overlay", true);
            } else {
                OverlayUtil.disableOverlay("IconifyComponentVPBG1.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG1.overlay", false);
            }
        });

        thick_bg.setChecked(PrefConfig.loadPrefBool(Iconify.getAppContext(), "IconifyComponentVPBG2.overlay"));

        thick_bg.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                thin_bg.setChecked(false);
                no_bg.setChecked(false);
                OverlayUtil.disableOverlay("IconifyComponentVPBG1.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG1.overlay", false);
                OverlayUtil.disableOverlay("IconifyComponentVPBG3.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG3.overlay", false);
                OverlayUtil.enableOverlay("IconifyComponentVPBG2.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG2.overlay", true);
            } else {
                OverlayUtil.disableOverlay("IconifyComponentVPBG2.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG2.overlay", false);
            }
        });

        no_bg.setChecked(PrefConfig.loadPrefBool(Iconify.getAppContext(), "IconifyComponentVPBG3.overlay"));

        no_bg.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                thin_bg.setChecked(false);
                thick_bg.setChecked(false);
                OverlayUtil.disableOverlay("IconifyComponentVPBG1.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG1.overlay", false);
                OverlayUtil.disableOverlay("IconifyComponentVPBG2.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG2.overlay", false);
                OverlayUtil.enableOverlay("IconifyComponentVPBG3.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG3.overlay", true);
            } else {
                OverlayUtil.disableOverlay("IconifyComponentVPBG3.overlay");
                PrefConfig.savePrefBool(Iconify.getAppContext(), "IconifyComponentVPBG3.overlay", false);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}