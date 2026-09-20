import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Transport {
    private static final int MAX_PASSENGERS = 1000;
    private static final double MAX_PASSENGER_WEIGHT = 500.0;
    private static final BufferedReader READER =
            new BufferedReader(new InputStreamReader(System.in));

    // 1. Поля класса
    private int passengerCount;          
    private double[] passengerWeights;   
    private static int createdObjectsCount = 0; 

    // 2. Конструкторы

    // 2.1 Конструктор по умолчанию
    public Transport() {
        this(0);
    }

    // 2.2 Конструктор с параметрами №1 (массив весов)
    public Transport(double[] weights) {
        setPassengerWeights(weights); 
        createdObjectsCount++;
    }

    // 2.3 Конструктор с параметрами №2 (число пассажиров)
    public Transport(int passengerCount) {
        setPassengerCount(passengerCount);
        createdObjectsCount++;
    }

    // 2.4 Конструктор копирования
    public Transport(Transport other) {
        if (other == null) {
            throw new IllegalArgumentException("Объект для копирования не может быть null");
        }
        setPassengerWeights(other.passengerWeights);
        createdObjectsCount++;
    }

    // 3. Функции доступа и изменения (get / set)

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        validatePassengerCount(passengerCount);
        this.passengerCount = passengerCount;
        this.passengerWeights = new double[passengerCount];
    }

    public double[] getPassengerWeights() {
        return passengerWeights.clone();
    }

    public void setPassengerWeights(double[] weights) {
        if (weights == null) {
            this.passengerCount = 0;
            this.passengerWeights = new double[0];
            return;
        }
        validatePassengerCount(weights.length);
        for (double weight : weights) {
            validateWeight(weight);
        }
        this.passengerCount = weights.length;
        this.passengerWeights = weights.clone();
    }

    public static int getCreatedObjectsCount() {
        return createdObjectsCount;
    }

    // 4. Методы заполнения и вывода

    public void printInfo() {
        System.out.println("----------------------------------------");
        System.out.println("Число пассажиров: " + passengerCount);
        System.out.print("Вес каждого пассажира: ");
        if (passengerCount == 0) {
            System.out.print("пассажиров нет");
        } else {
            for (int i = 0; i < passengerWeights.length; i++) {
                System.out.print(passengerWeights[i] + " кг; ");
            }
        }
        System.out.println();
        System.out.println("Общая грузоподъемность: " + getTotalCapacity() + " кг");
    }

    public void fillFromKeyboard() {
        int count = readInt("Введите количество пассажиров: ", 0, MAX_PASSENGERS);
        double[] newWeights = new double[count];
        for (int i = 0; i < count; i++) {
            newWeights[i] = readDouble("Введите вес пассажира " + (i + 1) + ": ",
                    0.0, MAX_PASSENGER_WEIGHT);
        }
        setPassengerWeights(newWeights);
    }

    public void fillRandom() {
        Random rand = new Random();
        int count = rand.nextInt(5) + 1; 
        double[] newWeights = new double[count];
        
        for (int i = 0; i < count; i++) {
            newWeights[i] = 40 + rand.nextInt(50); 
        }
        setPassengerWeights(newWeights); 
    }

    public void formFill() {
        System.out.println("Выберите способ заполнения данных:");
        System.out.println("1. Ввести данные с клавиатуры");
        System.out.println("2. Заполнить случайными значениями");
        int choice = readInt("Ваш выбор: ", 1, 2);
        if (choice == 1) {
            fillFromKeyboard();
        } else {
            fillRandom();
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = readLine();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Повторный запрос выводится ниже.
            }
            System.out.println("Ошибка: введите целое число от " + min + " до " + max + ".");
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = readLine().replace(',', '.');
            try {
                double value = Double.parseDouble(input);
                if (Double.isFinite(value) && value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Повторный запрос выводится ниже.
            }
            System.out.println("Ошибка: введите число от " + min + " до " + max + ".");
        }
    }

    private static String readLine() {
        try {
            String input = READER.readLine();
            if (input == null) {
                throw new IllegalStateException("Ввод завершен неожиданно");
            }
            return input.trim();
        } catch (IOException exception) {
            throw new IllegalStateException("Ошибка чтения данных", exception);
        }
    }

    private static void validatePassengerCount(int count) {
        if (count < 0 || count > MAX_PASSENGERS) {
            throw new IllegalArgumentException("Количество пассажиров должно быть от 0 до "
                    + MAX_PASSENGERS);
        }
    }

    private static void validateWeight(double weight) {
        if (!Double.isFinite(weight) || weight < 0 || weight > MAX_PASSENGER_WEIGHT) {
            throw new IllegalArgumentException("Вес пассажира должен быть от 0 до "
                    + MAX_PASSENGER_WEIGHT + " кг");
        }
    }

    // 5. Вычисления и сравнения

    public double getTotalCapacity() {
        double sum = 0;
        for (double weight : passengerWeights) {
            sum += weight;
        }
        return sum;
    }

    public void compareByPassengers(Transport other) {
        if (this.passengerCount > other.passengerCount) {
            System.out.println("Текущий транспорт везет больше пассажиров (" + this.passengerCount + " > " + other.passengerCount + ")");
        } else if (this.passengerCount < other.passengerCount) {
            System.out.println("Принятый транспорт везет больше пассажиров (" + this.passengerCount + " < " + other.passengerCount + ")");
        } else {
            System.out.println("Оба транспорта везут одинаковое число пассажиров (" + this.passengerCount + ")");
        }
    }

    public static void compareByTotalWeight(Transport t1, Transport t2) {
        double w1 = t1.getTotalCapacity();
        double w2 = t2.getTotalCapacity();

        if (w1 > w2) {
            System.out.println("Первый объект везет больше веса (" + w1 + " кг > " + w2 + " кг)");
        } else if (w1 < w2) {
            System.out.println("Второй объект везет больше веса (" + w1 + " кг < " + w2 + " кг)");
        } else {
            System.out.println("Оба объекта везут одинаковый вес (" + w1 + " кг)");
        }
    }

    // 6. Главный метод (main)

    public static void main(String[] args) {
        System.out.println("=== СОЗДАНИЕ ОДИНОЧНЫХ ОБЪЕКТОВ ===");
        
        Transport t1 = new Transport(); 
        t1.formFill();
        System.out.println("Объект 1:");
        t1.printInfo();

        double[] sampleWeights = {75.0, 82.5, 60.0};
        Transport t2 = new Transport(sampleWeights); 
        System.out.println("\nОбъект 2:");
        t2.printInfo();

        Transport t3 = new Transport(4); 
        t3.formFill();
        System.out.println("\nОбъект 3:");
        t3.printInfo();

        Transport t4 = new Transport(t2); 
        System.out.println("\nОбъект 4 (копия объекта 2):");
        t4.printInfo();

        System.out.println("\n=== ДИНАМИЧЕСКИЙ ВЕКТОР ОБЪЕКТОВ ===");
        Transport[] vector = new Transport[3];
        
        vector[0] = new Transport(); 
        vector[0].formFill();
        System.out.println("\nОбъект vector[0] после создания:");
        vector[0].printInfo();

        vector[1] = new Transport(new double[]{90.0, 50.0});
        System.out.println("\nОбъект vector[1] после создания:");
        vector[1].printInfo();

        vector[2] = new Transport(vector[1]); 
        System.out.println("\nОбъект vector[2] после создания:");
        vector[2].printInfo();

        for (int i = 0; i < vector.length; i++) {
            System.out.println("\nЭлемент вектора №" + (i + 1) + ":");
            vector[i].printInfo();
        }

        System.out.println("\n=== СРАВНЕНИЕ ВМЕСТИМОСТИ (ПАССАЖИРЫ) ===");
        System.out.print("Пара t1 и t2: ");
        t1.compareByPassengers(t2);

        System.out.print("Пара vector[0] и vector[1]: ");
        vector[0].compareByPassengers(vector[1]);

        System.out.println("\n=== СРАВНЕНИЕ ГРУЗОПОДЪЕМНОСТИ (ВЕС) ===");
        System.out.print("Пара t2 и t4: ");
        Transport.compareByTotalWeight(t2, t4);

        System.out.print("Пара vector[1] и vector[2]: ");
        Transport.compareByTotalWeight(vector[1], vector[2]);

        System.out.println("\nЧисло созданных объектов: " + Transport.getCreatedObjectsCount());
    }
}