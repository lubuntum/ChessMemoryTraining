package com.example.chessmemory.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.chessmemory.databinding.SettingsDialogBinding;

public class SettingsDialog extends DialogFragment {
    public static SettingsDialog getInstance(){
        Bundle args = new Bundle();
        SettingsDialog settingsDialog = new SettingsDialog();
        settingsDialog.setArguments(args);
        return settingsDialog;
    }

    SettingsDialogBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SettingsDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
