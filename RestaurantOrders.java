//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: RestaurantOrders.java
// Course: CS 300 Fall 2020
//
// Author: Huong Nguyen
// Email: htnguyen23@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Anu B. (CS300 student) - helped with implementation for placeOrder() method
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////


public class RestaurantOrders implements SortedListADT<Order> {

  private LinkedOrder head; // front of the doubly-linked list
  private LinkedOrder tail; // end of the doubly-linked list
  private int size; // number of orders currently in the list
  private final int CAPACITY; // maximum number of orders for this list

  // A no-argument constructor which creates a list with capacity 20
  public RestaurantOrders() {
    this.CAPACITY = 20;
  }

  /**
   * A one-argument constructor which creates a list with the provided positive capacity.
   * 
   * @param capacity
   * @throws an Illegal ArgumentException if the provided capacity is 0 or negative.
   */
  public RestaurantOrders(int capacity) throws IllegalArgumentException {
    if (capacity <= 0)
      throw new IllegalArgumentException(
        "IllegalArgumentException - provided capacity is 0 or negative.");
    this.CAPACITY = capacity;
  }

  /**
   * @return the capacity of this list
   */
  public int capacity() {
    return this.CAPACITY;
  }

  /**
   * @eturn a String representation of the orders in this list from head to tail, space-separated.
   */
  public String readForward() {
    String result = "The list contains ";
    result += this.size() + " orders(s): [ ";
    LinkedOrder curNode = this.head;
    for (int i = 0; i < this.size(); i++) {
      result += curNode.toString() + " ";
      curNode = curNode.getNext();
    }
    result += "]";
    return result;
  }

  /**
   * @return a String representation of the orders in this list from tail to head, space-separated.
   */
  public String readBackward() {
    String result = "The list contains ";
    result += this.size() + " orders(s): [ ";
    LinkedOrder curNode = this.tail;
    for (int i = this.size() - 1; i >= 0; i--) {
      result += curNode.toString() + " ";
      curNode = curNode.getPrevious();
    }
    result += "]";
    return result;
  }

  /**
   * Removes all orders from this list.
   */
  @Override
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * @param index
   * @return the order at position index of this list, without removing it
   */
  @Override
  public Order get(int index) {
    LinkedOrder curNode = this.head;
    if (index == 0) // I only added in this b/c idk if my loop runs if they ask for index 0
      return curNode.getOrder();
    for (int i = 0; i < index; i++) {
      curNode = curNode.getNext();
    }
    return curNode.getOrder();
  }

  /**
   * Note that matching the timestamp here doesn't matter, we are only concerned with the food
   * contained in the order
   * 
   * @param findOrder
   * @return the index of the specified order's dishes in the list, or -1 if not found
   */
  @Override
  public int indexOf(Order findOrder) {
    int index = -1;
    LinkedOrder curNode = this.head;
    String key = findOrder.getDishes();
    for (int i = 0; i < this.size(); i++) {
      if (curNode.getOrder().getDishes().equals(key)) {
        index = i;
        return index;
      }
    }
    return index;
  }

  /**
   * @return true if and only if the list is currently empty; false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (this.size == 0)
      return true;
    return false;
  }

  /**
   * Adds a new Order to this sorted list in the correct position if there is room in the list.
   * 
   * @param newOrder
   * @throws an IllegalArgumentException if newOrder has the same timestamp as an existing order, a
   *            negative timestamp, or is null
   */
  @Override
  public void placeOrder(Order newOrder) throws IllegalArgumentException {
    if (newOrder == null)
      throw new IllegalArgumentException("IllegalArgumentException = null order");
    LinkedOrder orderToAdd = new LinkedOrder(newOrder);
    Order tempOrder = new Order("irrelevant", 0);
    if (tempOrder.compareTo(newOrder) > 0)
      throw new IllegalArgumentException("IllegalArgumentException - negative timestamp");
    if (this.size() < this.CAPACITY) {
      // insert order in an empty list
      if (isEmpty()) {
        this.tail = orderToAdd;
        this.head = orderToAdd;
        this.size += 1;
      } else if (this.tail.getOrder().compareTo(newOrder) == 0
        || this.head.getOrder().compareTo(newOrder) == 0) {
        throw new IllegalArgumentException(
          "IllegalArgumentException - same timestamp as an existing order");
      }
      // insert the order if the head timestamp is larger than the new order
      else if (this.head.getOrder().compareTo(newOrder) > 0) {
        orderToAdd.setNext(this.head);
        orderToAdd.getNext().setPrevious(orderToAdd);
        this.head = orderToAdd;
        this.size += 1;
      }
      // insert the order if the tail timestamp is smaller than the new order
      else if (this.tail.getOrder().compareTo(newOrder) < 0) {
        orderToAdd.setPrevious(this.tail);
        this.tail = orderToAdd;
        orderToAdd.getPrevious().setNext(orderToAdd);
        size += 1;
      }
      // insert the order in the correct position in middle of list
      else {
        LinkedOrder currNode = this.head;
        while (!(this.tail.equals(currNode))) {
          if (currNode.getOrder().compareTo(newOrder) == 0) {
            throw new IllegalArgumentException("IllegalArgumentException - same timestamp as an existing order");
          } else if ((newOrder.compareTo(currNode.getOrder()) > 0)
            && (currNode.getNext().getOrder().compareTo(newOrder) > 0)) {
            currNode.getNext().setPrevious(orderToAdd);
            orderToAdd.setNext(currNode.getNext());
            orderToAdd.setPrevious(currNode);
            currNode.setNext(orderToAdd);
            this.size += 1;
            break;
          }
          currNode = currNode.getNext();
        }

      }
    }

  }

  /**
   * Removes and returns the order at the order at the given index.
   * 
   * @param index
   * @return
   */
  @Override
  public Order removeOrder(int index) {
    if (index >= this.size() || index < 0)
      throw new IndexOutOfBoundsException("IndexOutOfBoundsException - invalid index to remove");
    LinkedOrder orderToRemove = this.head;
    Order removedOrder = null;
    int counter = 0;
    while (orderToRemove != null) {
      if (counter == index) {
        removedOrder = orderToRemove.getOrder();
        if (this.size() == 1) {
          this.tail = null;
          this.head = null;
          this.size -= 1;
        }
        else if (orderToRemove.equals(this.head)) {
          orderToRemove.getNext().setPrevious(null);
          this.head = orderToRemove.getNext();
          orderToRemove.setNext(null);
          this.size -= 1;
        }
        else if (orderToRemove.equals(this.tail)) {
          orderToRemove.getPrevious().setNext(null);
          this.tail = orderToRemove.getPrevious();
          orderToRemove.setPrevious(null);
          this.size -= 1;
        } else {
          orderToRemove.getPrevious().setNext(orderToRemove.getNext());
          orderToRemove.getNext().setPrevious(orderToRemove.getPrevious());
          orderToRemove.setNext(null);
          orderToRemove.setPrevious(null);
          size -= 1;
        }
        break;
      }
      orderToRemove = orderToRemove.getNext();
      counter += 1;
    }
    return removedOrder;
  }

  /**
   * @return the number of orders currently in the list
   */
  @Override
  public int size() {
    return this.size;
  }
}
