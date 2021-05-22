package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Menu extends Fragment {
    private String val;
    private Button btn3;
    private View view;

    OnMenuDataListener frListener;

    public Menu() {

    }

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
        if(context instanceof OnMenuDataListener) {
            frListener = (OnMenuDataListener) context;
        } else {
            // исключение можно выбросить
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu, container, false);
        btn3 = view.findViewById(R.id.btn_play);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // печатаем полученное значение из активности
                //Snackbar.make(view.findViewById(R.id.fr1root),val,Snackbar.LENGTH_LONG).show();
                // вызываем метод реализованный в активности
                frListener.onMenuDataListener("back string");

            }
        });
        return view;
    }

    public interface OnMenuDataListener {
        void onMenuDataListener(String str);
    }
}
