package com.lxy.qemoji.hook;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XposedHelpers;
public class Entry implements IXposedHookLoadPackage{
   
    @Override
   public void handleLoadPackage(LoadPackageParam loadPackageParam) {
        ClassLoader classLoader = null;
        if (loadPackageParam.packageName.equals( "com.tencent.mobileqq")) {
            if (loadPackageParam != null) {
                classLoader = loadPackageParam.classLoader;
            }
            XC_MethodHook r =
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            // this will be called before the clock was updated by the original
                            // method
                            param.setResult(-1);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            // this will be called after the clock was updated by the original
                            // method
                        }
                    };
                    try{
            Class loadClass =loadPackageParam.classLoader.loadClass("com.tencent.mobileqq.text.EmotcationConstants");
            XposedHelpers.findAndHookMethod(loadClass, "getSingleEmoji", Integer.TYPE, r );
            XposedHelpers.findAndHookMethod(loadClass, "getDoubleEmoji", Integer.TYPE, Integer.TYPE, r);
            }catch(ClassNotFoundException e){
            
            }
        }
        }
}
