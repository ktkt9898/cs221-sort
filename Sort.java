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
		return new IUDoubleLinkedList<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
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
		// Split the list into two halves until only one element remains
		int listSize = list.size();
		int midPoint = list.size() / 2;

		// Create two new lists to store the left and right side
		IndexedUnsortedList<T> leftList = newList();
		IndexedUnsortedList<T> rightList = newList();

		// Add the elements in the left half
		for (int i = 0; i < midPoint; i++) {
			leftList.add(list.removeFirst());
		}

		// Add the elements in the right half
		for (int i = midPoint; i < listSize; i++) {
			rightList.add(list.removeFirst());
		}

		// Recursively call mergesort on the two halves until only one element remains in each list
		mergesort(leftList);
		mergesort(rightList);

		// Use compareTo to compare the elements in the left list
		while (!leftList.isEmpty() || !rightList.isEmpty()) {
			// In the event that the left list is empty but later need to compare the right list
			if (leftList.isEmpty()) {
				list.add(rightList.removeFirst());
			}

			// In the event the right list is empty but later need to compare the left list
			else if (rightList.isEmpty()) {
				list.add(leftList.removeFirst());
			}

			// Otherwise, compare the elements in the left and right list
			else {
				// If the first element in the left list is less than the first element in the right list,
				// add the first element in the left list
				if (leftList.first().compareTo(rightList.first()) < 0) {
					list.add(leftList.removeFirst());
				}
				// Else the first element in the right list is less than the first element in the left list
				// add the first element in the right list
				else {
					list.add(rightList.removeFirst());
				}
			}
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
		// Split the list into two halves until only one element remains
		int listSize = list.size();
		int midPoint = list.size() / 2;

		// Create two new lists to store the left and right side
		IndexedUnsortedList<T> leftList = newList();
		IndexedUnsortedList<T> rightList = newList();

		// Add the elements in the left half
		for (int i = 0; i < midPoint; i++) {
			leftList.add(list.removeFirst());
		}

		// Add the elements in the right half
		for (int i = midPoint; i < listSize; i++) {
			rightList.add(list.removeFirst());
		}

		// Recursively call mergesort on the two halves until only one element remains in each list
		mergesort(leftList, c);
		mergesort(rightList, c);

		// Use the Comparator to compare the elements in the left list
		while (!leftList.isEmpty() || !rightList.isEmpty()) {
			// In the event that the left list is empty but later need to compare the right list
			if (leftList.isEmpty()) {
				list.add(rightList.removeFirst());
			}

			// In the event the right list is empty but later need to compare the left list
			else if (rightList.isEmpty()) {
				list.add(leftList.removeFirst());
			}

			// Otherwise, compare the elements in the left and right list
			else {
				// If the first element in the left list is less than the first element in the right list,
				// add the first element in the left list
				// Now using the Comparator to compare elements, similar process as compareTo
				if (c.compare(leftList.first(), rightList.first()) < 0) {
					list.add(leftList.removeFirst());
				}
				// Else the first element in the right list is less than the first element in the left list
				// add the first element in the right list
				else {
					list.add(rightList.removeFirst());
				}
			}
		}
	}
}