package in.evoqis.retrofitexample;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    UsersAdapter usersAdapter;
    RecyclerView recyclerView;
    List<UserListResponse> userListResponseData;
    private boolean ascending = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getUserListData(); // call a method in which we have implement our GET type web API
    }

    private void getUserListData() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // getUsersList() is a method in API Interface class, in this method we define our API sub url
        Api.getClient().getUsersList(new Callback<List<UserListResponse>>() {
            @Override
            public void success(List<UserListResponse> userListResponses, Response response) {
                // in this method we will get the response from API
                progressDialog.dismiss(); //dismiss progress dialog
                userListResponseData = userListResponses;
                setDataInRecyclerView(); // call this method to set the data in adapter
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog

            }
        });
    }

   /* private void sortData(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
            Collections.sort(userListResponseData);
        else
        {
            Collections.reverse(userListResponseData);
        }

        //ADAPTER
        usersAdapter = new UsersAdapter(this, userListResponseData);
        recyclerView.setAdapter(usersAdapter);

    }*/


    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        usersAdapter = new UsersAdapter(this, userListResponseData);
        recyclerView.setAdapter(usersAdapter);
        /*sortData(ascending);
        ascending = !ascending;*/
        // call the constructor of UsersAdapter to send the reference and data to Adapter
       /* usersAdapter = new UsersAdapter(MainActivity.this, userListResponseData);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView*/
    }

/*
    private void sortData(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
            Collections.sort(userListResponseData);
        else
        {
            Collections.reverse(userListResponseData);
        }

        //ADAPTER
        usersAdapter = new UsersAdapter(this, userListResponseData);
        recyclerView.setAdapter(usersAdapter);

    }
*/
}