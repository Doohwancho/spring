package com.example.di.nextstep.stage4;

import java.util.Set;

/*

얼라 얘도 구현 안됬네?

annotation 기반(@Inject, @Service, @Repository)으로 찾는 건가?
그냥 packageName 기반으로 찾는건가?

 */
public class ClassPathScanner {
    public static Set<Class<?>> getAllClassesInPackage(final String packageName) {
        return null;
    }

    /*

    예제 코드 (https://cnpnote.tistory.com/entry/SPRING-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%EC%97%90%EC%84%9C-%EC%8A%A4%ED%94%84%EB%A7%81%EA%B3%BC-%EA%B0%99%EC%9D%80-%ED%8C%A8%ED%82%A4%EC%A7%80-%EC%8A%A4%EC%BA%90%EB%8B%9D-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0)

    public static void findSubClasses(Context context, Class parent) {
        ApplicationInfo ai = context.getApplicationInfo();
        String classPath = ai.sourceDir;
        DexFile dex = null;
        try {
            dex = new DexFile(classPath);
            Enumeration<String> apkClassNames = dex.entries();
            while (apkClassNames.hasMoreElements()) {
                String className = apkClassNames.nextElement();
                try {
                    Class c = context.getClassLoader().loadClass(className);
                    if (parent.isAssignableFrom(c)) {
                        android.util.Log.i("nora", className);
                    }
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //              android.util.Log.i("nora", className);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                dex.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

     */
}

