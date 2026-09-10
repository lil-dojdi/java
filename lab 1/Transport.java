import java.util.Random;
import java.util.Scanner;

public class Transport {
    // 1. Поля класса
    private int passengerCount;          
    private double[] passengerWeights;   
    private static int createdObjectsCount = 0; 

    // 2. Конструкторы

    // 2.1 Конструктор по умолчанию
    public Transport() {
        this.passengerCount = 0;
        this.passengerWeights = new double[0]; 
        createdObjectsCount++;
    }

    // 2.2 Конструктор с параметрами №1 (массив весов)
    public Transport(double[] weights) {
        setPassengerWeights(weights); 
        createdObjectsCount++;
    }

    // 2.3 Конструктор с параметрами №2 (число пассажиров)
    public Transport(int passengerCount) {
        this.passengerCount = passengerCount;
        this.passengerWeights = new double[passengerCount]; 
        createdObjectsCount++;
    }

    // 2.4 Конструктор копирования
    public Transport(Transport other) {
        this.passengerCount = other.passengerCount;
        this.passengerWeights = new double[other.passengerCount]; 
        for (int i = 0; i < other.passengerCount; i++) {
            this.passengerWeights[i] = other.passengerWeights[i];
        }
        createdObjectsCount++;
    }

    // 3. Функции доступа и изменения (get / set)

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public double[] getPassengerWeights() {
        return passengerWeights;
    }

    public void setPassengerWeights(double[] weights) {
        if (weights != null) {
            this.passengerCount = weights.length;
            this.passengerWeights = new double[this.passengerCount];
            for (int i = 0; i < weights.length; i++) {
                this.passengerWeights[i] = weights[i];
            }
        } else {
            this.passengerCount = 0;
            this.passengerWeights = new double[0];
        }
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество пассажиров: ");
        int count = scanner.nextInt();
        
        double[] newWeights = new double[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Введите вес пассажира " + (i + 1) + ": ");
            newWeights[i] = scanner.nextDouble();
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
        System.out.println("Выберете способ заполнения данных:");
        System.out.println("1. Ввести данные с клавиатуры");
        System.out.println("2. Заполнить случайными значениями");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                fillFromKeyboard();
                break;
            case 2:
                fillRandom();
                break;
            default:
                System.out.println("Некорректный выбор");
        }
    }

    // 5. Вычисления и сравнения

    public double getTotalCapacity() {
        double sum = 0;
        for (int i = 0; i < passengerWeights.length; i++) {
            sum += passengerWeights[i];
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

        vector[1] = new Transport(new double[]{90.0, 50.0});

        vector[2] = new Transport(vector[1]); 

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