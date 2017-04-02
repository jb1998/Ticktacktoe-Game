package me.jaxbot.ticktacktoe;

import android.content.Context;
import android.widget.ImageButton;

/**
 * Created by pc on 3/8/2017.
 */

public class MyImageButton extends ImageButton{
    private int player;
    public MyImageButton(Context context) {
        super(context);
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
