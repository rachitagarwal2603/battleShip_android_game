package app.racdeveloper.com.mygrid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActiviy extends AppCompatActivity {

    GridView grid, grid2;
    Button setBattles,play,change;
    LinearLayout llplay;
    TextView tvStatus,tvhitmiss,tvscore,tvResult,tvturn;
    int width=5;
    int[] play1;
    int[] play2;
    int player=1,turn=1;
    static String[] gridData;
    static int set=0;
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activiy);

        initialise();

        grid2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(turn==1) {
                    if (player == 1) {
                        if (play2[position] == 1) {
                            play2[position] = 0;
                            play1[width * width]++;
                            tvhitmiss.setText("HIT!!");
                        } else
                            tvhitmiss.setText("MISS!!");
                    } else if (player == 2) {
                        if (play1[position] == 1) {
                            play1[position] = 0;
                            play2[width * width]++;
                            tvhitmiss.setText("HIT!!");
                        } else
                            tvhitmiss.setText("MISS!!");
                    }
                    tvscore.setText(play1[width * width] + " - " + play2[width * width]);
                    if (play1[width * width] == 5 || play2[width * width] == 5) {
                        change.setVisibility(View.INVISIBLE);
                        grid.setVisibility(View.INVISIBLE);
                        grid2.setVisibility(View.INVISIBLE);
                        llplay.setVisibility(View.INVISIBLE);
                        tvturn.setVisibility(View.INVISIBLE);
                        tvResult.setVisibility(View.VISIBLE);
                        if (play1[width * width] == 5)
                            tvResult.setText("Player1 has WON!!!!");
                        else
                            tvResult.setText("Player2 has WON!!!!");
                    }
                    turn--;
                }
            }
        });

        setBattles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActiviy.this,SaveActivity.class);
                startActivity(i);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(set==0)
                    Toast.makeText(MainActiviy.this, "First set your BattleShips", Toast.LENGTH_SHORT).show();
                else {
                    tvStatus.setText("1st Player");
                    tvturn.setText("Turn");
                    llplay.setVisibility(View.VISIBLE);
                    setBattles.setVisibility(View.INVISIBLE);
                    tvscore.setText(play1[width*width]+" - "+play2[width*width]);
                    play.setVisibility(View.INVISIBLE);
                    setDisplay();
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn=1;
                if (set == 0)
                    Toast.makeText(MainActiviy.this, "First start Play!!", Toast.LENGTH_SHORT).show();
                else {
                    if (player == 1) {
                        player = 2;
                        tvhitmiss.setText("");
                        tvStatus.setText("2nd Player");
                        setDisplay();
                    } else if (player == 2) {
                        player = 1;
                        tvStatus.setText("1st Player");
                        tvhitmiss.setText("");
                        setDisplay();
                    }
                }
            }
        });
    }


    private void setDisplay() {

        if(player==1) {
            for (int i = 0; i < width * width; i++)
                if (play1[i] == 1) {
                    View view = grid.getChildAt(i);
                    Drawable img = getBaseContext().getResources().getDrawable(R.drawable.ic_launcher);
                    img.setBounds(0, 0, 120, 120);
                    view.setBackground(img);
                }
                else if(play1[i]==0) {
                    View view = grid.getChildAt(i);
                    view.setBackgroundColor(Color.WHITE);
                }
        }
        else if(player==2) {
            for (int i = 0; i < width * width; i++)
                if (play2[i] == 1) {
                    View view = grid.getChildAt(i);
                    Drawable img = getBaseContext().getResources().getDrawable(R.drawable.ic_launcher);
                    img.setBounds(0, 0, 120, 120);
                    view.setBackground(img);
                }
                else if(play2[i]==0) {
                    View view = grid.getChildAt(i);
                    view.setBackgroundColor(Color.WHITE);
                }
        }
    }

    private void initialise() {
        grid = (GridView) findViewById(R.id.gridView);
        grid.setColumnWidth(width);
        grid2= (GridView) findViewById(R.id.gridView2);
        grid2.setColumnWidth(width);
        setBattles= (Button) findViewById(R.id.bSet);
        play= (Button) findViewById(R.id.bPlay);
        change= (Button) findViewById(R.id.bChange);
        tvStatus= (TextView) findViewById(R.id.tvStatus);
        tvStatus.setText("");
        tvhitmiss= (TextView) findViewById(R.id.tvHitMiss);
        tvscore= (TextView) findViewById(R.id.tvScore);
        tvResult= (TextView) findViewById(R.id.tvResult);
        tvturn= (TextView) findViewById(R.id.textView2);
        tvturn.setText("SR Battle Ships");
        llplay= (LinearLayout) findViewById(R.id.llPlay);

        play1=new int[width*width+1];
        play1= SaveActivity.player1;
        play2=new int[width*width+1];
        play2= SaveActivity.player2;

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
                else if(parent==grid2){
                    view=super.getView(position,convertView,parent);
                    view.setBackgroundColor(Color.WHITE);
                }
                return view;
            }
        };
        grid.setAdapter(ad);
        grid2.setAdapter(ad);
    }

}