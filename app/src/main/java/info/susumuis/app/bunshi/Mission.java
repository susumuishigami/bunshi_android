package info.susumuis.app.bunshi;/*------------------------------------------------------
 ファイル名  info.susumuis.app.bunshi.Mission.java
 日付        2004/08/22
 作成者      Susumu ISHIGAMI
 -------------------------------------------------------*/

import java.util.*;

/**
 * 指令を管理するクラス。
 * @author Susumu ISHIGAMI
 * @version 0.3.2-8/23
 */
public class Mission {
    
    public static final byte ALLELACE = 0;
    public static final byte SERIES   = 1;
    public static final byte GENERATE = 2;
    
    // 指令
    private static final String[] mission_str = {
            "全消しスベシ",
            "連鎖スベシ",
            "ヲ発生サセヨ"
    };
    
    private byte id_;
    private byte param_;
    private ConfigValue configValue_;
    private Random rnd_;
    
    public Mission(Random rnd, ConfigValue configValue) {
        rnd_ = rnd;
        configValue_ = configValue;
        setNextMission();
    }
    
    public byte getId() {
        return id_;
    }
    
    public byte getParam() {
        return param_;
    }
    
    public String toString() {
        switch (id_) {
        	case ALLELACE: return mission_str[id_];
        	case SERIES  : return param_ + mission_str[id_];
        	case GENERATE:
        	    if (param_ >= Molecule.CMETHANE.getId() && param_ <= Molecule.C4METHANE.getId())
        	        return "クロロメタン系" + "\n" + mission_str[id_];
        	    else if (param_ >= Molecule.FRON11.getId() && param_ <= Molecule.FRON41.getId())
        	        return "フロンガス類" + "\n" + mission_str[id_];
        	    else
        	        return Molecule.MOLECULES[param_].getName() + "\n" + mission_str[id_];
        	default:
        	    return "error: id=" + id_ + " param=" + param_;
        }
    }
    
    private void setNextMission() {
	    id_ = (byte) Math.abs(rnd_.nextInt() % mission_str.length);
	    switch (id_) {
	    	case SERIES:
	    	    param_ = (byte) (Math.abs(rnd_.nextInt() % 5) + 5);
	    	    break;
	    	case GENERATE:
	    	    param_ = (byte) (Math.abs(rnd_.nextInt()) % (Molecule.NITRICACID.getId()
	    	            - Molecule.CO2.getId()) + Molecule.CO2.getId());
	    	    if (!configValue_.isGeneratable(param_)) setNextMission(); // 再帰呼び出しでやり直し。
	    	    break;
	    }
	}
    
    public void success() {
        setNextMission();
    }
}
