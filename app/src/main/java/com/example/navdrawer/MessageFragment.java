package com.example.navdrawer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MessageFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mTemp, mHumi;
    private TextView mTempText, mHumiText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message,container,false);
        mTempText = rootView.findViewById(R.id.temperature);
        mHumiText = rootView.findViewById(R.id.humidity);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(mTemp != null){
            sensorManager.registerListener(this,mTemp,SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            mTempText.setText(R.string.temp_not_supported);
        }

        mHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(mHumi != null){
            sensorManager.registerListener(this,mHumi,SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            mHumiText.setText(R.string.humidity_not_supported);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            mTempText.setText("Temperature: " + sensorEvent.values[0]);
        } else if(sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            mHumiText.setText("Humidity: " + sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}


