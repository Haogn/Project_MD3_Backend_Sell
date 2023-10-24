package ra.util;

import ra.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase<T> {
    public static final String USER_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\users.txt";
    public static final String PRODUCT_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\product.txt";
    public static final String CATALOG_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\catalog.txt";
    public static final String ORDER_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\order.txt";
    public static final String USER_LOGIN_PATH = "C:\\Java-rikkei\\MD3\\Project_MD3\\src\\ra\\util\\userlogin.txt";

    public void writeToFile(List<T> o , String path) {
        FileOutputStream out = null ;;
        ObjectOutputStream outputStream = null ;
        try {
            out = new FileOutputStream(path);
            outputStream = new ObjectOutputStream(out) ;
            outputStream.writeObject(o);
        } catch (FileNotFoundException e) {
            System.err.print("");
        } catch (IOException e) {
            System.out.println("");
        } finally {
            if (out != null ) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("");
                }
            }

            if ( outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("");
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
            System.out.println("");
        } catch (ClassNotFoundException e) {
            System.out.println("");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("");
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("");
                }
            }
        }
        return o ;
    }
//    TODO : userLogin
public void writeToFileLogin(User o , String path) {
    FileOutputStream out = null ;;
    ObjectOutputStream outputStream = null ;
    try {
        out = new FileOutputStream(path);
        outputStream = new ObjectOutputStream(out) ;
        outputStream.writeObject(o);
    } catch (FileNotFoundException e) {
        System.err.print("");
    } catch (IOException e) {
        System.out.println("");
    } finally {
        if (out != null ) {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("");
            }
        }

        if ( outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                System.out.println("");
            }
        }
    }
}

    public User readUserFrom(String path) {
        FileInputStream in = null ;
        ObjectInputStream inputStream = null ;
       User user = new User();

        try {
            in = new FileInputStream(path) ;
            inputStream = new ObjectInputStream(in);
            user =(User) inputStream.readObject() ;
        } catch (FileNotFoundException e) {
            System.err.print("");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("");
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("");
                }
            }
        }
        return user ;
    }
}
