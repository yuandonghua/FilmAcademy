package starpark.filmacademy.view.tab;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by Administrator on 2016/6/22 0022.
 */

public class ViewUtilsLollipop {
    static void setBoundsViewOutlineProvider(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }
}
