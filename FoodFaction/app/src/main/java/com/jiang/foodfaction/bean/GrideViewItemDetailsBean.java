package com.jiang.foodfaction.bean;

import java.util.List;

/**
 * Created by dllo on 17/2/27.
 */

public class GrideViewItemDetailsBean {


    /**
     * id : 56
     * code : mifan_zheng
     * name : 米饭
     * calory : 116
     * weight : 100
     * health_light : 2
     * is_liquid : false
     * thumb_image_url : http://s.boohee.cn/house/food_mid/mid_photo_201512613144756.jpg
     * large_image_url : http://s.boohee.cn/house/food_big/big_photo201512613144756.jpg
     * uploader :
     * appraise : 一种碳水化合物和水分含量都较高的主食，但它是身体所需热量的主要来源之一，可占全天热量的55%左右，减肥期间适量食用。
     * protein : 2.6
     * fat : 0.3
     * fiber_dietary : 0.3
     * carbohydrate : 25.6
     * gi : 83.2
     * gl : 21.5
     * compare : {}
     * recipe : false
     * recipe_type : null
     * units : [{"unit_id":207,"amount":"1.0","unit":"两 食堂的","weight":"145.0","eat_weight":"145.0","calory":"168.2"},{"unit_id":233,"amount":"1.0","unit":"勺","weight":"40.0","eat_weight":"40.0","calory":"46.4"},{"unit_id":134,"amount":"1.0","unit":"盒（快餐饭盒）","weight":"280.0","eat_weight":"280.0","calory":"324.8"},{"unit_id":980,"amount":"1.0","unit":"碗","weight":"200.0","eat_weight":"200.0","calory":"232.0"},{"unit_id":49,"amount":"1.0","unit":"碗(小乐扣)","weight":"100.0","eat_weight":"100.0","calory":"116.0"},{"unit_id":132,"amount":"1.0","unit":"碗（小碗）","weight":"150.0","eat_weight":"150.0","calory":"174.0"}]
     * ingredient : {"calory":"116.0","protein":"2.6","fat":"0.3","carbohydrate":"25.6","fiber_dietary":"0.3","vitamin_a":"","vitamin_c":"","vitamin_e":"","carotene":"","thiamine":"0.0","lactoflavin":"0.0","niacin":"1.9","cholesterol":"","magnesium":"15.0","calcium":"7.0","iron":"1.3","zinc":"0.9","copper":"0.1","manganese":"0.6","kalium":"30.0","phosphor":"62.0","natrium":"2.5","selenium":"0.4"}
     * lights : {"calory":"低热量","protein":"","carbohydrate":"","fat":"低脂肪","fiber_dietary":"","gi":"高GI","gl":"高GL"}
     * comments_ct : 124
     * health_appraise : [{"health_mode":0,"show":1,"light":2,"appraise":"一种碳水化合物和水分含量都较高的主食，但它是身体所需热量的主要来源之一，可占全天热量的55%左右，减肥期间适量食用。"},{"health_mode":1,"show":1,"light":2,"appraise":"一种碳水化合物和水分含量都较高的主食，但它是身体所需热量的主要来源之一，可占全天热量的55%左右，减肥期间适量食用。"}]
     */

    private int id;
    private String code;
    private String name;
    private String calory;
    private String weight;
    private int health_light;
    private boolean is_liquid;
    private String thumb_image_url;
    private String large_image_url;
    private String uploader;
    private String appraise;
    private String protein;
    private String fat;
    private String fiber_dietary;
    private String carbohydrate;
    private String gi;
    private String gl;
    private CompareBean compare;
    private boolean recipe;
    private Object recipe_type;
    private IngredientBean ingredient;
    private LightsBean lights;
    private int comments_ct;
    private List<UnitsBean> units;
    private List<HealthAppraiseBean> health_appraise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getHealth_light() {
        return health_light;
    }

    public void setHealth_light(int health_light) {
        this.health_light = health_light;
    }

    public boolean isIs_liquid() {
        return is_liquid;
    }

    public void setIs_liquid(boolean is_liquid) {
        this.is_liquid = is_liquid;
    }

    public String getThumb_image_url() {
        return thumb_image_url;
    }

    public void setThumb_image_url(String thumb_image_url) {
        this.thumb_image_url = thumb_image_url;
    }

    public String getLarge_image_url() {
        return large_image_url;
    }

    public void setLarge_image_url(String large_image_url) {
        this.large_image_url = large_image_url;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getFiber_dietary() {
        return fiber_dietary;
    }

    public void setFiber_dietary(String fiber_dietary) {
        this.fiber_dietary = fiber_dietary;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getGi() {
        return gi;
    }

    public void setGi(String gi) {
        this.gi = gi;
    }

    public String getGl() {
        return gl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public CompareBean getCompare() {
        return compare;
    }

    public void setCompare(CompareBean compare) {
        this.compare = compare;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }

    public Object getRecipe_type() {
        return recipe_type;
    }

    public void setRecipe_type(Object recipe_type) {
        this.recipe_type = recipe_type;
    }

    public IngredientBean getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientBean ingredient) {
        this.ingredient = ingredient;
    }

    public LightsBean getLights() {
        return lights;
    }

    public void setLights(LightsBean lights) {
        this.lights = lights;
    }

    public int getComments_ct() {
        return comments_ct;
    }

    public void setComments_ct(int comments_ct) {
        this.comments_ct = comments_ct;
    }

    public List<UnitsBean> getUnits() {
        return units;
    }

    public void setUnits(List<UnitsBean> units) {
        this.units = units;
    }

    public List<HealthAppraiseBean> getHealth_appraise() {
        return health_appraise;
    }

    public void setHealth_appraise(List<HealthAppraiseBean> health_appraise) {
        this.health_appraise = health_appraise;
    }

    public static class CompareBean {
    }

    public static class IngredientBean {
        /**
         * calory : 116.0
         * protein : 2.6
         * fat : 0.3
         * carbohydrate : 25.6
         * fiber_dietary : 0.3
         * vitamin_a :
         * vitamin_c :
         * vitamin_e :
         * carotene :
         * thiamine : 0.0
         * lactoflavin : 0.0
         * niacin : 1.9
         * cholesterol :
         * magnesium : 15.0
         * calcium : 7.0
         * iron : 1.3
         * zinc : 0.9
         * copper : 0.1
         * manganese : 0.6
         * kalium : 30.0
         * phosphor : 62.0
         * natrium : 2.5
         * selenium : 0.4
         */

        private String calory;
        private String protein;
        private String fat;
        private String carbohydrate;
        private String fiber_dietary;
        private String vitamin_a;
        private String vitamin_c;
        private String vitamin_e;
        private String carotene;
        private String thiamine;
        private String lactoflavin;
        private String niacin;
        private String cholesterol;
        private String magnesium;
        private String calcium;
        private String iron;
        private String zinc;
        private String copper;
        private String manganese;
        private String kalium;
        private String phosphor;
        private String natrium;
        private String selenium;

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public String getFiber_dietary() {
            return fiber_dietary;
        }

        public void setFiber_dietary(String fiber_dietary) {
            this.fiber_dietary = fiber_dietary;
        }

        public String getVitamin_a() {
            return vitamin_a;
        }

        public void setVitamin_a(String vitamin_a) {
            this.vitamin_a = vitamin_a;
        }

        public String getVitamin_c() {
            return vitamin_c;
        }

        public void setVitamin_c(String vitamin_c) {
            this.vitamin_c = vitamin_c;
        }

        public String getVitamin_e() {
            return vitamin_e;
        }

        public void setVitamin_e(String vitamin_e) {
            this.vitamin_e = vitamin_e;
        }

        public String getCarotene() {
            return carotene;
        }

        public void setCarotene(String carotene) {
            this.carotene = carotene;
        }

        public String getThiamine() {
            return thiamine;
        }

        public void setThiamine(String thiamine) {
            this.thiamine = thiamine;
        }

        public String getLactoflavin() {
            return lactoflavin;
        }

        public void setLactoflavin(String lactoflavin) {
            this.lactoflavin = lactoflavin;
        }

        public String getNiacin() {
            return niacin;
        }

        public void setNiacin(String niacin) {
            this.niacin = niacin;
        }

        public String getCholesterol() {
            return cholesterol;
        }

        public void setCholesterol(String cholesterol) {
            this.cholesterol = cholesterol;
        }

        public String getMagnesium() {
            return magnesium;
        }

        public void setMagnesium(String magnesium) {
            this.magnesium = magnesium;
        }

        public String getCalcium() {
            return calcium;
        }

        public void setCalcium(String calcium) {
            this.calcium = calcium;
        }

        public String getIron() {
            return iron;
        }

        public void setIron(String iron) {
            this.iron = iron;
        }

        public String getZinc() {
            return zinc;
        }

        public void setZinc(String zinc) {
            this.zinc = zinc;
        }

        public String getCopper() {
            return copper;
        }

        public void setCopper(String copper) {
            this.copper = copper;
        }

        public String getManganese() {
            return manganese;
        }

        public void setManganese(String manganese) {
            this.manganese = manganese;
        }

        public String getKalium() {
            return kalium;
        }

        public void setKalium(String kalium) {
            this.kalium = kalium;
        }

        public String getPhosphor() {
            return phosphor;
        }

        public void setPhosphor(String phosphor) {
            this.phosphor = phosphor;
        }

        public String getNatrium() {
            return natrium;
        }

        public void setNatrium(String natrium) {
            this.natrium = natrium;
        }

        public String getSelenium() {
            return selenium;
        }

        public void setSelenium(String selenium) {
            this.selenium = selenium;
        }
    }

    public static class LightsBean {
        /**
         * calory : 低热量
         * protein :
         * carbohydrate :
         * fat : 低脂肪
         * fiber_dietary :
         * gi : 高GI
         * gl : 高GL
         */

        private String calory;
        private String protein;
        private String carbohydrate;
        private String fat;
        private String fiber_dietary;
        private String gi;
        private String gl;

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getFiber_dietary() {
            return fiber_dietary;
        }

        public void setFiber_dietary(String fiber_dietary) {
            this.fiber_dietary = fiber_dietary;
        }

        public String getGi() {
            return gi;
        }

        public void setGi(String gi) {
            this.gi = gi;
        }

        public String getGl() {
            return gl;
        }

        public void setGl(String gl) {
            this.gl = gl;
        }
    }

    public static class UnitsBean {
        /**
         * unit_id : 207
         * amount : 1.0
         * unit : 两 食堂的
         * weight : 145.0
         * eat_weight : 145.0
         * calory : 168.2
         */

        private int unit_id;
        private String amount;
        private String unit;
        private String weight;
        private String eat_weight;
        private String calory;

        public int getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(int unit_id) {
            this.unit_id = unit_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getEat_weight() {
            return eat_weight;
        }

        public void setEat_weight(String eat_weight) {
            this.eat_weight = eat_weight;
        }

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }
    }

    public static class HealthAppraiseBean {
        /**
         * health_mode : 0
         * show : 1
         * light : 2
         * appraise : 一种碳水化合物和水分含量都较高的主食，但它是身体所需热量的主要来源之一，可占全天热量的55%左右，减肥期间适量食用。
         */

        private int health_mode;
        private int show;
        private int light;
        private String appraise;

        public int getHealth_mode() {
            return health_mode;
        }

        public void setHealth_mode(int health_mode) {
            this.health_mode = health_mode;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public int getLight() {
            return light;
        }

        public void setLight(int light) {
            this.light = light;
        }

        public String getAppraise() {
            return appraise;
        }

        public void setAppraise(String appraise) {
            this.appraise = appraise;
        }
    }
}
