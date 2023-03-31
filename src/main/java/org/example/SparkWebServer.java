package org.example;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");
        get("/collatz", (req, res ) -> {
            String number = req.queryParams("value");
            return getResponse(Integer.parseInt(number));
        });
    }

    /**
     * metodo para verificar el puerto usado
     * @return Puerto
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String getResponse(int number) {
        return "{ \"operation\" : \"collatzsequence\", \n" +
                " \"input\" : " + number + ", \n" +
                " \"output\" : \"" + col(number) + "\" \n" +
                "}";
    }

    /**
     * Metodo que realiza la operación de la conjetura de collatz
     * @param number digitado por el usuario
     * @return Lista con los enteros
     */
    public static String col(int number){
        String solution = "";
        while (number>1){
            solution = solution + number + "->";
            if (number%2 == 0){
                number = number/2;
            } else {
                number = number*3 + 1;
            }
        }
        solution = solution + "1";
        return solution;
    }


}
