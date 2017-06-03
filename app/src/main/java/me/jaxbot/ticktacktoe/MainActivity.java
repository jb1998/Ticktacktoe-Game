package me.jaxbot.ticktacktoe;



import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    me.jaxbot.ticktacktoe.MyButton buttons[][];
      me.jaxbot.ticktacktoe.MyImageButton buttons[][];
    LinearLayout rows[];
    LinearLayout mainLayout;
    public final static int NO_PLAYER = 0;
    public final static int PLAYER1 = 1;
    public final static int PLAYER2 = 2;
    public final static int PLAYER_1_WINS = 1;
    public final static int PLAYER_2_WINS = 2;
    public final static int DRAW = 3;
    public final static int INCOMPLETE = 4;
    int n = 4;
    boolean player1Turn;
    boolean gameOver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.activity_main);
        Intent i = getIntent();

      //  String name = i.getStringExtra(IntentConstants.PLAYER_NAME);
//        if (name != null) {
      //  Toast.makeText(this, "Player Name : " + name, Toast.LENGTH_SHORT).show();
//
        setUpBoard();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.newGame){

            resetBoard();
        }else if(id == R.id.boardSize_3){

            if(n != 3) {
                n = 3;
                setUpBoard();
            }
            else{
                resetBoard();
            }
        }else if(id == R.id.boardSize_4){
            n = 4;
            setUpBoard();
        }else if(id == R.id.boardSize_5){
            n = 5;
            setUpBoard();
        }
        return true;
    }

    private void resetBoard() {

        player1Turn = true;
        gameOver = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j].setImageResource(R.drawable.tick1);
                buttons[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP);
                buttons[i][j].setPlayer(NO_PLAYER);
            }
        }
    }


    private void setUpBoard() {
        player1Turn = true;
        buttons = new me.jaxbot.ticktacktoe.MyImageButton[n][n];
        rows = new LinearLayout[n];
        gameOver = false;
        mainLayout.removeAllViews();

        for (int i = 0; i < n; i++) {
            rows[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            params.setMargins(5, 5, 5, 5);
            rows[i].setLayoutParams(params);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rows[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new me.jaxbot.ticktacktoe.MyImageButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(5, 5, 5, 5);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setImageResource(R.drawable.tick1);
                buttons[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP);
                buttons[i][j].setPlayer(NO_PLAYER);
                buttons[i][j].setOnClickListener(this);
                rows[i].addView(buttons[i][j]);
            }
        }


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        me.jaxbot.ticktacktoe.MyImageButton b = (me.jaxbot.ticktacktoe.MyImageButton) v;

        if(gameOver){
            return;
        }

        if (b.getPlayer() != NO_PLAYER) {
//         Toast t =
            Toast.makeText(this, "Invalid Move !!", Toast.LENGTH_SHORT).show();
//         t.show();
            return;
        }
        if (player1Turn) {
            b.setPlayer(PLAYER1);
            b.setImageResource(R.drawable.tick1a);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            b.setPlayer(PLAYER2);
            b.setImageResource(R.drawable.tick2aa);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        int status = gameStatus();
        if(status == PLAYER_1_WINS || status == PLAYER_2_WINS){
            Toast.makeText(this, "Player "+status+" wins !!", Toast.LENGTH_SHORT).show();
            gameOver = true;
            return;
        }

        if(status == DRAW){
            Toast.makeText(this, "Draw !!", Toast.LENGTH_SHORT).show();
            gameOver = true;
            return;
        }
        player1Turn = !player1Turn;

    }

    private int gameStatus() {


        // Rows
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (buttons[i][j].getPlayer() == NO_PLAYER || buttons[i][0].getPlayer() != buttons[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (buttons[i][0].getPlayer() == PLAYER1) {
                    return PLAYER_1_WINS;
                } else {
                    return PLAYER_2_WINS;
                }
            }

        }

// Columns
        for (int j = 0; j < n; j++) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (buttons[i][j].getPlayer() == NO_PLAYER || buttons[0][j].getPlayer() != buttons[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (buttons[0][j].getPlayer() == PLAYER1) {
                    return PLAYER_1_WINS;
                } else {
                    return PLAYER_2_WINS;
                }
            }

        }

        // Diagonal 1
        boolean flag = true;
        for(int i = 0; i < n; i++){
            if (buttons[i][i].getPlayer() == NO_PLAYER || buttons[0][0].getPlayer() != buttons[i][i].getPlayer()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (buttons[0][0].getPlayer() == PLAYER1) {
                return PLAYER_1_WINS;
            } else {
                return PLAYER_2_WINS;
            }
        }

        // Diagonal 2
        flag = true;
        for(int i = n - 1; i >= 0; i--){
            int col = n - 1 - i;
            if (buttons[i][col].getPlayer() == NO_PLAYER || buttons[n - 1][0].getPlayer() != buttons[i][col].getPlayer()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (buttons[n - 1][0].getPlayer() == PLAYER1) {
                return PLAYER_1_WINS;
            } else {
                return PLAYER_2_WINS;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(buttons[i][j].getPlayer() == NO_PLAYER){
                    return INCOMPLETE;
                }
            }
        }
        return DRAW;
    }




}
