package proj;

import java.util.Observable;

	public class SquareAndRectangleFrame extends OutputFrame {

	@Override
	public void update(Observable arg0, Object arg1) {
		AbstractList list = (MyArrayListWithIterator)arg1;
		String result = "";
		for (AbstractList.AbstractIterator info = list.createIterator(); !info.isDone(); info.next())
		{
			if (info.currentItem().hasRectOrSquare())
				result += info.currentItem().toString();
		}
		this.displayResult(result);
	}
}
