import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

// Делаем ввод выражения через клавиатуру
        Scanner inputKey = new Scanner(System.in); // делаем возможным ввод данных с клавиатуры
        System.out.println("Введите ваше выражение"); // выводим текст
        String expression = inputKey.nextLine();
        String input = calc(expression);
    }

        public static String calc(String input ){


        // подключаем наш конфертер из римских в арабские
        Convert convert = new Convert();

        // Создаем  массив с возможными операциями
        String[] operation = {"+", "-", "/", "*"};
        String[] RegexOperation = {"\\+", "-", "/", "\\*"}; // экранируем, чтобы нормально разделить строки



        // Опеределяем какой знак действия используется
        int operationIndex = -1; // создаем и указываем, что изначально индекс равен -1, чтобы легко работать с масивом
        for (int i = 0; i < operation.length; i++) {
            if (input.contains(operation[i])) {
                operationIndex = i;
                break;
            }
        }

        //Если введен некорректный знак вычисления
        if (operationIndex == -1) { //проверяем, если индекс равен -1, то знак введен не верно
            System.out.println("Ошибка: введено некорректное выражение");

        }



            //разделяем строки
        String[] data = input.split(RegexOperation[operationIndex]);

        // проверяем, что только два числа учавствуют в вычислении
        if (data.length != 2){
            System.out.println("Ошибка: введите только два значения для операции");
            return input;
        }


        //Проверяем, не используются ли римские и арабские цифры одновременно
        if(convert.isRim(data[0]) == convert.isRim(data[1]))
        {
            int a = 0,b = 0;


            boolean isRim = convert.isRim(data[0]);
            if(isRim){

                // Если числа римские, то конвертируем в арабские
                a = convert.rimToInt(data[0]);
                b = convert.rimToInt(data[1]);

            }else {
                // если арабские, то конвертируем из string в и int
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if(a<0 || a>10) {
                System.out.println("Ошибка: числа не могут быть отрицательными или больше десяти");
                return input;

                }else {
                if(b<0 || b>10) {
                    System.out.println("Ошибка: числа не могут быть отрицательными или больше 10");
                    return input;
                }
            }




            int result = switch (operation[operationIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };

            if(isRim){
                // если римские, то результат в римском числе
                System.out.println(convert.intToRim(result));

            }
            else {
                // если в арабском, возвращаем в арабском числе
                System.out.println(result);

            }

        }else{
            System.out.println("Ошибка: числа должны быть в одном формате");
        }

        return input;
    }

}












