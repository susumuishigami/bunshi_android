package info.susumuis.app.bunshi;/*------------------------------------------------------
 ファイル名  info.susumuis.app.bunshi.Molecule.java
 日付        2004/08/15
 作成者      Susumu ISHIGAMI
 -------------------------------------------------------*/

/**
 * 分子型
 * @author Susumu ISHIGAMI
 * @version 0.2.5-8/21
 */
public final class Molecule {
    public static final Molecule HYDROGEN = new Molecule(0,  "水素", 0, 20);
    public static final Molecule NITROGEN = new Molecule(1,  "窒素", 0, 80);
    public static final Molecule OXYGEN   = new Molecule(2,  "酸素", 0, 300);
    public static final Molecule FLORIDE  = new Molecule(3,  "弗素", 0, -50);
    public static final Molecule CHLORINE = new Molecule(4,  "塩素", 0, -20);
    
    public static final Molecule CO       = new Molecule(5,  "一酸化炭素", 0, -70);
    public static final Molecule NO       = new Molecule(6,  "一酸化窒素", 0, -50);
    public static final Molecule SO       = new Molecule(7,  "一酸化硫黄", 0, -30);
    public static final Molecule CLO      = new Molecule(8,  "一酸化塩素", 0, -50);
    public static final Molecule HF       = new Molecule(9,  "弗化水素", 0, -40);
    
    public static final Molecule CLF      = new Molecule(10, "弗化塩素", 0, -40);
    public static final Molecule CS       = new Molecule(11, "一硫化炭素", 0, -20);
    public static final Molecule HCL      = new Molecule(12, "塩化水素", 0, -40);
    // 3つ以上---------------------------------------------------------------------
    public static final Molecule CO2      = new Molecule(13, "二酸化炭素", 100, -5);
    public static final Molecule NO2      = new Molecule(14, "二酸化窒素", 100, -60);
    
    public static final Molecule SO2      = new Molecule(15, "二酸化硫黄", 100, -50);
    public static final Molecule CLO2     = new Molecule(16, "二酸化塩素", 100, -50);
    public static final Molecule OZONE    = new Molecule(17, "オゾン", 100, -40);
    public static final Molecule WATER    = new Molecule(18, "水蒸気", 100, 400);
    public static final Molecule F2O      = new Molecule(19, "酸化弗素", 100, -60);
    
    public static final Molecule CL2O     = new Molecule(20, "一酸化二塩素", 100, -20);
    public static final Molecule SCL2     = new Molecule(21, "二塩化硫黄", 100, -40);
    public static final Molecule H2S      = new Molecule(22, "硫化水素", 100, -100);
    public static final Molecule CS2      = new Molecule(23, "二硫化炭素", 100, -40);
    public static final Molecule HCN      = new Molecule(24, "シアン化水素", 100, -100);
    
    public static final Molecule CNF      = new Molecule(25, "弗化シアン", 100, -100);
    public static final Molecule CNCL     = new Molecule(26, "塩化シアン", 100, -100);
    public static final Molecule HCLO     = new Molecule(27, "次亜塩素酸", 100, -30);
    // 4つ以上---------------------------------------------------------------------
    public static final Molecule AMMONIA  = new Molecule(28, "アンモニア", 300, -30);
    public static final Molecule SO3      = new Molecule(29, "三酸化硫黄", 300, -30);
    
    public static final Molecule NO3      = new Molecule(30, "三酸化窒素", 300, -10);
    public static final Molecule NCL3     = new Molecule(31, "塩化窒素", 300, -20);
    public static final Molecule METHANAL = new Molecule(32, "ホルムアルデヒド", 300, -20);
    public static final Molecule H2O2     = new Molecule(33, "過酸化水素", 300, -10);
    public static final Molecule S2CL2    = new Molecule(34, "一塩化硫黄", 300, -30);
    
    public static final Molecule HNO2     = new Molecule(35, "亜硝酸", 300, -20);
    public static final Molecule ETHINE   = new Molecule(36, "アセチレン", 300, 0);
    public static final Molecule CYAN     = new Molecule(37, "シアン", 300, -50);
    // 5つ以上---------------------------------------------------------------------
    public static final Molecule METHANE  = new Molecule(38, "メタン", 1000, -10);
    public static final Molecule CMETHANE = new Molecule(39, "クロロメタン", 1000, -10);
    
    public static final Molecule C2METHANE= new Molecule(40, "ジクロロメタン", 1000, -20);
    public static final Molecule C3METHANE= new Molecule(41, "トリクロロメタン", 1000, -20);
    public static final Molecule C4METHANE= new Molecule(42, "テトラクロロメタン", 1000, -20);
    public static final Molecule FRON11   = new Molecule(43, "フロン11", 1000, -20);
    public static final Molecule FRON12   = new Molecule(44, "フロン12", 1000, -20);
    
    public static final Molecule FRON13   = new Molecule(45, "フロン13", 1000, -20);
    public static final Molecule FRON14   = new Molecule(46, "フロン14", 1000, -20);
    public static final Molecule FRON21   = new Molecule(47, "フロン21", 1000, -20);
    public static final Molecule FRON22   = new Molecule(48, "フロン22", 1000, -20);
    public static final Molecule FRON23   = new Molecule(49, "フロン23", 1000, -20);
    
    public static final Molecule FRON31   = new Molecule(50, "フロン31", 1000, -20);
    public static final Molecule FRON32   = new Molecule(51, "フロン32", 1000, -20);
    public static final Molecule FRON41   = new Molecule(52, "フロン41", 1000, -20);
    // 6つ以上---------------------------------------------------------------------
    public static final Molecule METHANOL = new Molecule(53, "メタノール", 3000, -20);
    public static final Molecule FORMICACID = new Molecule(54, "蟻酸", 3000, -20);
    
    public static final Molecule NITRICACID = new Molecule(55, "硝酸", 3000, -20);
    public static final Molecule ACETICACID = new Molecule(56, "酢酸", 5000, -20);
    public static final Molecule NUCLEAR    = new Molecule(57, "水素核融合", 300, 0);
    
    
    
    
    public static final Molecule[] MOLECULES = {
        HYDROGEN, NITROGEN, OXYGEN, FLORIDE, CHLORINE,
        CO, NO, SO, CLO, HF, CLF, CS, HCL,
        
        CO2, NO2, SO2, CLO2, OZONE, WATER,
        F2O, CL2O, SCL2, H2S, CS2, HCN, CNF, CNCL, HCLO,
        
        AMMONIA, SO3, NO3, NCL3, METHANAL, H2O2,
        S2CL2, HNO2, ETHINE, CYAN,
        
        METHANE, CMETHANE, C2METHANE, C3METHANE, C4METHANE,
        FRON11, FRON12, FRON13, FRON14, FRON21, FRON22,
        FRON23, FRON31, FRON32, FRON41,
        
        METHANOL, FORMICACID, NITRICACID, ACETICACID, NUCLEAR
    };
    
    public static final Molecule[] STABLES = {
        HYDROGEN, NITROGEN, OXYGEN, FLORIDE, CHLORINE,
        SO, HF, CLF, HCL,
        
        CO2, NO2, SO2, WATER,
        F2O, CL2O, SCL2, H2S, CS2, HCN, CNCL, HCLO,
        
        AMMONIA, NCL3, METHANAL,
        HNO2, ETHINE, CYAN,
        
        METHANE, CMETHANE, C2METHANE, C3METHANE, C4METHANE,
        FRON11, FRON12, FRON13, FRON14, FRON21, FRON22,
        FRON23, FRON31, FRON32, FRON41,
        
        METHANOL, FORMICACID, NITRICACID, ACETICACID
    };
    
    public static final Molecule[] JUNIORS = {
        HYDROGEN, NITROGEN, OXYGEN, FLORIDE, CHLORINE,
        CO, HCL,
        
        CO2, NO2, SO2, OZONE, WATER,
        H2S,
        
        AMMONIA, H2O2
    };
    
    public static final Molecule[] OVER3S = {
        CO2, NO2, SO2, CLO2, OZONE, WATER,
        F2O, CL2O, SCL2, H2S, CS2, HCN, CNF, CNCL, HCLO,
        
        AMMONIA, SO3, NO3, NCL3, METHANAL, H2O2,
        S2CL2, HNO2, ETHINE, CYAN,
        
        METHANE, CMETHANE, C2METHANE, C3METHANE, C4METHANE,
        FRON11, FRON12, FRON13, FRON14, FRON21, FRON22,
        FRON23, FRON31, FRON32, FRON41,
        
        METHANOL, FORMICACID, NITRICACID, ACETICACID, NUCLEAR
    };
    
    private String name_;
    private int id_;
    private int bonus_;
    private int speedEffect_;
    
    public Molecule(int id, String name, int bonus, int speedEffect) {
        id_ = id;
        name_ = name;
        bonus_ = bonus;
        speedEffect_ = speedEffect;
    }
    
    public int getSpeedEffect() {
        return speedEffect_;
    }

    public int getId() {
        return id_;
    }
    
    public String getName() {
        return name_;
    }
    
    public int getBonus() {
        return bonus_;
    }
    
    
}
