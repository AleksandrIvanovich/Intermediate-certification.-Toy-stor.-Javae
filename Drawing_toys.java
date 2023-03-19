package Toy_storу;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Drawing_toys {
   
        public static void main(String[] args) {

            String[] toys = new String[] {"Мишка", "Машинка", "Юла", "Кукла", "Мяч", "Кубики", null};
            int[] chance = new int[] {10, 30, 15, 5, 10, 10, 20}; // 
            Scanner scanner = new Scanner(System.in);
            boolean run = true;
            Map<Integer, String> listNum = new HashMap<>();
            int num = 0;

            while(run){ 
                String word = getString(scanner); 
                num = num+1;
                switch (word) { 
                    case "1": 
                        String str = getPrize(toys, chance);
                            if (str != null){
                                listNum.put(num, str);
                                System.out.println("Вы выиграли игрушку:  " + str);
                                }
                            else{
                                System.out.println("Вы ничего не выиграли");
                                num = num - 1;
                                }  
                            if (!listNum.isEmpty()){
                                System.out.println("Ваш выигрыш: " + listNum.toString()); 
                                }     
                        break;
                    case "2":
                        String string = collectWinnings(listNum,num-1);
                        if (string == "Вам нечего получать!"){
                            System.out.println(string);
                            }
                        else {
                            System.out.println("Вы забрали: " + string);
                            addInFile(string);
                            num = num -2;
                        }
                        if (!listNum.isEmpty()){
                            System.out.println("Ещё можно забрать: " + listNum);
                        }
                        break;
                    case "3":
                       run = false;
                       cleanFile();
                        break;
                    default:
                        System.out.println("Вы что-то ввели не так");
                        num = num-1;
                        break;
                    }    
                }
            }
        public static String getString(Scanner scanner) {
            System.out.println("\n1 - Играть.\n2 - Забрать выигрыш.\n3 - Закончить игру.");
            String result = scanner.nextLine();
            return result;   
        }        

        private static String getPrize(String[] toys, int[] chance) {   

            int count = IntStream.of(chance).sum(); // Считаем количество элементов воображаемого массива
            String strNum = new String();
            Random random = new Random(); // Генерация случайного числа
            for (int randomNumsCount = 0; randomNumsCount < 1; randomNumsCount++) {              
                int index = random.nextInt(count); // Выбираем случайный индекс из воображаемого массива
                for (int i = 0; i < chance.length; i++) { // Ищем элемент, которому принадлежит этот индекс
                    index -= chance[i];
                    if(index < 0) {
                        strNum = toys[i];
                        break;
                    } 
                }
            } 
            return strNum;
        }

        private static String collectWinnings(Map<Integer, String> list, int num) {
            String result = new String();
           if (list.isEmpty()){
               result = "Вам нечего получать!";  
           }
           else{
            result = list.get(num);
            list.remove(num);
           }
        return result;
       }

       public static void cleanFile() { 

        String line = "Спасибо за игру!";
        try {
            PrintWriter fileWriter = new PrintWriter("toys.txt");
            fileWriter.close();
        
        } catch (Exception e) {

        } finally {
            System.out.println(line);
        }
    }

    public static void addInFile(String args) { 

            String line = "Выигрыш добавлен в файл";
        try {
            String pathProject = System.getProperty("user.dir");
            String pathFile = pathProject.concat("/toys.txt");
            File file = new File(pathFile);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(args);
            fileWriter.close();
    
        } catch (Exception e) {

        } finally {
            if (args != null){
            System.out.println(line);
            }
        }
    }
}

