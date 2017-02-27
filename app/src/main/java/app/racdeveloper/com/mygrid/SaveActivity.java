package app.racdeveloper.com.mygrid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Button;

/**
 * Created by user on 26-06-2016.
 */
public class SaveActivity extends AppCompatActivity {

    TextView tvStatus;
    Button save, bStatus;
    GridView grid;
    ArrayAdapter<String> ad;
    static int[] player1;
    static int[] player2;
    int player=1;
    int width=5;
    static String[] gridData;
    int count = 5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        tvStatus= (TextView) findViewById(R.id.tvStatus);
        bStatus= (Button) findViewById(R.id.bStatus);
        save= (Button) findViewById(R.id.bSave);
        grid= (GridView) findViewById(R.id.grid);
        player1= new int[width*width+1];
        player2= new int[width*width+1];
        MainActiviy.set=1;

        gridData= new String[width*width];
        for(int i = 0; i<(width*width); i++){
            gridData[i]= new String(""+(i+1));
        }

        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gridData){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = null;
                if(parent==grid){
                    view=super.getView(position,convertView,parent);
                    view.setBackgroundColor(Color.WHITE);
                }
                return view;
            }
        };
        grid.setAdapter(ad);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (count > 0) {
                    Drawable img = getBaseContext().getResources().getDrawable(R.drawable.ic_launcher);
                    img.setBounds(0, 0, 120, 120);
                    view.setBackground(img);
                    if(player==1) {if(player1[position]==0){player1[position]=1;count--;}}
                    else if(player==2) {if(player2[position]==0) {player2[position]=1;count--;}}
                }
            }
        });
        bStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player==1){
                    grid.setAdapter(ad);
                    tvStatus.setText("2nd Player");
                    save.setText("Save");
                    bStatus.setText("Done!!");
                    count=5;
                    player=2;}
                else if(player==2){
                    Intent i= new Intent(SaveActivity.this, MainActiviy.class);
                    startActivity(i);
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setText("Saved");

            }
        });
    }
}