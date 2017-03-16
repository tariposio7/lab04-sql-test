package com.tariposio.lab04_sql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        final EditText newTodoListText = (EditText) findViewById(R.id.editTextAddTask);
        final Button newTodoListBtn = (Button) findViewById(R.id.btnAddNewTask);
        newTodoListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList todoList = new TodoList();
                todoList.setTaskname(String.valueOf(newTodoListText.getText()));
                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.add(todoList);
                todoListDAO.close();
                finish();
                onResume();
            }
        });
    }
}
