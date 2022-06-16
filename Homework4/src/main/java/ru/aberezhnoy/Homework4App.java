package ru.aberezhnoy;

public class Homework4App {
    public static void main(String[] args) {
        DoubleRelatedList<Cat> drl = new DoubleRelatedList<>();
        System.out.println(drl);
        drl.push(new Cat(1, "Barsik"));
        drl.push(new Cat(2, "Mursik"));
        drl.push(new Cat(3, "Snezhok"));
        System.out.println(drl);
        drl.pop();
        System.out.println(drl);
        MyIterator<Cat> mi = drl.getIterator();
        System.out.println(mi.getCurrent());
        System.out.println(mi.hasNext());
        mi.deleteCurrent();
        System.out.println(drl);
        drl.push(new Cat(4, "Pushok"));
        System.out.println(drl);
        drl.delete(new Cat(4, "Pushok"));
        System.out.println(drl);
        System.out.println(drl.contains(new Cat(2, "Mursik")));
        System.out.println(drl.contains(new Cat(4, "Pushok")));
        System.out.println(mi.hasNext());
        drl.push(new Cat(5, "Chamberlin"));
        System.out.println(mi.next());
    }
}
