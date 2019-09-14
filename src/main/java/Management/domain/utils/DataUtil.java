package Management.domain.utils;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public final class DataUtil {
    private DataUtil() {
    }

    public static final String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder(b.length * 2);
        String stmp = "";

        for(int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }

        return hs.toString();
    }

    public static final byte[] hex2byte(String hs) {
        byte[] b = hs.getBytes();
        if (b.length % 2 != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        } else {
            byte[] b2 = new byte[b.length / 2];

            for(int n = 0; n < b.length; n += 2) {
                String item = new String(b, n, 2);
                b2[n / 2] = (byte)Integer.parseInt(item, 16);
            }

            return b2;
        }
    }

    public static final String getFullPathRelateClass(String relatedPath, Class<?> cls) {
        String path = null;
        if (relatedPath == null) {
            throw new NullPointerException();
        } else {
            String clsPath = getPathFromClass(cls);
            File clsFile = new File(clsPath);
            String tempPath = clsFile.getParent() + File.separator + relatedPath;
            File file = new File(tempPath);

            try {
                path = file.getCanonicalPath();
            } catch (IOException var8) {
                var8.printStackTrace();
            }

            return path;
        }
    }

    public static final String getPathFromClass(Class<?> cls) {
        String path = null;
        if (cls == null) {
            throw new NullPointerException();
        } else {
            URL url = getClassLocationURL(cls);
            if (url != null) {
                path = url.getPath();
                if ("jar".equalsIgnoreCase(url.getProtocol())) {
                    try {
                        path = (new URL(path)).getPath();
                    } catch (MalformedURLException var6) {
                        ;
                    }

                    int location = path.indexOf("!/");
                    if (location != -1) {
                        path = path.substring(0, location);
                    }
                }

                File file = new File(path);

                try {
                    path = file.getCanonicalPath();
                } catch (IOException var5) {
                    var5.printStackTrace();
                }
            }

            return path;
        }
    }

    public static final boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        } else if (pObj.equals("")) {
            return true;
        } else {
            if (pObj instanceof String) {
                if (((String)pObj).trim().length() == 0) {
                    return true;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection)pObj).size() == 0) {
                    return true;
                }
            } else if (pObj instanceof Map && ((Map)pObj).size() == 0) {
                return true;
            }

            return false;
        }
    }

    public static final boolean isNotEmpty(Object pObj) {
        if (pObj == null) {
            return false;
        } else if ("".equals(pObj)) {
            return false;
        } else {
            if (pObj instanceof String) {
                if (((String)pObj).trim().length() == 0) {
                    return false;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection)pObj).size() == 0) {
                    return false;
                }
            } else if (pObj instanceof Map && ((Map)pObj).size() == 0) {
                return false;
            }

            return true;
        }
    }

    public static final String replace4JsOutput(String pStr) {
        pStr = pStr.replace("\r\n", "<br/>&nbsp;&nbsp;");
        pStr = pStr.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        pStr = pStr.replace(" ", "&nbsp;");
        return pStr;
    }

    public static final String[] trim(String[] paramArray) {
        if (ArrayUtils.isEmpty(paramArray)) {
            return paramArray;
        } else {
            String[] resultArray = new String[paramArray.length];

            for(int i = 0; i < paramArray.length; ++i) {
                String param = paramArray[i];
                resultArray[i] = StringUtils.trim(param);
            }

            return resultArray;
        }
    }

    private static URL getClassLocationURL(Class<?> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("null input: cls");
        } else {
            URL result = null;
            String clsAsResource = cls.getName().replace('.', '/').concat(".class");
            ProtectionDomain pd = cls.getProtectionDomain();
            if (pd != null) {
                CodeSource cs = pd.getCodeSource();
                if (cs != null) {
                    result = cs.getLocation();
                }

                if (result != null && "file".equals(result.getProtocol())) {
                    try {
                        if (!result.toExternalForm().endsWith(".jar") && !result.toExternalForm().endsWith(".zip")) {
                            if ((new File(result.getFile())).isDirectory()) {
                                result = new URL(result, clsAsResource);
                            }
                        } else {
                            result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
                        }
                    } catch (MalformedURLException var6) {
                        ;
                    }
                }
            }

            if (result == null) {
                ClassLoader clsLoader = cls.getClassLoader();
                result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
            }

            return result;
        }
    }

    public static final <K> K ifNull(K k, K defaultValue) {
        return k == null ? defaultValue : k;
    }
}

