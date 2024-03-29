package lab06.Refer_answer;

import java.util.*;
class Exercise24_01 {
  public static void main(String[] args) {
    new Exercise24_01();
  }

  public Exercise24_01() {
    String[] name1 = {"Tom", "George", "Peter", "Jean", "Jane"};
    String[] name2 = {"Tom", "George", "Michael", "Michelle", "Daniel"};
    
    MyList<String> list1 = new MyArrayList<String>(name1);   
    MyList<String> list2 = new MyArrayList<String>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.addAll(list2);
    System.out.println("After addAll:" + list1 + "\n");
    
    list1 = new MyArrayList<String>(name1);
    list2 = new MyArrayList<String>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.removeAll(list2);
    System.out.println("After removeAll:" + list1 + "\n");
    
    list1 = new MyArrayList<String>(name1);
    list2 = new MyArrayList<String>(name2);   
    System.out.println("list1:" + list1);
    System.out.println("list2:" + list2);
    list1.retainAll(list2);
    System.out.println("After retainAll:" + list1 + "\n");
  }

  public interface MyList<E> extends java.lang.Iterable {
    /** Add a new element at the end of this list */
    public void add(E e);

    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Clear the list */
    public void clear();

    /** Return true if this list contains the element */
    public boolean contains(E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(E e);

    /** Return true if this list contains no elements */
    public boolean isEmpty();

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the first occurrence of the element o from this list.
     *  Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public boolean remove(E e);

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     *  with the specified element and return the old element. */
    public Object set(int index, E e);

    /** Return the number of elements in this list */
    public int size();
    
    public boolean addAll(MyList<E> otherList);

    /** Removes all the elements in otherList from this list 
      * Returns true if this list changed as a result of the call */
    public boolean removeAll(MyList<E> otherList);

    /** Retains the elements in this list that are also in otherList 
      * Returns true if this list changed as a result of the call */
    public boolean retainAll(MyList<E> otherList);
  }

  public abstract class MyAbstractList<E> implements MyList<E> {
    protected int size = 0; // The size of the list

    /** Create a default list */
    protected MyAbstractList() {
    }

    /** Create a list from an array of objects */
    protected MyAbstractList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    /** Add a new element at the end of this list */
    public void add(E e) {
      add(size, e);
    }

    /** Return true if this list contains no elements */
    public boolean isEmpty() {
      return size == 0;
    }

    /** Return the number of elements in this list */
    public int size() {
      return size;
    }

    /** Remove the first occurrence of the element o from this list.
     *  Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public boolean remove(E e) {
      if (indexOf(e) >= 0) {
        remove(indexOf(e));
        return true;
      }
      else 
        return false;
    }
    
    /** Adds the elements in otherList to this list.
     * Returns true if this list changed as a result of the call */
    public boolean addAll(MyList<E> otherList) {
      for (int i = 0; i < otherList.size(); i++)
        add(otherList.get(i));
      
      if (otherList.size() > 0) 
        return true;
      else
        return false;
    }
  
    /** Removes all the elements in otherList from this list 
      * Returns true if this list changed as a result of the call */
    public boolean removeAll(MyList<E> otherList) {
      boolean changed = false;
      for (int i = 0; i < otherList.size(); i++) {
        if (remove(otherList.get(i)))
          changed = true;
      }
        
      return changed;
    }  
    
    /** Retains the elements in this list that are also in otherList 
      * Returns true if this list changed as a result of the call */
    public boolean retainAll(MyList<E> otherList) {
      boolean changed = false;
      for (int i = 0; i < this.size(); ) {
        if (!otherList.contains(this.get(i))) {
          this.remove(get(i));
          changed = true;
        }
        else
          i++;
      }
        
      return changed;
    }
  }

  public class MyArrayList<E> extends MyAbstractList<E> {
    public static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];
    private int size = 0;
    
    /** Create a default list */
    public MyArrayList() {
    }

    /** Create a list from an array of objects */
    public MyArrayList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]); // Warning: don�t use super(objects)! 
    }

    /** Add a new element at the specified index in this list */
    public void add(int index, E e) {
      ensureCapacity();

      // Move the elements to the right after the specified index
      for (int i = size - 1; i >= index; i--)
        data[i + 1] = data[i];

      // Insert new element to data[index]
      data[index] = e;

      // Increase size by 1
      size++;
    }

    /** Create a new larger array, double the current size + 1 */
    private void ensureCapacity() {
      if (size >= data.length) {
        E[] newData = (E[])(new Object[size * 2 + 1]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }

    /** Clear the list */
    public void clear() {
      data = (E[])new Object[INITIAL_CAPACITY];
      size = 0;
    }

    /** Return true if this list contains the element */
    public boolean contains(Object e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return true;

      return false;
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
      return data[index];
    }

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(Object e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return i;

      return -1;
    }

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(Object e) {
      for (int i = size - 1; i >= 0; i--)
        if (e.equals(data[i])) return i;

      return -1;
    }

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index) {
      E e = data[index];

      // Shift data to the left
      for (int j = index; j < size - 1; j++)
        data[j] = data[j + 1];

      data[size - 1] = null; // This element is now null

      // Decrement size
      size--;

      return e;
    }

    /** Replace the element at the specified position in this list
     *  with the specified element. */
    public E set(int index, E e) {
      E old = data[index];
      data[index] = e;
      return old;
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("[");

      for (int i = 0; i < size; i++) {
        result.append(data[i]);
        if (i < size - 1) result.append(", ");
      }

      return result.toString() + "]";
    }

    /** Trims the capacity to current size */
    public void trimToSize() {
      if (size != data.length) { 
        E[] newData = (E[])(new Object[size]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      } // If size == capacity, no need to trim
    }

    /** Override the iterator method defined in Iterable */
    public java.util.Iterator<E> iterator() {
      return new ArrayListIterator();
    }

    private class ArrayListIterator 
        implements java.util.Iterator<E> {
      private int current = 0; // Current index 

      public boolean hasNext() {
        return (current < size);
      }

      public E next() {
        return data[current++];
      }

      public void remove() {
        MyArrayList.this.remove(current);
      }
    }

    @Override
    public int size() {
      // TODO Auto-generated method stub
      return size;
    }
  }
}
