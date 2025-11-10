package iteration1;

public interface Person {
	default int getId() {
		return 0;
	};
	
	default String getName() {
		return "";
	}
}