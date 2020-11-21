package com.example.retrofitpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    EditText ui ;
     EditText ti ;
   EditText te ;
    JsonApiHolder jsonApiHolder;
    TextView tv ;
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
       ui = findViewById(R.id.ui);
        ti = findViewById(R.id.ti);
        te = findViewById(R.id.te);
        bt = findViewById(R.id.button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonApiHolder  = retrofit.create(JsonApiHolder.class);
//createPost();
 bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        createPost();
    }
});
    }
    public void createPost(){
        String uig = ui.getText().toString();
        int uif=Integer.parseInt(uig);
        String tig = ti.getText().toString();
        String teg = te.getText().toString();

        Post post = new Post(uif , tig, teg);
        Call<Post> call = jsonApiHolder.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tv.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";
                tv.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tv.setText(t.getMessage());

            }
        });

    }

}