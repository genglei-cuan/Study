package steve.cn.mylib.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 检测是否有SIM的工具类 着重解决关于双卡双待的手机检测问题
 * Created by Steve on 2015/7/29.
 */
public class SIMCardUtil {

    private static SIMCardUtil simCardUtil;
    private String imsiSIM1;
    private String imsiSIM2;
    private boolean isSIM1Ready;
    private boolean isSIM2Ready;

    private SIMCardUtil() {
    }


    //简单判断单卡是否有sim卡
    public static boolean hasSIM(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {
            return true;
        } else {
            return false;
        }
    }


    //判断双卡，无奈次API只出现在22之后
    @TargetApi(22)
    public static boolean hasDual(Context context) {
        SubscriptionManager subscriptionManager = SubscriptionManager.from(context);
        List<SubscriptionInfo>
                activeSubscriptionInfoList =
                subscriptionManager.getActiveSubscriptionInfoList();
        System.out.println(subscriptionManager.getActiveSubscriptionInfoCount());
        for (SubscriptionInfo info : activeSubscriptionInfoList) {
            System.out.println(info.getSimSlotIndex());
        }
        return false;
    }


    /**
     * 获取单例
     */
    public static SIMCardUtil getInstance(Context context) {
        if (simCardUtil == null) {
            simCardUtil = new SIMCardUtil();
            TelephonyManager
                    telephonyManager =
                    ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
            simCardUtil.imsiSIM1 = telephonyManager.getDeviceId();
            simCardUtil.imsiSIM2 = null;

            try {
                simCardUtil.imsiSIM1 = getDeviceIdBySlot(context, "getDeviceIdGemini", 0);
                simCardUtil.imsiSIM2 = getDeviceIdBySlot(context, "getDeviceIdGemini", 1);
            } catch (GeminiMethodNotFoundException e) {
                e.printStackTrace();
                try {
                    simCardUtil.imsiSIM1 = getDeviceIdBySlot(context, "getDeviceId", 0);
                    simCardUtil.imsiSIM2 = getDeviceIdBySlot(context, "getDeviceId", 1);
                } catch (GeminiMethodNotFoundException e1) {
                    //Call here for next manufacturer's predicted method name if you wish
                    e1.printStackTrace();
                }
            }

            simCardUtil.isSIM1Ready =
                    telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY;
            simCardUtil.isSIM2Ready = false;

            try {
                simCardUtil.isSIM1Ready = getSIMStateBySlot(context, "getSimStateGemini", 0);
                simCardUtil.isSIM2Ready = getSIMStateBySlot(context, "getSimStateGemini", 1);
            } catch (GeminiMethodNotFoundException e) {

                e.printStackTrace();

                try {
                    simCardUtil.isSIM1Ready = getSIMStateBySlot(context, "getSimState", 0);
                    simCardUtil.isSIM2Ready = getSIMStateBySlot(context, "getSimState", 1);
                } catch (GeminiMethodNotFoundException e1) {
                    //Call here for next manufacturer's predicted method name if you wish
                    e1.printStackTrace();
                }
            }
        }

        return simCardUtil;
    }


    /**
     * 查询IMEI号
     *
     * @param predictedMethodName 不同生厂商的方法名不一样，所以需要外部传入
     * @param slotID              第几个卡槽
     * @return 返回的是IMEI号
     */
    private static String getDeviceIdBySlot(Context context, String predictedMethodName, int slotID)
            throws GeminiMethodNotFoundException {
        String imsi = null;
        TelephonyManager
                telephony =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());
            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimID = telephonyClass.getMethod(predictedMethodName, parameter);
            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object ob_phone = getSimID.invoke(telephony, obParameter);
            if (ob_phone != null) {
                imsi = ob_phone.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeminiMethodNotFoundException(predictedMethodName);
        }
        return imsi;
    }

    /**
     * 获得SIM卡的使用状态
     *
     * @param predictedMethodName 方法名字
     * @param slotID              第几张卡
     * @return 是否在使用
     */
    private static boolean getSIMStateBySlot(Context context, String predictedMethodName, int slotID)
            throws GeminiMethodNotFoundException {
        boolean isReady = false;
        TelephonyManager
                telephony =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());
            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimStateGemini = telephonyClass.getMethod(predictedMethodName, parameter);
            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object ob_phone = getSimStateGemini.invoke(telephony, obParameter);
            if (ob_phone != null) {
                int simState = Integer.parseInt(ob_phone.toString());
                if (simState == TelephonyManager.SIM_STATE_READY) {
                    isReady = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeminiMethodNotFoundException(predictedMethodName);
        }
        return isReady;
    }

    /**
     * 打印TelephonyManager定义的所有方法以来找到上面方法所需要的方法名
     *
     * @param context 上下文
     */
    public static void printTelephonyManagerMethodNamesForThisDevice(Context context) {
        TelephonyManager
                telephony =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Class<?> telephonyClass;
        try {
            telephonyClass = Class.forName(telephony.getClass().getName());
            Method[] methods = telephonyClass.getMethods();
            for (int idx = 0; idx < methods.length; idx++) {
                System.out
                        .println("\n" + methods[idx] + " declared by " + methods[idx].getDeclaringClass());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取第一张卡的IMEI
    public String getImsiSIM1() {
        return imsiSIM1;
    }

    //获取第二张卡的IMEI
    public String getImsiSIM2() {
        return imsiSIM2;
    }

    //判断第一个SIM卡是否准备就绪
    public boolean isSIM1Ready() {
        return isSIM1Ready;
    }

    //判断第二个SIM卡是否准备就绪
    public boolean isSIM2Ready() {
        return isSIM2Ready;
    }

    //判断是否为双卡
    public boolean isDualSIM() {
        return imsiSIM2 != null;
    }

    private static class GeminiMethodNotFoundException extends Exception {

        private static final long serialVersionUID = -996812356902545308L;

        public GeminiMethodNotFoundException(String info) {
            super(info);
        }
    }

}
