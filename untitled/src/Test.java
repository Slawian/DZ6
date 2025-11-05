public class Test {
    public static void main(String[] args) {
        System.out.println("--- Тестируем Cache<String> с емкостью 3 ---");
        Cache<String> stringCache = new Cache<>(3);

        // Добавление элементов и демонстрация ограничения емкости
        stringCache.add("яблоко");
        stringCache.add("банан");
        stringCache.add("вишня");
        System.out.println("После добавления 3 элементов: " + stringCache); // [яблоко, банан, вишня]

        stringCache.add("финик"); // 'яблоко' должно быть удалено
        System.out.println("После добавления 'финик': " + stringCache); //  [банан, вишня, финик]

        // Демонстрация exists()
        System.out.println("Существует ли 'банан'? " + stringCache.exists("банан")); //  true
        System.out.println("Существует ли 'яблоко'? " + stringCache.exists("яблоко"));   //  false

        // Демонстрация getFirst() и getLast()
        System.out.println("Первый элемент: " + stringCache.getFirst()); //  банан
        System.out.println("Последний элемент: " + stringCache.getLast());   //  финик

        // Демонстрация getItemByIndex()
        System.out.println("Элемент по индексу 1: " + stringCache.getItemByIndex(1)); // вишня
        System.out.println("Элемент по индексу 5 (вне диапазона): " + stringCache.getItemByIndex(5)); // null

        // Демонстрация remove()
        System.out.println("Удаляем 'вишня': " + stringCache.remove("вишня")); // true
        System.out.println("Кэш после удаления 'вишня': " + stringCache); // [банан, финик]
        System.out.println("Удаляем 'груша' (отсутствует): " + stringCache.remove("груша")); // false
        System.out.println("Кэш после попытки удаления 'груша': " + stringCache); // [банан, финик]

        System.out.println("\n--- Тестируем Cache<Integer> с емкостью 2 ---");
        Cache<Integer> intCache = new Cache<>(2);
        intCache.add(10);
        intCache.add(20);
        intCache.add(30); // 10 удаляется
        System.out.println("Кэш<Integer>: " + intCache); // [20, 30]
        System.out.println("Первое целое число: " + intCache.getFirst()); //  20
        System.out.println("Последнее целое число: " + intCache.getLast());   // 30

        System.out.println("\n--- Тестируем крайние случаи ---");
        Cache<Double> emptyCache = new Cache<>(1);
        System.out.println("Первый элемент в пустом кэше: " + emptyCache.getFirst()); // null
        System.out.println("Удаление из пустого кэша: " + emptyCache.remove(1.0)); // false

    }
}