package Stack;

/**
 * interface of stack, all method should be O(1)
 * @param <AnyType>
 */
public interface StackInterface<AnyType> {
    boolean push(AnyType item);
    AnyType pop();
    AnyType peek();
    boolean isEmpty();
}
