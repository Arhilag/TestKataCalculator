import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args)throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите операцию:");
        String operation = in.nextLine();
        String result = calc(operation);
        System.out.println("Результат операции: "+result);
    }

    public static String calc(String input) throws IOException{
        String[] parts = input.split(" ");

        if(parts.length > 3){
            throw new IOException();
        }

        RimNumbers rim1 = checkRim(parts[0]);
        RimNumbers rim2 = checkRim(parts[2]);

        if(rim1!=RimNumbers.NULL && rim2!=RimNumbers.NULL){
            int num1 = translateRimToArabian(rim1);
            int num2 = translateRimToArabian(rim2);
            int result = calcAnyway(num1, parts[1], num2);
            System.out.println(result);
            if(result < 1){
                throw new IOException();
            }
            return convertToArabian(result);
        }

        if((rim1!=RimNumbers.NULL && rim2==RimNumbers.NULL) || (rim1==RimNumbers.NULL && rim2!=RimNumbers.NULL)){
            throw new IOException();
        }

        int num1 = Integer.valueOf(parts[0]);
        int num2 = Integer.valueOf(parts[2]);

        if(num1 > 10 || num1 < 1|| num2 > 10|| num2 < 1){
            throw new IOException();
        }

        String result = String.valueOf(calcAnyway(num1, parts[1], num2));
        return result;
    }

    private static RimNumbers checkRim(String num){
        switch (num){
            case "I":
                return RimNumbers.I;
            case "II":
                return RimNumbers.II;
            case "III":
                return RimNumbers.III;
            case "IV":
                return RimNumbers.IV;
            case "V":
                return RimNumbers.V;
            case "VI":
                return RimNumbers.VI;
            case "VII":
                return RimNumbers.VII;
            case "VIII":
                return RimNumbers.VIII;
            case "IX":
                return RimNumbers.IX;
            case "X":
                return RimNumbers.X;
        }
        return RimNumbers.NULL;
    }

    private static int calcAnyway(int num1, String operator, int num2){
        int result = -1;
        switch (operator){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }

    private static int translateRimToArabian(RimNumbers number){
        if(number == RimNumbers.I){
            return 1;
        }
        if(number == RimNumbers.II){
            return 2;
        }
        if(number == RimNumbers.III){
            return 3;
        }
        if(number == RimNumbers.IV){
            return 4;
        }
        if(number == RimNumbers.V){
            return 5;
        }
        if(number == RimNumbers.VI){
            return 6;
        }
        if(number == RimNumbers.VII){
            return 7;
        }
        if(number == RimNumbers.VIII){
            return 8;
        }
        if(number == RimNumbers.IX){
            return 9;
        }
        if(number == RimNumbers.X){
            return 10;
        }
        return -1;
    }

    public static String convertToArabian(int in) {
        StringBuilder  a = new StringBuilder("");

        int c1 = in / 100;
        a.append(C(c1));
        int c2 = in % 100;

        if(c2 == 0)
            return a.toString();

        int l1 = c2 / 50;
        a.append(L(l1));
        int l2 = c2 % 50;

        if(l2 == 0)
            return a.toString();

        int x1 = l2 / 10;
        a.append(X(x1));
        int x2 = l2 % 10;

        a.append(basic(x2));
        return a.toString();
    }

    private static String C(int in) {
        if (in == 4) return "CD"; //если 400, то 500-100
        else if ((in != 0) && (in < 4)) {
            StringBuffer a = new StringBuffer("");
            int i = 0;
            while (i < in) {
                a.append("C");
                i++;
            }
            return a.toString();
        }
        else return "";
    }

    private static String X(int in) {
        if (in == 4) return "XL"; // если 40, то 50-10
        else if ((in != 0) && (in < 4)) {
            StringBuffer a = new StringBuffer("");
            int i = 0;
            while (i < in) {
                a.append("X");
                i++;
            }
            return a.toString();
        }
        else return "";
    }

    private static String L(int in) {
        if (in == 4) return "XC";
        // если90 то100 - 10
        return "L";

    }

    private static String basic(int in) {
        String[] a = {
                "",
                "I",
                "II",
                "III",
                "IV",
                "V",
                "VI",
                "VII",
                "VIII",
                "IX"
        };
        return a[in];
    }
}