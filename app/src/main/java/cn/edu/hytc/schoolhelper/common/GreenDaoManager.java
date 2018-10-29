package cn.edu.hytc.schoolhelper.common;

import org.greenrobot.greendao.query.QueryBuilder;

import cn.edu.hytc.schoolhelper.gen.DaoMaster;
import cn.edu.hytc.schoolhelper.gen.DaoSession;

/**
 * Created by admin on 2018/10/16.
 */

public class GreenDaoManager {
    private static  GreenDaoManager mInstance;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public GreenDaoManager(){
        if(mInstance==null){
            setDebugMode(true);
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "schoolHelperDb",null);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    private void setDebugMode(boolean flag) {
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    public static GreenDaoManager getInstance(){
        if(mInstance==null){
            synchronized (GreenDaoManager.class){
                if(mInstance==null){
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public static DaoMaster getMaster() {
        return mDaoMaster;
    }

    public static DaoSession getSession() {
        return mDaoSession;
    }
    public static  DaoSession getNewSession(){
        return mDaoMaster.newSession();
    }
}
