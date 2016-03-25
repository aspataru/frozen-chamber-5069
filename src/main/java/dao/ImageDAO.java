package dao;

import java.util.List;

public interface ImageDAO {

	boolean store(String fileURI, String name);

	boolean retrieve(String fileName, String localURI);

	boolean remove(String fileName);

	List<String> listContents();

}
