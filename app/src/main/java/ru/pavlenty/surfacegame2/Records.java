package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Records extends Fragment {
    private String val;
    private Button btn2;
    private View view;
    private TextView textView;

    Records.OnRecordsDataListener frListener;

    public Records() {}

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
        if(context instanceof Begin.OnBeginDataListener) {
            frListener = (Records.OnRecordsDataListener) context;
        }
        else {
            throw new NullPointerException("Объект не создан на основе класса Begin");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.records, container, false);
        btn2 = view.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.root, new Menu_play());
                ftrans.commit();
            }
        });
        textView = view.findViewById(R.id.text2);
        DelayedPrinter.Word word = new DelayedPrinter.Word(50, "Игра 1"+Login.user.getHight_score()[0]);
        word.setOffset(50);
        DelayedPrinter.printText(word, textView);
        return view;
    }

    public interface OnRecordsDataListener {
        void onRecordsDataListener(String str);
    }
}
