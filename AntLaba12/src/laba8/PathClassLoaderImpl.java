package laba8;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.task8.PathClassLoader;

public class PathClassLoaderImpl extends ClassLoader implements PathClassLoader {

    private String path;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(final String arg0) {
    	
    	if (arg0 == null) {
			throw new NullPointerException();
		}
    	
        path = arg0;
    }

    @Override
    public Class<?> findClass(final String name) throws ClassNotFoundException {

        String classNameWithoutPackafe = getClassName(name);

        File file = null;
        try {
            file = getFile(name);
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException("couldn't find class with name "
                    + name, e);
        }

        byte[] bytes;
        try {
            bytes = getByte(file);
        } catch (IOException e1) {
            throw new ClassNotFoundException(
                    "couldn't get bytecode class with name " + name, e1);
        }

        // Class<?> result = defineClass("laba1.Point", bytes, 0, bytes.length);
        Class<?> result = defineClass(name, bytes, 0, bytes.length);

        return result;
    }

    /**
     * Загрузка массива байтов. В массов байтов записывается байт код класса.
     * 
     * @param file
     *            файл хранящий байткод класса
     * @return массив байтов класса
     * @throws ClassNotFoundException
     *             если файл не найден
     * @throws IOException
     *             исключение связанное с работой потока
     */
    private byte[] getByte(final File file) throws ClassNotFoundException,
            IOException {
        long length = file.length();

        InputStream is = null;

        is = new BufferedInputStream(new FileInputStream(file));

        byte[] bytes = new byte[(int) length];

        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) is.read();
        }

        is.close();

        return bytes;

    }

    /**
     * Получение имени файла.
     * 
     * @param name
     *            ивя файла байткода.
     * @return файл
     * @throws FileNotFoundException
     *             нет файла
     */
    private File getFile(final String name) throws FileNotFoundException {

        File file = new File(path + File.separator + name + ".class");

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        return file;
    }

    /**
     * Класс.
     * 
     * @param classNameWitPackage
     *            имя класса с именами пакетов
     * @return имя класса
     * @throws IllegalArgumentException
     *             неверное полное имя класса
     */
    private String getClassName(final String classNameWitPackage) {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(classNameWitPackage);
        String name = null;
        if (matcher.find()) {
            name = matcher.group();
        } else {
            throw new IllegalArgumentException();
        }

        return name;
    }
}
