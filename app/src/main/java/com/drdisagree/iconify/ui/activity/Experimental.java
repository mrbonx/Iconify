package com.drdisagree.iconify.ui.activity;

import static com.drdisagree.iconify.common.References.HIDE_QSLABEL_SWITCH;
import static com.drdisagree.iconify.common.References.QSALPHA_LEVEL;
import static com.drdisagree.iconify.common.References.QSTRANSPARENCY_SWITCH;
import static com.drdisagree.iconify.common.References.STATUSBAR_CLOCKBG;
import static com.drdisagree.iconify.common.References.SYSTEM_UI_PACKAGE;
import static com.drdisagree.iconify.common.References.VERTICAL_QSTILE_SWITCH;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.drdisagree.iconify.R;
import com.drdisagree.iconify.config.Prefs;
import com.drdisagree.iconify.config.RemotePrefs;
import com.drdisagree.iconify.utils.FabricatedOverlayUtil;
import com.drdisagree.iconify.utils.SystemUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.topjohnwu.superuser.Shell;

import java.util.List;
import java.util.Objects;

public class Experimental extends AppCompatActivity {

    List<String> accurate_sh = Shell.cmd("settings get secure monet_engine_accurate_shades").exec().getOut();
    int shade = initialize_shade();

    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experimental);

        // Header
        CollapsingToolbarLayout collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setTitle(getResources().getString(R.string.activity_title_experimental));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Accurate Shades
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch enable_accurate_shades = findViewById(R.id.enable_accurate_shades);
        enable_accurate_shades.setChecked(shade != 0);
        enable_accurate_shades.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Shell.cmd("settings put secure monet_engine_accurate_shades 1").exec();
            } else {
                Shell.cmd("settings put secure monet_engine_accurate_shades 0").exec();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private int initialize_shade() {
        int shade = 1;
        try {
            shade = Integer.parseInt(accurate_sh.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shade;
    }
}