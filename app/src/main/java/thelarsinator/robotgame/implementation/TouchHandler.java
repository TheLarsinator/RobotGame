package thelarsinator.robotgame.implementation;

/**
 * Created by Lars on 15.07.2016.
 */
import java.util.List;

import android.view.View.OnTouchListener;

import thelarsinator.robotgame.framework.Input;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<Input.TouchEvent> getTouchEvents();
}