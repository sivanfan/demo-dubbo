package com.ule.demo.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @ClassName ClassUtil
 * @Author fanxl
 * @Description
 * @Date 16:41  2019/1/28
 **/
@Slf4j
public class ClassUtil {
    @Value("${handler.package}")
    public static final String HANDLER_PACKAGE ="";

    /**
     * @Author fanxl
     * @Description 搜索classpath下的类的名称集合(包名+类名)
     * @Date 11:01 2019/1/29
     * @param packageName
     *            包名
     * @param recursion
     *            是否递归搜索
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> getClassPathClassNames(String packageName, boolean recursion) {
        List<String> classNames = new ArrayList<String>();
        String packageDirName = packageName.replace(".", "/");
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if ("file".equalsIgnoreCase(protocol)) {
                    String absolutePackagePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    addClassFile(packageName, absolutePackagePath, recursion, classNames);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("package not found : " + packageName, e);
        }
        return classNames;
    }
    /**
     * @Author fanxl
     * @Description 以文件的形式获取包下面的所有Class
     * @Date 11:01 2019/1/29
     * @param packageName
     * @param absolutePackagePath
     * @param recursion
     * @param classNames
     * @return java.util.List<java.lang.String>
     **/
    private static void addClassFile(String packageName, String absolutePackagePath, final boolean recursion, List<String> classNames) {
        File dir = new File(absolutePackagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            log.warn("package not found : {}",packageName);
            return;
        }
        File[] dirFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (recursion && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        for (File file : dirFiles) {
            if (file.isDirectory()) {
                addClassFile(getClassOrPackName(packageName, file.getName()), file.getAbsolutePath(), recursion, classNames);
            } else {
                String classFileName = file.getName().substring(0, file.getName().length() - 6);
                classNames.add(getClassOrPackName(packageName, classFileName));
            }
        }
    }

    private static String getClassOrPackName(String packageName, String classOrSubPackageName) {
        return StringUtils.isNotBlank(packageName) ? packageName + "." + classOrSubPackageName : classOrSubPackageName;
    }
}
