package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Begin extends Fragment{

    private String val;
    private Button btn2;
    private View view;

    Begin.OnBeginDataListener frListener;

    public Begin() {

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
        if(context instanceof Menu.OnMenuDataListener) {
            frListener = (Begin.OnBeginDataListener) context;
        } else {
            // исключение можно выбросить
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.begin, container, false);
        btn2 = view.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // печатаем полученное значение из активности
                //Snackbar.make(view.findViewById(R.id.fr1root),val,Snackbar.LENGTH_LONG).show();
                // вызываем метод реализованный в активности
                frListener.onBeginDataListener("back string");

            }
        });
        return view;
    }

    public interface OnBeginDataListener {
        void onBeginDataListener(String str);
    }
}
