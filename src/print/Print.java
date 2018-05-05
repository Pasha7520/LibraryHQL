package print;

import java.util.List;

public interface Print<T> {
	public void print(T t);
	public void printList(List<T> list);
}
