package com.tariposio.lab04_sql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final TodoList editTodolist = (TodoList) getIntent().getSerializableExtra("editTodoList");

        final EditText editText = (EditText) findViewById(R.id.et_edit);
        editText.setText(editTodolist.getTaskname());

        Button btnEdt =(Button) findViewById(R.id.btn_edit);
        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList eTodoList = new TodoList();
                eTodoList.setTaskid(editTodolist.getTaskid());
                eTodoList.setTaskname(String.valueOf(editText.getText()));

                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.update(eTodoList);
                todoListDAO.close();
                finish();
            }
        });
        Button btdDel =(Button)findViewById(R.id.btn_delete);
        btdDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList delTodoList = new TodoList();
                delTodoList.setTaskid(editTodolist.getTaskid());

                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.delete(delTodoList);
                todoListDAO.close();
                finish();
                onResume();
            }
        });
    }
}
