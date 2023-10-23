package ra.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase<T> {
    public static final String USER_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\users.txt";
    public static final String PRODUCT_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\product.txt";
    public static final String CATALOG_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\catalog.txt";
    public static final String ORDER_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\order.txt";

    public void writeToFile(List<T> o , String path) {
        FileOutputStream out = null ;;
        ObjectOutputStream outputStream = null ;
        try {
            out = new FileOutputStream(path);
            outputStream = new ObjectOutputStream(out) ;
            outputStream.writeObject(o);
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.err.print("");
        } catch (IOException e) {
            System.err.println(DataBase.class + " Xảy ra lỗi khi ghi FIle .....");
        } finally {
            if (out != null ) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println(DataBase.class + " Xảy ra lỗi khi ghi FIle .....");
                }
            }

            if ( outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.err.println(DataBase.class + " Xảy ra lỗi khi ghi FIle .....");
                }
            }
        }
    }

    public List<T>  readFormFile(String path) {
        FileInputStream in = null ;
        ObjectInputStream inputStream = null ;
        List<T> o = new ArrayList<>();

        try {
            in = new FileInputStream(path) ;
            inputStream = new ObjectInputStream(in);
            o =(List<T>) inputStream.readObject() ;
        } catch (FileNotFoundException e) {
            System.err.print("");

        } catch (IOException e) {
            System.err.println(DataBase.class + " Xảy ra lỗi khi đọc File ");
        } catch (ClassNotFoundException e) {
                System.err.println(DataBase.class + " Xảy ra lỗi khi đọc File ");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println(DataBase.class + " Xảy ra lỗi khi đọc File ");
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.err.println(DataBase.class + " Xảy ra lỗi khi đọc File ");
                }
            }
        }
        return o ;
    }
}
