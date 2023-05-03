// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws Exception {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("Enter the math line");
        String line = in.nextLine();
        System.out.println(calc(line));
    }
    public static String calc(String line) throws Exception {
        char operator = ' ';
        String[] words;
        for (int i = 0; i < line.length(); i++) { //узнаем место расположение знака математической операции и какой это зна
            if (line.charAt(i) == '*')  operator = line.charAt(i);
            if (line.charAt(i) == '/') operator = line.charAt(i);
            if (line.charAt(i) == '-') operator = line.charAt(i);
            if (line.charAt(i) == '+') operator = line.charAt(i);
        }
        words = line.split("[+-/*]");
        if ((' ' == operator) || (words.length > 2)) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        words[0] = words[0].trim().toUpperCase();
        words[1] = words[1].trim().toUpperCase();
        byte RomeCheck = 0;
        for(int i = 0; i<2;i++){
            try {
                if(Integer.parseInt(words[i]) > 10 ||Integer.parseInt(words[i]) < 1)  throw new Exception("т.к. число не может быть больше 10 или меньше 1");
            }
            catch (NumberFormatException ex){
                RomeCheck++;
                words[i] = RomeArabTranslation(words[i]);
            }
        }
        if(RomeCheck == 1) throw new Exception("т.к. используются одновременно разные системы счисления");
        int result = 0;
        switch (operator) {
            case '+' -> result = Integer.parseInt(words[0]) + Integer.parseInt(words[1]);
            case '-' -> {
                result = Integer.parseInt(words[0]) - Integer.parseInt(words[1]);
                if (((Integer.parseInt(words[0]) - Integer.parseInt(words[1])) < 1) && RomeCheck == 2)
                    throw new Exception("т.к. в Римской системе исчесления нет 0 и отрицательных чисел");
            }
            case '/' -> result = Integer.parseInt(words[0]) / Integer.parseInt(words[1]);
            case '*' -> result = Integer.parseInt(words[0]) * Integer.parseInt(words[1]);
        }
        if (RomeCheck == 2) line = ArabRomeTrans(result);
        else   line = Integer.toString(result);
        return line;
    }


    public static String RomeArabTranslation(String recieveData) throws Exception {
        String[] arab = new String[]{"10", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] rome = new String[]{"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        for (int i = 0; i < 10; i++) if (recieveData.trim().toUpperCase().equals(rome[i]))  return arab[i];
        throw new Exception("т.к. введеные данные не соответсвует критерию");
    }

    static String ArabRomeTrans(int number) {
        String[] rome = new String[]{"C", "IC","XC", "L", "IL", "XL", "X","IX", "V","IV", "I"};
        String[] arab = new String[]{"100", "99", "90", "50", "49", "40", "10","9", "5","4", "1"};
        String result = " ";
        for(int i = 0; i < arab.length; i++) {
            for (int x = 0; x<3; x++) {
                if (number >= Integer.parseInt(arab[i])) {
                    result = result  + rome[i];
                    number = number - Integer.parseInt(arab[i]);
                }
            }
        }
        return result.trim();
    }
}

