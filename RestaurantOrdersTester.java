//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: RestaurantOrdersTester.java
// Course: CS 300 Fall 2020
//
// Author: Huong Nguyen
// Email: htnguyen23@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements unit test methods to check the correctness of LinkedOrders and
 * RestaurantOrders classes defined in the CS300 Fall 2020 - P07 Restaurant Orders programming
 * assignment.
 *
 */
public class RestaurantOrdersTester {

  /**
   * This method should test and make use of at least the LinkedOrders constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedOrders class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedOrders() {
    // test constructors
    Order order1 = new Order("Chicken", 1);
    Order order2 = new Order("Dumplings", 4);
    LinkedOrder linkedOrder3 = new LinkedOrder(new Order("Rice", 2));
    LinkedOrder linkedOrder4 = new LinkedOrder(new Order("Yogurt", 3));
    LinkedOrder linkedOrder1 = new LinkedOrder(order1);
    LinkedOrder linkedOrder2 = new LinkedOrder(order2, linkedOrder3, linkedOrder4);

    // test accessors
    if (!linkedOrder1.getOrder().getDishes().equals("Chicken"))
      return true;
    if (linkedOrder2.getNext().getOrder().compareTo(linkedOrder4.getOrder()) != 0)
      return true;
    if (linkedOrder2.getPrevious().getOrder().compareTo(linkedOrder3.getOrder()) != 0)
      return true;

    // test mutators
    linkedOrder2.setNext(linkedOrder3);
    linkedOrder2.setPrevious(linkedOrder4);
    if (linkedOrder2.getNext().getOrder().compareTo(linkedOrder3.getOrder()) != 0)
      return true;
    if (linkedOrder2.getPrevious().getOrder().compareTo(linkedOrder4.getOrder()) != 0)
      return true;

    return false;
  }

  /**
   * This method checks for the correctness of both RestaurantOrders constructors and the instance
   * method isEmpty() when called on an empty restaurant orders object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorIsEmpty() {
    RestaurantOrders resOrder1 = new RestaurantOrders();
    RestaurantOrders resOrder2 = new RestaurantOrders(10);
    if (resOrder1.capacity() != 20)
      return true;
    if (resOrder2.capacity() != 10)
      return true;
    if (!resOrder1.isEmpty())
      return true;
    return false;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders(int) constructor when passed a
   * negative int value for the capacity.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorBadInput() {
    try {
      RestaurantOrders resOrder1 = new RestaurantOrders(-2);
      return true;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    } catch (Exception iae) {
      System.out.println("Unexpected expection thrown.");
      return true;
    }
    return false;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder()() method when it is
   * passed bad inputs. This method must consider at least the test scenarios provided in the
   * detailed description of these javadocs. (1) Try adding a null to the list; (2) Try adding an
   * order which carries a negative timestamp. (3) Try adding an order with an existing timestamp to
   * the list.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAddBadInput() {
    RestaurantOrders resOrder1 = new RestaurantOrders();
    // Try adding a null to the list
    try {
      resOrder1.placeOrder(null);
      return true;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
    // Try adding an order which carries a negative timestamp
    try {
      resOrder1.placeOrder(new Order("Chicken", -22));
      return true;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
    // Try adding an order with an existing timestamp to the list
    try {
      resOrder1.placeOrder(new Order("Chicken", 1));
      resOrder1.placeOrder(new Order("Yogurt", 1));
      return true;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
    return false;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder()() considering at
   * least the test scenarios provided in the detailed description of these javadocs. (1) Try adding
   * an order to an empty list; (2) Try adding an order which is expected to be added at the head of
   * a non-empty restaurant list; (3) Try adding an order which is expected to be added at the end
   * of a non-empty restaurant list; (4) Try adding an order which is expected to be added at the
   * middle of a non-empty restaurant list. For each of those scenarios, make sure that the size of
   * the list is appropriately updated after a call without errors of the add() method, and that the
   * contents of the list is as expected whatever if list is read in the forward or backward
   * directions. You can also check the correctness of RestaurantOrders.get(int),
   * RestaurantOrders.indexOf(Order), and RestaurantOrders.size() in this test method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAdd() {
    RestaurantOrders resOrder1 = new RestaurantOrders();
    // try adding an order to an empty list 
    resOrder1.placeOrder(new Order("Chicken", 2));
    if (resOrder1.size() != 1)
      return true;
    // Try adding an order which is expected to be added at the head of a non-empty restaurant list
    Order order1 = new Order("Yogurt", 1);
    resOrder1.placeOrder(order1);
    if (resOrder1.indexOf(order1) != 0)
      return true;
    // Try adding an order which is expected to be added at the end of a non-empty restaurant list
    Order order2 = new Order("Rice", 4);
    resOrder1.placeOrder(order2);
    if (resOrder1.indexOf(order2) != 0)
      return true;
    // Try adding an order which is expected to be added at the middle of a non-empty restaurant list
    Order order3 = new Order("Dumplings", 3);
    resOrder1.placeOrder(order3);
    if (resOrder1.get(3).compareTo(order3) != 0)
      return true;
    return false;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.removeOrder() considering at
   * least the test scenarios provided in the detailed description of these javadocs. (1) Try
   * removing an order from an empty list or pass a negative index to RestaurantOrders.removeOrder()
   * method; (2) Try removing an order (at position index 0) from a list which contains only one
   * order; (3) Try to remove an order (position index 0) from a list which contains at least 2
   * orders; (4) Try to remove an order from the middle of a non-empty list containing at least 3
   * orders; (5) Try to remove the order at the end of a list containing at least two orders. For
   * each of those scenarios, make sure that the size of the list is appropriately updated after a
   * call without errors of the add() method, and that the contents of the list is as expected
   * whatever if list is read in the forward or backward directions.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersRemove() {
    // Try removing an order from an empty list or pass a negative index to RestaurantOrders.removeOrder() method
    RestaurantOrders resOrder1 = new RestaurantOrders();
    try {
      resOrder1.removeOrder(2);
    }
    catch (IndexOutOfBoundsException iobe) {
      System.out.println("Unable to remove order due to invalid index.");
    }
    try {
      resOrder1.removeOrder(-1);
    }
    catch (IndexOutOfBoundsException iobe) {
      System.out.println("Unable to remove order due to invalid index.");
    }
    // Try removing an order (at position index 0) from a list which contains only one order
      resOrder1.placeOrder(new Order("chicken", 1));
      resOrder1.removeOrder(0);
      if (resOrder1.size() != 0)
        return true;
    // Try to remove an order (position index 0) from a list which contains at least 2 orders
      resOrder1.placeOrder(new Order("yogurt", 2));
      resOrder1.placeOrder(new Order("dumplings", 3));
      resOrder1.removeOrder(0);
      if (resOrder1.size() != 1)
        return true;
    // Try to remove an order from the middle of a non-empty list containing at least 3 orders
      resOrder1.placeOrder(new Order("fish", 4));
      resOrder1.placeOrder(new Order("rice", 5));
      resOrder1.removeOrder(2);
      if (resOrder1.size() != 2)
        return true;
    // Try to remove the order at the end of a list containing at least two orders
      resOrder1.removeOrder(1);
    return false;
  }


  /**
   * This method calls all the test methods defined and implemented in your RestaurantOrdersTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if (testLinkedOrders())
      System.out.println("testLinkedOrders() failed");
    System.out.println("---");
    if (testRestaurantOrdersConstructorIsEmpty())
      System.out.println("testRestaurantOrdersConstructorIsEmpty() failed");
    System.out.println("---");
    if (testRestaurantOrdersConstructorBadInput())
      System.out.println("testRestaurantOrdersConstructorBadInput() failed");
    System.out.println("---");
    if (testRestaurantOrdersAddBadInput())
      System.out.println("testRestaurantOrdersAddBadInput() failed");
    System.out.println("---");
    if (testRestaurantOrdersAdd())
      System.out.println("testRestaurantOrdersAdd() failed");
    System.out.println("---");
    System.out.println("OMG Done Testing!");
    return false;
  }

  /**
   * Driver method defined in this RestaurantOrdersTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
