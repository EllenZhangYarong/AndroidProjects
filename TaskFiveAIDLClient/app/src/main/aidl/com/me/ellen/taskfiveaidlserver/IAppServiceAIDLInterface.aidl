// IAppServiceAIDLInterface.aidl
package com.me.ellen.taskfiveaidlserver;

// Declare any non-default types here with import statements
import com.me.ellen.taskfiveaidlserver.TimerServiceCallBack;
interface IAppServiceAIDLInterface {



            void registCallBack(TimerServiceCallBack callback);
            void unregistCallBack(TimerServiceCallBack callback);
}
