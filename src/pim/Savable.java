package pim;

public interface Savable {
	/**
	 * Add or update the save file for a savable instance
	 */
    void addOrUpdate();

	/**
	 * Delete the save file for a savable instance.
	 */
    void delete();
}
