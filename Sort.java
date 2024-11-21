import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new WrappedDLL<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		// Base case if list is empty or has one element
		// Stack will then reconstruct the entire list
		if (list.size() < 2) {
			return;
		}

		// Else its the recursive case
		// Choose a pivot/partition element

		// Don't actually choose the first element
		T pivot = list.removeFirst(); // Take the first element as the pivot point

		// Compare elements to pivot, Smaller to the left, Larger to the right
		IndexedUnsortedList<T> leftList = newList();
		IndexedUnsortedList<T> rightList = newList();

		// Gather all the elements from the original list
		while (!list.isEmpty()) {
			T element = list.removeFirst();

			// compareTo -1 means smaller, 1 means larger
			if (element.compareTo(pivot) < 0) {
				leftList.add(element);
			}
			else {
				rightList.add(element);
			}
		}

		// Recursively quickSort the left and right side
		quicksort(leftList);
		quicksort(rightList);

		// Reassemble the list
		// Take the left list values first
		while (!leftList.isEmpty()) {
			list.add(leftList.removeFirst());
		}

		// Now the pivot
		list.add(pivot);

		// Now the right list
		while (!rightList.isEmpty()) {
			list.add(rightList.removeFirst());
		}
	}
		
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		// Base case if list is empty or has one element
		// Stack will then reconstruct the entire list
		if (list.size() < 2) {
			return;
		}

		// Else its the recursive case
		// Choose a pivot/partition element

		// Don't actually choose the first element
		T pivot = list.removeFirst(); // Take the first element as the pivot point

		// Compare elements to pivot, Smaller to the left, Larger to the right
		IndexedUnsortedList<T> leftList = newList();
		IndexedUnsortedList<T> rightList = newList();

		// Gather all the elements from the original list
		while (!list.isEmpty()) {
			T element = list.removeFirst();

			// Now using the comparator, take compare the element against the pivot
			if (c.compare(element, pivot) < 0) {
				leftList.add(element);
			}
			else {
				rightList.add(element);
			}
		}

		// Recursively quickSort the left and right side
		quicksort(leftList, c);
		quicksort(rightList, c);

		// Reassemble the list
		// Take the left list values first
		while (!leftList.isEmpty()) {
			list.add(leftList.removeFirst());
		}

		// Now the pivot
		list.add(pivot);

		// Now the right list
		while (!rightList.isEmpty()) {
			list.add(rightList.removeFirst());
		}
	}
}