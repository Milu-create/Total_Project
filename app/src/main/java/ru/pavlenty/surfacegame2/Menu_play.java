package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Timer;
import java.util.TimerTask;

public class Menu_play extends Fragment {
    private String val;
    private Button btn_records, auto1, auto2;
    private TextView info;
    private View view;
    User user;
    Timer timer;

    Menu_play.OnMenu_playDataListener frListener;

    public Menu_play() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            val = getArguments().getString(MainActivity.KEY);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Menu.OnMenuDataListener) {
            frListener = (Menu_play.OnMenu_playDataListener) context;
        } else {
            throw new NullPointerException("Объект не создан на основе класса Menu_play");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        user = Login.user;
        view = inflater.inflate(R.layout.menu_play, null);
        btn_records = view.findViewById(R.id.records);
        btn_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.root, new Records());
                ftrans.commit();
            }
        });
        info = view.findViewById(R.id.text);
        DelayedPrinter.Word word = new DelayedPrinter.Word(10, user.getName()+", увас "+user.getPoint()+" монет");
        word.setOffset(50);
        DelayedPrinter.printText(word, info);
        timer = new Timer();
        timer.scheduleAtFixedRate(new Rule_for_point(), 0, 300000);
        auto1 = view.findViewById(R.id.auto1);
        auto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),GameActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public interface OnMenu_playDataListener {
        void onMenu_playDataListener(String str);
    }

    private void rule(){
        user.setPoint(user.getPoint()+1);
    }
}
