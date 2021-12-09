// 플래너, 2020111341, 원소희, 2021/12/09

package com.android.project2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.project2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("과제 2");

        ArrayList<String> midList= new ArrayList<>(); //항목리스트 선언
        ListView list = findViewById(R.id.listView1); //리스트뷰 정의

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,midList); //항목을 여러개 체크할 수 있도록
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(adapter);

        EditText editItem = findViewById(R.id.editItem); //에디트텍스트 정의
        Button btnAdd =findViewById(R.id.btnAdd);  //항목 추가 버튼 정의
        Button btnRm = findViewById(R.id.btnRm);  //항목 제거 버튼 정의
        TextView textView = findViewById(R.id.textview);//텍스트뷰 정의




        //항목추가버튼을 클릭했을 경우 정의
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            //에디트텍스트에서 입력받은 값을 받아 항목으로 추가
            public void onClick(View view) {
                midList.add(editItem.getText().toString());
                adapter.notifyDataSetChanged();
                textView.setText("오늘의 할 일: "+midList.size()+"개");//텍스트뷰로 나타낼 항목 개수 알려주기
            }
        });

        //항목을 길게 클릭했을 경우 정의
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            //해당 항목을 제거, 수고하셨어요! 문구를 토스트 메시지로 표시
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                midList.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "수고하셨어요!", Toast.LENGTH_SHORT).show();
                textView.setText("오늘의 할 일: "+midList.size()+"개"); //텍스트뷰로 나타낼 항목 개수 알려주기
                return false;
            }
        });



        //항목제거버튼을 클릭했을 경우 정의
        btnRm.setOnClickListener(new View.OnClickListener() {
            @Override
            //체크된 항목들을 제거, 수고하셨어요! 문구를 토스트 메시지로 표시
            public void onClick(View view) {
                SparseBooleanArray checkedItems = list.getCheckedItemPositions();
                int count = adapter.getCount() ;

                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        midList.remove(i) ;
                    }

                }

                list.clearChoices() ;
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "수고하셨어요!", Toast.LENGTH_SHORT).show();

                textView.setText("오늘의 할 일: "+midList.size()+"개");//텍스트뷰로 나타낼 항목 개수 알려주기
            }
        });
    }
}