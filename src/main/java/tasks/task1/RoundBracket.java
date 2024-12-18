package main.java.tasks.task1;
/*
Якщо ми з коректно записаного арифметичного виразу, що містить числа, знаки операцій
і відкривають/закривають круглі дужки викинемо числа і знаки операцій, а потім запишемо дужки,
що залишилися без пробілів між ними, то отриманий результат назвемо правильним скобковим виразом
[( )(()))" - правильне, а "()(" і "())(" - ні].
Знайти число правильних дужних виразів, що містять N дужок,
що відкриваються і N закриваються. N вводиться із клавіатури. N невід'ємне ціле число.

Приклад:
N = 1 (по одній дужці, що відкривається і закривається) - відповідь 1
()
)(
))
((
Лише один правильний варіант

Для введеного числа 2 - 2:
()()
(())
Тобто лише два варіанти, коли всі відкриті дужки правильно відкриваються/закриваються.
І так далі.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoundBracket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N = ");
        int N = scanner.nextInt();

        List<String> expressions = new ArrayList<>(); // here the result  of expression will be saved
        if (N > 0) {                                  //checking 'N' for a positive value
            generateVariants(N, N, "", expressions);
        }
        System.out.printf("Answer: %s variants%n", expressions.size());
    }

    private static void generateVariants(int openedBracket, int closedBracket,
                                         String current, List<String> variants) {
        if (openedBracket > closedBracket) {
            return;
        }
        if (openedBracket == 0 && openedBracket == closedBracket) {
            System.out.println(current);
            variants.add(current);
        }
        if (openedBracket > 0) {
            generateVariants(openedBracket - 1, closedBracket, current + "(", variants);
        }
        if (closedBracket > 0) {
            generateVariants(openedBracket, closedBracket - 1, current + ")", variants);
        }
    }
}
