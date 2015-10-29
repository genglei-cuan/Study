package cn.steve.gson;

import java.util.List;

/**
 * Created by yantinggeng on 2015/10/14.
 */
public class WeatherData {


    /**
     * city : {"aqi":"34","co":"0","no2":"11","o3":"63","pm10":"33","pm25":"7","qlty":"优","so2":"2"}
     */

    private AqiEntity aqi;
    /**
     * city : 北京 cnty : 中国 id : CN101010100 lat : 39.904000 lon : 116.391000 update :
     * {"loc":"2015-10-29 16:49","utc":"2015-10-29 08:49"}
     */

    private BasicEntity basic;
    /**
     * cond : {"code":"100","txt":"晴"} fl : 10 hum : 18 pcpn : 0 pres : 1025 tmp : 12 vis : 10 wind
     * : {"deg":"280","dir":"西北风","sc":"4-5","spd":"31"}
     */

    private NowEntity now;
    /**
     * aqi : {"city":{"aqi":"34","co":"0","no2":"11","o3":"63","pm10":"33","pm25":"7","qlty":"优","so2":"2"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2015-10-29
     * 16:49","utc":"2015-10-29 08:49"}} daily_forecast : [{"astro":{"sr":"06:39","ss":"17:16"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2015-10-29","hum":"16","pcpn":"0.0","pop":"0","pres":"1028","tmp":{"max":"12","min":"2"},"vis":"10","wind":{"deg":"319","dir":"北风","sc":"4-5","spd":"22"}},{"astro":{"sr":"06:40","ss":"17:15"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2015-10-30","hum":"19","pcpn":"0.0","pop":"0","pres":"1032","tmp":{"max":"13","min":"1"},"vis":"10","wind":{"deg":"310","dir":"无持续风向","sc":"微风","spd":"22"}},{"astro":{"sr":"06:41","ss":"17:14"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2015-10-31","hum":"18","pcpn":"0.0","pop":"0","pres":"1030","tmp":{"max":"15","min":"4"},"vis":"10","wind":{"deg":"202","dir":"无持续风向","sc":"微风","spd":"22"}},{"astro":{"sr":"06:42","ss":"17:13"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2015-11-01","hum":"14","pcpn":"0.0","pop":"0","pres":"1023","tmp":{"max":"16","min":"4"},"vis":"10","wind":{"deg":"285","dir":"无持续风向","sc":"微风","spd":"22"}},{"astro":{"sr":"06:43","ss":"17:12"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2015-11-02","hum":"16","pcpn":"0.0","pop":"0","pres":"1020","tmp":{"max":"17","min":"5"},"vis":"10","wind":{"deg":"178","dir":"无持续风向","sc":"微风","spd":"22"}},{"astro":{"sr":"06:45","ss":"17:10"},"cond":{"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"},"date":"2015-11-03","hum":"33","pcpn":"0.0","pop":"0","pres":"1020","tmp":{"max":"17","min":"7"},"vis":"10","wind":{"deg":"189","dir":"无持续风向","sc":"微风","spd":"22"}},{"astro":{"sr":"06:46","ss":"17:09"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2015-11-04","hum":"48","pcpn":"0.0","pop":"0","pres":"1020","tmp":{"max":"16","min":"7"},"vis":"10","wind":{"deg":"158","dir":"无持续风向","sc":"微风","spd":"22"}}]
     * hourly_forecast : [{"date":"2015-10-29 16:00","hum":"20","pop":"0","pres":"1028","tmp":"12","wind":{"deg":"327","dir":"西北风","sc":"3-4","spd":"22"}},{"date":"2015-10-29
     * 19:00","hum":"26","pop":"0","pres":"1030","tmp":"10","wind":{"deg":"326","dir":"西北风","sc":"微风","spd":"15"}},{"date":"2015-10-29
     * 22:00","hum":"31","pop":"0","pres":"1032","tmp":"8","wind":{"deg":"317","dir":"西北风","sc":"微风","spd":"13"}}]
     * now : {"cond":{"code":"100","txt":"晴"},"fl":"10","hum":"18","pcpn":"0","pres":"1025","tmp":"12","vis":"10","wind":{"deg":"280","dir":"西北风","sc":"4-5","spd":"31"}}
     * status : ok suggestion : {"comf":{"brf":"较舒适","txt":"白天天气晴好、但风力较强，您早晚会感觉偏凉，午后舒适、宜人。"},"cw":{"brf":"较不宜","txt":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"极易发","txt":"将有一次强降温过程，天气寒冷，且风力较强，极易发生感冒，请特别注意增加衣服保暖防寒。"},"sport":{"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"},"trav":{"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    private String status;
    /**
     * comf : {"brf":"较舒适","txt":"白天天气晴好、但风力较强，您早晚会感觉偏凉，午后舒适、宜人。"} cw :
     * {"brf":"较不宜","txt":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"} drsg :
     * {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"} flu : {"brf":"极易发","txt":"将有一次强降温过程，天气寒冷，且风力较强，极易发生感冒，请特别注意增加衣服保暖防寒。"}
     * sport : {"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"} trav :
     * {"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"} uv :
     * {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
     */

    private SuggestionEntity suggestion;
    /**
     * astro : {"sr":"06:39","ss":"17:16"} cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
     * date : 2015-10-29 hum : 16 pcpn : 0.0 pop : 0 pres : 1028 tmp : {"max":"12","min":"2"} vis :
     * 10 wind : {"deg":"319","dir":"北风","sc":"4-5","spd":"22"}
     */

    private List<DailyForecastEntity> daily_forecast;
    /**
     * date : 2015-10-29 16:00 hum : 20 pop : 0 pres : 1028 tmp : 12 wind :
     * {"deg":"327","dir":"西北风","sc":"3-4","spd":"22"}
     */

    private List<HourlyForecastEntity> hourly_forecast;

    public AqiEntity getAqi() {
        return aqi;
    }

    public void setAqi(AqiEntity aqi) {
        this.aqi = aqi;
    }

    public BasicEntity getBasic() {
        return basic;
    }

    public void setBasic(BasicEntity basic) {
        this.basic = basic;
    }

    public NowEntity getNow() {
        return now;
    }

    public void setNow(NowEntity now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SuggestionEntity getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionEntity suggestion) {
        this.suggestion = suggestion;
    }

    public List<DailyForecastEntity> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecastEntity> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<HourlyForecastEntity> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(List<HourlyForecastEntity> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public static class AqiEntity {

        /**
         * aqi : 34 co : 0 no2 : 11 o3 : 63 pm10 : 33 pm25 : 7 qlty : 优 so2 : 2
         */

        private CityEntity city;

        public CityEntity getCity() {
            return city;
        }

        public void setCity(CityEntity city) {
            this.city = city;
        }

        public static class CityEntity {

            private String aqi;
            private String co;
            private String no2;
            private String o3;
            private String pm10;
            private String pm25;
            private String qlty;
            private String so2;

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }
    }

    public static class BasicEntity {

        private String city;
        private String cnty;
        private String id;
        private String lat;
        private String lon;
        /**
         * loc : 2015-10-29 16:49 utc : 2015-10-29 08:49
         */

        private UpdateEntity update;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public UpdateEntity getUpdate() {
            return update;
        }

        public void setUpdate(UpdateEntity update) {
            this.update = update;
        }

        public static class UpdateEntity {

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
    }

    public static class NowEntity {

        /**
         * code : 100 txt : 晴
         */

        private CondEntity cond;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        private String tmp;
        private String vis;
        /**
         * deg : 280 dir : 西北风 sc : 4-5 spd : 31
         */

        private WindEntity wind;

        public CondEntity getCond() {
            return cond;
        }

        public void setCond(CondEntity cond) {
            this.cond = cond;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindEntity getWind() {
            return wind;
        }

        public void setWind(WindEntity wind) {
            this.wind = wind;
        }

        public static class CondEntity {

            private String code;
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class WindEntity {

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class SuggestionEntity {

        /**
         * brf : 较舒适 txt : 白天天气晴好、但风力较强，您早晚会感觉偏凉，午后舒适、宜人。
         */

        private ComfEntity comf;
        /**
         * brf : 较不宜 txt : 较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。
         */

        private CwEntity cw;
        /**
         * brf : 较冷 txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
         */

        private DrsgEntity drsg;
        /**
         * brf : 极易发 txt : 将有一次强降温过程，天气寒冷，且风力较强，极易发生感冒，请特别注意增加衣服保暖防寒。
         */

        private FluEntity flu;
        /**
         * brf : 较不宜 txt : 天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。
         */

        private SportEntity sport;
        /**
         * brf : 适宜 txt : 天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。
         */

        private TravEntity trav;
        /**
         * brf : 中等 txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
         */

        private UvEntity uv;

        public ComfEntity getComf() {
            return comf;
        }

        public void setComf(ComfEntity comf) {
            this.comf = comf;
        }

        public CwEntity getCw() {
            return cw;
        }

        public void setCw(CwEntity cw) {
            this.cw = cw;
        }

        public DrsgEntity getDrsg() {
            return drsg;
        }

        public void setDrsg(DrsgEntity drsg) {
            this.drsg = drsg;
        }

        public FluEntity getFlu() {
            return flu;
        }

        public void setFlu(FluEntity flu) {
            this.flu = flu;
        }

        public SportEntity getSport() {
            return sport;
        }

        public void setSport(SportEntity sport) {
            this.sport = sport;
        }

        public TravEntity getTrav() {
            return trav;
        }

        public void setTrav(TravEntity trav) {
            this.trav = trav;
        }

        public UvEntity getUv() {
            return uv;
        }

        public void setUv(UvEntity uv) {
            this.uv = uv;
        }

        public static class ComfEntity {

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class CwEntity {

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class DrsgEntity {

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class FluEntity {

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class SportEntity {

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class TravEntity {

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class UvEntity {

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }

    public static class DailyForecastEntity {

        /**
         * sr : 06:39 ss : 17:16
         */

        private AstroEntity astro;
        /**
         * code_d : 100 code_n : 100 txt_d : 晴 txt_n : 晴
         */

        private CondEntity cond;
        private String date;
        private String hum;
        private String pcpn;
        private String pop;
        private String pres;
        /**
         * max : 12 min : 2
         */

        private TmpEntity tmp;
        private String vis;
        /**
         * deg : 319 dir : 北风 sc : 4-5 spd : 22
         */

        private WindEntity wind;

        public AstroEntity getAstro() {
            return astro;
        }

        public void setAstro(AstroEntity astro) {
            this.astro = astro;
        }

        public CondEntity getCond() {
            return cond;
        }

        public void setCond(CondEntity cond) {
            this.cond = cond;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public TmpEntity getTmp() {
            return tmp;
        }

        public void setTmp(TmpEntity tmp) {
            this.tmp = tmp;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindEntity getWind() {
            return wind;
        }

        public void setWind(WindEntity wind) {
            this.wind = wind;
        }

        public static class AstroEntity {

            private String sr;
            private String ss;

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }
        }

        public static class CondEntity {

            private String code_d;
            private String code_n;
            private String txt_d;
            private String txt_n;

            public String getCode_d() {
                return code_d;
            }

            public void setCode_d(String code_d) {
                this.code_d = code_d;
            }

            public String getCode_n() {
                return code_n;
            }

            public void setCode_n(String code_n) {
                this.code_n = code_n;
            }

            public String getTxt_d() {
                return txt_d;
            }

            public void setTxt_d(String txt_d) {
                this.txt_d = txt_d;
            }

            public String getTxt_n() {
                return txt_n;
            }

            public void setTxt_n(String txt_n) {
                this.txt_n = txt_n;
            }
        }

        public static class TmpEntity {

            private String max;
            private String min;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }
        }

        public static class WindEntity {

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class HourlyForecastEntity {

        private String date;
        private String hum;
        private String pop;
        private String pres;
        private String tmp;
        /**
         * deg : 327 dir : 西北风 sc : 3-4 spd : 22
         */

        private WindEntity wind;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public WindEntity getWind() {
            return wind;
        }

        public void setWind(WindEntity wind) {
            this.wind = wind;
        }

        public static class WindEntity {

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }
}
