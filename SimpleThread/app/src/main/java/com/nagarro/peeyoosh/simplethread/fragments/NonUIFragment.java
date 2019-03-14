package com.nagarro.peeyoosh.simplethread.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nagarro.peeyoosh.simplethread.OrientationSurvivalActivity;
import com.nagarro.peeyoosh.simplethread.R;

public class NonUIFragment extends Fragment {

    private static final String TAG = "NonUIFragment";
    private NonUiViewModel mViewModel;
    private OrientationSurvivalActivity orientationSurvivalActivity;

    public static NonUIFragment newInstance() {
        return new NonUIFragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.w(TAG, TAG + "=== onAttach");
        orientationSurvivalActivity = (OrientationSurvivalActivity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w(TAG, TAG + "=== onAttach");
        setRetainInstance(true);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(TAG, TAG + "=== onCreate");
        startTask();
    }

    private void startTask() {
        mViewModel = ViewModelProviders.of(this).get(NonUiViewModel.class);
        mViewModel.startTask();
        mViewModel.progressValue.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer progress) {
//                Log.w(TAG, TAG + "=== Progress  " + progress);
                if(orientationSurvivalActivity!=null)
                    orientationSurvivalActivity.setProgressValue(progress);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.w(TAG, TAG + "=== onCreateView");
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.w(TAG, TAG + "=== onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.w(TAG, TAG + "=== onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.w(TAG, TAG + "=== onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.w(TAG, TAG + "=== onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.w(TAG, TAG + "=== onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG, TAG + "=== onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.w(TAG, TAG + "=== onDetach");
    }
}
