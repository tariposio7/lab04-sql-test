package com.tariposio.lab04_sql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.TodoListView);
        TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
        todoListDAO.open();
        ArrayList<TodoList> mylist = todoListDAO.getAlltodoList();

        //ArrayAdapter<TodoList> adapter = new ArrayAdapter<TodoList>(getApplicationContext(),
        //       android.R.layout.simple_list_item_1, mylist);
        final MyListView adapter = new MyListView(this, mylist);

        listview.setAdapter(adapter);
        todoListDAO.close();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //    Toast.makeText(getApplicationContext()
              //          ,String.valueOf(adapter.getItemId(position))
                //        ,Toast.LENGTH_LONG)
                  //      .show();
                Intent editIntent = new Intent(getApplicationContext(),EditActivity.class);
                editIntent.putExtra("editTodoList",adapter.getItem(position));
                startActivity(editIntent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if (id == R.id.action_add_new_menu){

            Intent addNewIntent = new Intent(this,AddDataActivity.class);
            startActivity(addNewIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume(){
        super.onResume();
        TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
        todoListDAO.open();
        ArrayList<TodoList> mylist = todoListDAO.getAlltodoList();

        //ArrayAdapter<TodoList> adapter = new ArrayAdapter<TodoList>(getApplicationContext(),
        //       android.R.layout.simple_list_item_1, mylist);
        MyListView adapter = new MyListView(this, mylist);

        listview.setAdapter(adapter);
        todoListDAO.close();
    }
}
