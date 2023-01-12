//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinkedOrder.java
// Course:   CS 300 Fall 2020
//
// Author:   Huong Nguyen
// Email:    htnguyen23@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////


public class LinkedOrder {

  private final Order ORDER; // data field of this linkedOrder
  private LinkedOrder previous; // reference to the Order before this one
  private LinkedOrder next; // reference to the Order after this one

  /**
   * A one-argument constructor which sets previous and next to null.
   * 
   * @param order
   * @throws an IllegalArgumentException if order's timestamp is negative
   */
  public LinkedOrder(Order order) throws IllegalArgumentException {
    if (order == null)
      throw new IllegalArgumentException(
        "IllegalArgumentException - order is null");
    this.ORDER = order;
    this.next = null;
    this.previous = null;
    Order tempOrder = new Order("irrelevant", 0);
    if (order.compareTo(tempOrder) < 0)
      throw new IllegalArgumentException(
        "IllegalArgumentException - timestamp of order is negative");
  }

  /**
   * A three-argument constructor which sets all fields as specified.
   * 
   * @param order
   * @param prev
   * @param next
   * @throws IllegalArgumentException if order's timestamp is negative
   */
  public LinkedOrder(Order order, LinkedOrder prev, LinkedOrder next)
    throws IllegalArgumentException {
    this.ORDER = order;
    this.previous = prev;
    this.next = next;
    Order tempOrder = new Order("irrelevant", 0);
    if (order.compareTo(tempOrder) < 0)
      throw new IllegalArgumentException(
        "IllegalArgumentException - timestamp of order is negative");
  }

  /**
   * Returns a reference to the order from this LinkedOrder.
   */
  public Order getOrder() {
    return this.ORDER; // uhhh is this right lol
  }

  /**
   * Returns a reference to the previous LinkedOrder attached to this one
   */
  public LinkedOrder getPrevious() {
    return this.previous;
  }

  /**
   * Returns a reference to the next LinkedOrder attached to this one
   */
  public LinkedOrder getNext() {
    return this.next;
  }

  /**
   * Set the previous LinkedOrder attached to this one.
   * 
   * @param previous
   */
  public void setPrevious(LinkedOrder previous) {
    this.previous = previous;
  }

  /**
   * Sets the next Linked Order attached to this one.
   * 
   * @param next
   */
  public void setNext(LinkedOrder next) {
    this.next = next;
  }
}
