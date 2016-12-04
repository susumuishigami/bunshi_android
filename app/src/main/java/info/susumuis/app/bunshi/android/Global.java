package info.susumuis.app.bunshi.android;

import info.susumuis.app.bunshi.Bunshi;
import info.susumuis.app.bunshi.ConfigValue;
import info.susumuis.app.bunshi.ScoreManager;

/**
 * Created by susumuis on 2015/06/08.
 */
public class Global {
    public static ConfigValue configValue = new ConfigValue();
    public static ScoreManager scoreManager;

    public static Bunshi startNewGame() {
        return new Bunshi(Bunshi.ENDLESS, configValue);
    }
    static {
        startNewGame();
    }
}
