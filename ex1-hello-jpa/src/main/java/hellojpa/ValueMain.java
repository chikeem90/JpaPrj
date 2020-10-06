package hellojpa;

public class ValueMain {
    public static void main(String[] args) {
        Integer a = new Integer(10);
        Integer b = a;

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        Address address1 = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");

        System.out.println(address1 == address2);
        System.out.println(address1.equals(address2));
    }
}
