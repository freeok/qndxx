package work.pcdd.qndxx.util;

import java.io.File;

/**
 * @author AD
 * @date 2021/1/7
 */
public class UploadUtils {

    /**
     * 上传目录的路径
     * src/main/resources/static/uploads/
     */
    public static final String ROOT_PATH;

    /**
     * src/main/resources/static/uploads/image/
     */
    public static final String ROOT_IMG_PATH;

    /**
     * 项目的绝对路径 + src/main/resources/static/uploads/
     */
    public static final String ROOT_REAL_PATH;

    /**
     * 项目的绝对路径 + src/main/resources/static/uploads/image/
     */
    public static final String IMG_REAL_PATH;


    /**
     * 注意：src/main/resource/是不可改的
     */
    static {
        ROOT_PATH = "src"
                + File.separator + "main"
                + File.separator + "resources"
                + File.separator + "static"
                + File.separator + "uploads"
                + File.separator;

        ROOT_IMG_PATH = ROOT_PATH + "image" + File.separator;

        ROOT_REAL_PATH = new File(ROOT_PATH).getAbsolutePath() + File.separator;

        IMG_REAL_PATH = new File(ROOT_IMG_PATH).getAbsolutePath() + File.separator;
    }

    /**
     * 根据传入的目录名，文件名 创建目录或文件
     * 然后返回指定文件在当前项目的绝对路径
     *
     * @param args 指定的的目录，可以是一个目录或者多级目录，最后一个参数为文件名
     * @return 指定文件在当前项目的绝对路径
     */
    public static String getRealPath(String... args) {
        // 设置上传文件的路径，为上传目录绝对路径+自定义的路径
        StringBuffer dirPath = new StringBuffer(ROOT_PATH);

        // 拼接路径，最后一个参数是文件名，无需拼接
        for (int i = 0; i < args.length - 1; i++) {
            dirPath.append(args[i] + File.separator);
        }

        // 创建多级目录的File对象
        File file = new File(dirPath.toString());

        // 若dirName为多级目录且不存在，则创建多级目录
        if (!file.exists()) {
            file.mkdirs();
        }

        // 关键：返回文件绝对路径，这里的绝对路径是相当于当前项目的路径而不是"容器"路径
        return file.getAbsolutePath() + File.separator + args[args.length - 1];
    }
}
