package geoand.junit.agent;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {

    private static final String CLASS_NAME = "org.junit.runner.notification.RunNotifier";

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

                if (CLASS_NAME.replace(".", "/").equals(s)) {
                    try {
                        final ClassPool cp = ClassPool.getDefault();
                        final CtClass cc = cp.get(CLASS_NAME);

                        doNotNotifyListenersOfTestFailures(cc);

                        byte[] byteCode = cc.toBytecode();
                        cc.detach();
                        return byteCode;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                return null;
            }
        });
    }

    private static void doNotNotifyListenersOfTestFailures(CtClass cc) throws NotFoundException, CannotCompileException {
        final CtMethod fireTestFailuresMethod = cc.getDeclaredMethod("fireTestFailure");
        fireTestFailuresMethod.setBody("{return;}");
    }

}

