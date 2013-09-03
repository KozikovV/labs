package laba8;

import java.io.Serializable;

import interfaces.task8.CyclicItem;

/**
 * Элемент циклической коллекции.
 * 
 * @author zinchenko
 *
 */
public class CyclicItemImpl implements CyclicItem, Serializable {

    private transient Object temp;
    private Object value;
    private CyclicItem nextItem;

    /**
     * Конструктор по умолчанию
     */
    public CyclicItemImpl() {
    	 nextItem =this;
    }

    /**
     * Конструктор с параметрами.
     * 
     * @param value
     *            сериализуемое значение
     * @param temp
     *            не сериализуемое значение
     */
    public CyclicItemImpl(Object value, Object temp) {
        super();
        this.temp = temp;
        this.value = value;
        nextItem =this;
    }

    @Override
    public Object getTemp() {
        return temp;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public CyclicItem nextItem() {
        return nextItem;
    }

    @Override
    public void setNextItem(CyclicItem arg0) {
        nextItem = arg0;
    }

    @Override
    public void setTemp(Object arg0) {
        temp = arg0;
    }

    @Override
    public void setValue(Object arg0) {
        value = arg0;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CyclicItemImpl other = (CyclicItemImpl) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CyclicItemImpl [temp=" + temp + ", value=" + value + "]";
    }

}
