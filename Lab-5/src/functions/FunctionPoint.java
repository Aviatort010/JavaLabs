package functions;

import java.text.DecimalFormat;

/**
 * Класс - точка функции.
 *
 * @author Aviatort010
 */
public class FunctionPoint {
//========================================[Поля класса]========================================
    /**
     * Координаты точки.
     */
    private double x = 0, y = 0;

//========================================[Конструкторы]========================================

    /**
     * Конструктор точки функции по координатам x, y.
     *
     * @param x абсцисса.
     * @param y ордината.
     */
    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор копирования.
     *
     * @param otherPoint исходная точка (откуда копировать).
     */
    public FunctionPoint(FunctionPoint otherPoint) {
        this.x = otherPoint.getX();
        this.y = otherPoint.getY();
    }

    /**
     * Конструктор по умолчанию.
     * Создаётся точка (0, 0).
     */
    public FunctionPoint() {
        this.x = 0;
        this.y = 0;
    }

//==============================[public функции]==============================

    /**
     * Возвращает Абсциссу точки.
     *
     * @return x.
     */
    public double getX() {
        return x;
    }

    /**
     * Возвращает Ординату точки.
     *
     * @return y.
     */
    public double getY() {
        return y;
    }

    /**
     * Изменяет абсциссу точки.
     *
     * @param x новая абсцисса.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Изменяет Ординату точки.
     *
     * @param y новая ордината.
     */
    public void setY(double y) {
        this.y = y;
    }

//==============================[Перегруженные функции]==============================

    /**
     * Перегрузка преобразования в строку.
     *
     * @return (x, y).
     */
    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#.###");
        return "(" + formatter.format(x) + "; " + formatter.format(y) + ")";
    }

    /**
     * Перегрузка сравнения на равество.
     *
     * @param o объект для сравнения.
     * @return Dot1 =? Dot2.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != FunctionPoint.class) return false;
        if (((FunctionPoint) o).getX() != x) return false;
        return ((FunctionPoint) o).getY() == y;
    }

    // Идея в написании следующей функции принадлежит NODAX

    /**
     * Перегрузка генерации ХешКода.
     * ХешКод - Целочисленный идендификатор объекта в программе.
     *
     * @return ХешКод точки функции.
     */
    @Override
    public int hashCode() {
        // Переводим double в двойной Int ( - long) без потерь информации.
        long doubleIntX = Double.doubleToLongBits(x);
        // >>> - нулевой оператор сдвига вправо.
        int reHash = (int) (doubleIntX ^ (doubleIntX >>> 32));
        // То же делаем и с y и добавляем к Результату.
        long doubleIntY = Double.doubleToLongBits(y);
        reHash = 31 * reHash + (int) (doubleIntY ^ (doubleIntY >>> 32));

        return reHash;
    }

    /**
     * Перегрузка клонирования точки функции.
     *
     * @return клон исходной точки.
     * @throws CloneNotSupportedException
     */
    @Override
    public FunctionPoint clone() throws CloneNotSupportedException {
        try {
            return (FunctionPoint) super.clone();
        } catch (CloneNotSupportedException e) {
            return new FunctionPoint(x, y);
        }
    }
}
